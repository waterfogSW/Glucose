rootProject.name = "glucose-backend"

/**
 * common
 */
include(":common:ulid")
project(":common:ulid").projectDir = file("common/ulid")

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
