rootProject.name = "user"

include("user-domain")
include("user-usecase")

// common
include(":common-ulid")
project(":common-ulid").projectDir = file("./user-common/ulid")

// boostrap
include(":user-bootstrap-http")
project(":user-bootstrap-http").projectDir = file("user-bootstrap/http")

// infrastructure
include(":user-infrastructure-persistence")
project(":user-infrastructure-persistence").projectDir = file("user-infrastructure/persistence")

include(":user-infrastructure-redis")
project(":user-infrastructure-redis").projectDir = file("user-infrastructure/redis")

include(":user-infrastructure-client")
project(":user-infrastructure-client").projectDir = file("user-infrastructure/client")
