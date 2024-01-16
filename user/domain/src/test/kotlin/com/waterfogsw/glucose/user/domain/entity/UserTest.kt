package com.waterfogsw.glucose.user.domain.entity

import com.waterfogsw.glucose.common.support.vo.Email
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class UserTest : DescribeSpec({

    describe("User.create()") {
        it("User를 생성한다.") {
            val user: User = User.create(
                name = "test",
                email = Email("test@naver.com"),
            )

            user.id shouldNotBe null
        }
    }
})
