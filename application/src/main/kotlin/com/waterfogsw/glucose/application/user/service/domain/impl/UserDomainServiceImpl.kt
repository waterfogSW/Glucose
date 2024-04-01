package com.waterfogsw.glucose.application.user.service.domain.impl

import com.waterfogsw.glucose.support.common.exception.CustomException
import com.waterfogsw.glucose.support.common.vo.Email
import com.waterfogsw.glucose.support.common.vo.URL
import com.waterfogsw.glucose.application.user.port.outbound.UserRepository
import com.waterfogsw.glucose.application.user.service.domain.UserDomainService
import com.waterfogsw.glucose.application.user.service.domain.exception.UserException
import com.waterfogsw.glucose.domain.user.entity.User
import org.springframework.stereotype.Service
import java.util.*

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

    override fun getById(id: UUID): User {
        return userRepository.findById(id)
            ?: throw CustomException(
                type = UserException.USER_NOT_FOUND,
                message = "유저를 찾을 수 없습니다. id: $id"
            )
    }
}
