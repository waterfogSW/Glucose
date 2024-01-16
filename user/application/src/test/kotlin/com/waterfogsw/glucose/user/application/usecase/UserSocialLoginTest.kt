package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.application.port.SocialLoginPortStub
import com.waterfogsw.glucose.user.application.port.UserOAuthInfoRepositorySpy
import com.waterfogsw.glucose.user.domain.entity.OAuth2UserInfoTestFixture
import com.waterfogsw.glucose.user.domain.entity.UserOAuthInfo
import com.waterfogsw.glucose.user.domain.enums.Provider
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.util.*

class UserSocialLoginTest : DescribeSpec({

    describe("소셜 로그인시") {
        context("이전에 소셜 로그인을 통해 가입한 적이 없으면") {
            it("새로 생성된 유저 아이디를 반환한다.") {
                // arrange
                val socialLoginPort = SocialLoginPortStub()
                val userSocialLoginInfoRepository = UserOAuthInfoRepositorySpy()
                val sut = UserSocialLogin(
                    oidcPort = socialLoginPort,
                    userOAuthInfoRepository = userSocialLoginInfoRepository,
                    userRegisterUseCase = UserRegisterUseCaseStub(),
                )

                // act
                val command = UserSocialLoginUseCase.Command("test", Provider.KAKAO)
                val result: UserSocialLoginUseCase.Result = sut.invoke(command)

                // assert
                check(result is UserSocialLoginUseCase.Result.Success)
                result.userId.shouldBeInstanceOf<UUID>()
            }
        }

        context("이전에 소셜 로그인을 통해 가입한 적이 있으면") {

            val userOAuthInfoFixture: UserOAuthInfo = OAuth2UserInfoTestFixture.create()
            val userSocialLoginInfoRepository = UserOAuthInfoRepositorySpy()
            userSocialLoginInfoRepository.save(userOAuthInfoFixture)

            it("해당 유저 아이디를 반환한다.") {
                // arrange
                val sut = UserSocialLogin(
                    oidcPort = SocialLoginPortStub(),
                    userOAuthInfoRepository = userSocialLoginInfoRepository,
                    userRegisterUseCase = UserRegisterUseCaseStub()
                )

                // act
                val command = UserSocialLoginUseCase.Command("test", Provider.KAKAO)
                val result: UserSocialLoginUseCase.Result = sut.invoke(command)

                // assert
                check(result is UserSocialLoginUseCase.Result.Success)
                result.userId shouldBe userOAuthInfoFixture.userId
            }
        }
    }

})
