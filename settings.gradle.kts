rootProject.name = "glucose"

/**
 * support
 */
include(":support:common")
project(":support:common").projectDir = file("support/common")

include(":support:jwt")
project(":support:jwt").projectDir = file("support/jwt")

/**
 * user
 */
include(":domain")
project(":domain").projectDir = file("domain")

include(":application")
project(":application").projectDir = file("application")

include(":bootstrap:http")
project(":bootstrap:http").projectDir = file("bootstrap/http")

include(":infrastructure:persistence")
project(":infrastructure:persistence").projectDir = file("infrastructure/persistence")

include(":infrastructure:redis")
project(":infrastructure:redis").projectDir = file("infrastructure/redis")

include(":infrastructure:client")
project(":infrastructure:client").projectDir = file("infrastructure/client")
