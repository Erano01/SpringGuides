rootProject.name = "SpringGuides"

// Ana modüller
include("rest-apis", "misc", "io", "data-access", "security", "messaging", "ops",
    "batch-processing", "web", "caching", "microservices", "testing",
    "documentation", "cloud-platform", "streaming", "graphql")

// rest-apis multi-module projects
include("rest-apis:tut-rest", "rest-apis:gs-rest-service", "rest-apis:gs-consuming-rest",
    "rest-apis:gs-rest-hateoas", "rest-apis:gs-spring-boot",
    "rest-apis:gs-accessing-neo4j-data-rest", "rest-apis:gs-accessing-gemfire-data-rest",
    "rest-apis:gs-reactive-rest-service")

// tut-rest projects sub-modules
include("rest-apis:tut-rest:nonrest", "rest-apis:tut-rest:rest",
    "rest-apis:tut-rest:evolution", "rest-apis:tut-rest:links")

//
include("security:gs-authenticating-ldap")
findProject(":security:gs-authenticating-ldap")?.name = "gs-authenticating-ldap"
include("security:gs-securing-web")
findProject(":security:gs-securing-web")?.name = "gs-securing-web"
include("security:gs-rest-service-cors")
findProject(":security:gs-rest-service-cors")?.name = "gs-rest-service-cors"
