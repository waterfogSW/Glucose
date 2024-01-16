package com.waterfogsw.glucose.user.application.service.application

import com.waterfogsw.glucose.user.application.port.SocialLoginPortStub
import com.waterfogsw.glucose.user.application.port.UserSocialLoginInfoRepositorySpy
import com.waterfogsw.glucose.user.application.port.inbound.UserSocialLoginUseCase
import com.waterfogsw.glucose.user.application.service.UserRegisterUseCaseStub
import com.waterfogsw.glucose.user.application.service.applicaiton.UserSocialLoginApplicationService
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfoTestFixture
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.enums.Provider
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.util.*

class UserSocialLoginApplicationServiceTest : DescribeSpec({

    describe("소셜 로그인시") {
        context("이전에 소셜 로그인을 통해 가입한 적이 없으면") {
            it("새로 생성된 유저 아이디를 반환한다.") {
                // arrange
                val socialLoginPort = SocialLoginPortStub()
                val userSocialLoginInfoRepository = UserSocialLoginInfoRepositorySpy()
                val sut = UserSocialLoginApplicationService(
                    oidcPort = socialLoginPort,
                    userSocialLoginInfoRepository = userSocialLoginInfoRepository,
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

            val userSocialLoginInfoFixture: UserSocialLoginInfo = UserSocialLoginInfoTestFixture.create()
            val userSocialLoginInfoRepository = UserSocialLoginInfoRepositorySpy()
            userSocialLoginInfoRepository.save(userSocialLoginInfoFixture)

            it("해당 유저 아이디를 반환한다.") {
                // arrange
                val sut = UserSocialLoginApplicationService(
                    oidcPort = SocialLoginPortStub(),
                    userSocialLoginInfoRepository = userSocialLoginInfoRepository,
                    userRegisterUseCase = UserRegisterUseCaseStub()
                )

                // act
                val command = UserSocialLoginUseCase.Command("test", Provider.KAKAO)
                val result: UserSocialLoginUseCase.Result = sut.invoke(command)

                // assert
                check(result is UserSocialLoginUseCase.Result.Success)
                result.userId shouldBe userSocialLoginInfoFixture.userId
            }
        }
    }

})
