package com.waterfogsw.glucose.support.common.util

import com.github.f4b6a3.uuid.UuidCreator
import java.util.*

object UuidUtil {

    fun createUuid(): UUID {
        return UuidCreator.getTimeOrderedEpoch()
    }

}
