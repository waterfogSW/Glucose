package com.waterfogsw.glucose.user.application.usecase

import com.waterfogsw.glucose.user.application.port.UserOAuthInfoRepositorySpy
import com.waterfogsw.glucose.user.application.port.UserRepositorySpy
import com.waterfogsw.glucose.user.domain.enums.Provider
import com.waterfogsw.glucose.user.domain.vo.Email
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UserRegisterTest: DescribeSpec({

    describe("유저 가입 유스케이스") {


        it("새로운 유저와 인증정보를 저장하고 유저 아이디를 반환한다.") {
            // arrange
            val userRepository = UserRepositorySpy()
            val userOAuthInfoRepository = UserOAuthInfoRepositorySpy()
            val sut = UserRegister(
                userRepository = userRepository,
                userOAuthInfoRepository = userOAuthInfoRepository
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
            userOAuthInfoRepository.existsByUserId(result.userId) shouldBe true
        }
    }
})
