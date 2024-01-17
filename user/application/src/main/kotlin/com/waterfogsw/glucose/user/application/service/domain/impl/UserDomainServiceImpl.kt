package com.waterfogsw.glucose.user.application.service.domain.impl

import com.waterfogsw.glucose.common.support.exception.CustomException
import com.waterfogsw.glucose.common.support.vo.Email
import com.waterfogsw.glucose.common.support.vo.URL
import com.waterfogsw.glucose.user.application.port.outbound.UserRepository
import com.waterfogsw.glucose.user.application.service.domain.UserDomainService
import com.waterfogsw.glucose.user.application.service.domain.exception.UserException
import com.waterfogsw.glucose.user.domain.entity.User
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
