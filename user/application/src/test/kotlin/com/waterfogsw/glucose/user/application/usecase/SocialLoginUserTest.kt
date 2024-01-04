package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.application.port.stub.SocialLoginPortStub
import com.waterfogsw.glucose.user.application.port.stub.UserRepositorySpy
import com.waterfogsw.glucose.user.application.port.stub.UserSocialLoginInfoRepositorySpy
import com.waterfogsw.glucose.user.domain.enums.OAuth2Provider
import com.waterfogsw.glucose.user.domain.entity.UserSocialLoginInfo
import com.waterfogsw.glucose.user.domain.entity.OAuth2UserInfoTestFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SocialLoginUserTest : DescribeSpec({

    describe("소셜 로그인시") {
        context("이전에 소셜 로그인을 통해 가입한 적이 없으면") {
            it("새로운 유저를 생성하고, 로그인 정보를 저장한다.") {
                // arrange
                val socialLoginPort = SocialLoginPortStub()
                val userRepository = UserRepositorySpy()
                val userSocialLoginInfoRepository = UserSocialLoginInfoRepositorySpy()
                val sut = SocialLoginUser(
                    oidcPort = socialLoginPort,
                    userRepository = userRepository,
                    socialLoginInfoRepository = userSocialLoginInfoRepository
                )

                // act
                val command = SocialLoginUserUseCase.Command("test", OAuth2Provider.KAKAO)
                val result: SocialLoginUserUseCase.Result = sut.invoke(command)

                // assert
                check(result is SocialLoginUserUseCase.Result.Success)
                userRepository.existsById(result.userId) shouldBe true
                userSocialLoginInfoRepository.existsByUserId(result.userId) shouldBe true
            }
        }

        context("이전에 소셜 로그인을 통해 가입한 적이 있으면") {

            val userSocialLoginInfoFixture: UserSocialLoginInfo = OAuth2UserInfoTestFixture.create()
            val userSocialLoginInfoRepository = UserSocialLoginInfoRepositorySpy()
            userSocialLoginInfoRepository.save(userSocialLoginInfoFixture)

            it("해당 유저 아이디를 반환한다.") {
                // arrange
                val socialLoginPort = SocialLoginPortStub()
                val userRepository = UserRepositorySpy()

                val sut = SocialLoginUser(
                    oidcPort = socialLoginPort,
                    userRepository = userRepository,
                    socialLoginInfoRepository = userSocialLoginInfoRepository
                )

                // act
                val command = SocialLoginUserUseCase.Command("test", OAuth2Provider.KAKAO)
                val result: SocialLoginUserUseCase.Result = sut.invoke(command)

                // assert
                check(result is SocialLoginUserUseCase.Result.Success)
                result.userId shouldBe userSocialLoginInfoFixture.userId
            }
        }
    }

})
