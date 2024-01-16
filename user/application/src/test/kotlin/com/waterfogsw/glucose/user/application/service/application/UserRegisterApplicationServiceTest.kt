package com.waterfogsw.glucose.user.application.service.application

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.user.application.port.UserSocialLoginInfoRepositorySpy
import com.waterfogsw.glucose.user.application.port.UserRepositorySpy
import com.waterfogsw.glucose.user.application.port.inbound.UserRegisterUseCase
import com.waterfogsw.glucose.user.application.service.applicaiton.UserRegisterApplicationService
import com.waterfogsw.glucose.user.domain.enums.Provider
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UserRegisterApplicationServiceTest: DescribeSpec({

    describe("유저 가입 유스케이스") {


        it("새로운 유저와 인증정보를 저장하고 유저 아이디를 반환한다.") {
            // arrange
            val userRepository = UserRepositorySpy()
            val userSocialLoginInfoRepository = UserSocialLoginInfoRepositorySpy()
            val sut = UserRegisterApplicationService(
                userRepository = userRepository,
                userSocialLoginInfoRepository = userSocialLoginInfoRepository
            )

            // act
            val command = UserRegisterUseCase.Command(
                email = Email("test@test.com"),
                name = "test",
                picture = null,
                provider = Provider.KAKAO,
            )
            val result: UserRegisterUseCase.Result = sut.invoke(command)

            // assert
            check(result is UserRegisterUseCase.Result.Success)
            userRepository.existsById(result.userId) shouldBe true
            userSocialLoginInfoRepository.existsByUserId(result.userId) shouldBe true
        }
    }
})
