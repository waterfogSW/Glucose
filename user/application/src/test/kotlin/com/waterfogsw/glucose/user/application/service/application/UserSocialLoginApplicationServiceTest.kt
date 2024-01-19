package com.waterfogsw.glucose.user.application.service.application

import com.waterfogsw.glucose.support.jwt.util.JwtTokenProvider
import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.user.application.common.security.UserJwtTokenServiceFake
import com.waterfogsw.glucose.user.application.port.SocialLoginPortStub
import com.waterfogsw.glucose.user.application.port.inbound.UserSocialLoginUseCase
import com.waterfogsw.glucose.user.application.service.applicaiton.UserSocialLoginApplicationService
import com.waterfogsw.glucose.user.application.service.domain.UserDomainServiceSpy
import com.waterfogsw.glucose.user.application.service.domain.UserSocialLoginInfoDomainServiceSpy
import com.waterfogsw.glucose.user.domain.entity.User
import com.waterfogsw.glucose.user.domain.entity.UserTestFixture
import com.waterfogsw.glucose.user.domain.enums.Provider
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UserSocialLoginApplicationServiceTest : DescribeSpec({

    describe("유저 소셜 로그인 유스케이스") {

        context("유저가 가입되어있지 않으면") {

            it("가입을 위한 사용자 정보를 반환한다.") {
                // arrange
                val sut = UserSocialLoginApplicationService(
                    oidcPort = SocialLoginPortStub(),
                    userSocialLoginInfoDomainService = UserSocialLoginInfoDomainServiceSpy(),
                    userDomainService = UserDomainServiceSpy(),
                    userJwtTokenService = UserJwtTokenServiceFake(),
                )
                val provider = Provider.KAKAO
                val command = UserSocialLoginUseCase.Command("test", provider)

                // act
                val result: UserSocialLoginUseCase.Result = sut.invoke(command)

                // assert
                check(result is UserSocialLoginUseCase.Result.UserNotRegistered)
                result.provider shouldBe provider
                result.email shouldBe Email("test@test.com")
                result.name shouldBe "test"
                result.picture shouldBe null
            }
        }

        context("유저가 가입되어있으면") {

            val userSocialLoginInfoDomainService = UserSocialLoginInfoDomainServiceSpy()
            val userDomainService = UserDomainServiceSpy()
            val user: User = UserTestFixture.create()
            val provider = Provider.KAKAO

            userSocialLoginInfoDomainService.create(
                userId = user.id,
                email = user.email,
                provider = provider
            )
            userDomainService.save(user)

            it("로그인 토큰을 반환한다.") {
                // arrange
                val sut = UserSocialLoginApplicationService(
                    oidcPort = SocialLoginPortStub(),
                    userSocialLoginInfoDomainService = userSocialLoginInfoDomainService,
                    userDomainService = userDomainService,
                    userJwtTokenService = UserJwtTokenServiceFake(),
                )
                val command = UserSocialLoginUseCase.Command("test", provider)

                // act
                val result: UserSocialLoginUseCase.Result = sut.invoke(command)

                // assert
                check(result is UserSocialLoginUseCase.Result.Success)
                JwtTokenProvider.verifyToken(result.accessToken)
                    .getOrNull()!!.customClaims["type"] shouldBe "access"
                JwtTokenProvider.verifyToken(result.refreshToken)
                    .getOrNull()!!.customClaims["type"] shouldBe "refresh"
            }
        }
    }

})
