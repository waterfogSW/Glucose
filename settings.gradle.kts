rootProject.name = "glucose-backend"

/**
 * support
 */
include(":support:common")
project(":support:common").projectDir = file("support/common")

include(":support:ulid")
project(":support:ulid").projectDir = file("support/ulid")

include(":support:jwt")
project(":support:jwt").projectDir = file("support/jwt")

/**
 * user
 */
include(":user:domain")
project(":user:domain").projectDir = file("user/domain")

include(":user:application")
project(":user:application").projectDir = file("user/application")

include(":user:bootstrap:http")
project(":user:bootstrap:http").projectDir = file("user/bootstrap/http")

include(":user:infrastructure:persistence")
project(":user:infrastructure:persistence").projectDir = file("user/infrastructure/persistence")

include(":user:infrastructure:redis")
project(":user:infrastructure:redis").projectDir = file("user/infrastructure/redis")

include(":user:infrastructure:client")
project(":user:infrastructure:client").projectDir = file("user/infrastructure/client")
