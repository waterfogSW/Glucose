package com.waterfogsw.glucose.user.application.service.domain.impl

import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.common.support.vo.URL
import com.waterfogsw.glucose.user.application.port.outbound.UserRepository
import com.waterfogsw.glucose.user.application.service.domain.UserDomainService
import com.waterfogsw.glucose.user.domain.entity.User
import org.springframework.stereotype.Service

@Service
class UserDomainServiceImpl(
    private val userRepository: UserRepository,
) : UserDomainService {
    override fun create(
        name: String,
        email: Email,
        picture: URL?
    ): User {
        return User.create(
            name = name,
            email = email,
            picture = picture,
        ).also {
            userRepository.save(it)
        }
    }
}
