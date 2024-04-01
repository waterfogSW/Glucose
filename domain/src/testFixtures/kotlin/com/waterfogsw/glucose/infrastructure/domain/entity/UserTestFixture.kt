package com.waterfogsw.glucose.infrastructure.domain.entity

import com.waterfogsw.glucose.domain.user.entity.User
import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import java.util.*

object UserTestFixture {

    fun create(
        id: UUID = UUID.randomUUID(),
        name: String = "name",
        email: Email = Email("test@test.com"),
        picture: URL? = URL("https://test.com/test.png"),
    ): User {
        return User(
            id = id,
            name = name,
            email = email,
            picture = picture,
        )
    }
}
