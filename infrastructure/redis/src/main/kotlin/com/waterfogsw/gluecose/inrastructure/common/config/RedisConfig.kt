package com.waterfogsw.gluecose.inrastructure.common.config

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Configuration

@Configuration
class RedisConfig(
    private val redisProperties: RedisProperties
)
