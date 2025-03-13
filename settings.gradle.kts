rootProject.name = "SpringGuides"
include("rest-apis")
include("misc")
include("io")
include("data-access")
include("security")
include("messaging")
include("ops")
include("batch-processing")
include("web")
include("caching")
include("microservices")
include("testing")
include("documentation")
include("cloud-platform")
include("streaming")
include("graphql")
include("rest-apis:tut-rest")
findProject(":rest-apis:tut-rest")?.name = "tut-rest"
include("rest-apis:gs-rest-service")
findProject(":rest-apis:gs-rest-service")?.name = "gs-rest-service"
include("rest-apis:gs-consuming-rest")
findProject(":rest-apis:gs-consuming-rest")?.name = "gs-consuming-rest"
include("rest-apis:gs-rest-hateoas")
findProject(":rest-apis:gs-rest-hateoas")?.name = "gs-rest-hateoas"
include("rest-apis:gs-spring-boot")
findProject(":rest-apis:gs-spring-boot")?.name = "gs-spring-boot"
include("rest-apis:gs-accessing-neo4j-data-rest")
findProject(":rest-apis:gs-accessing-neo4j-data-rest")?.name = "gs-accessing-neo4j-data-rest"
include("rest-apis:gs-accessing-gemfire-data-rest")
findProject(":rest-apis:gs-accessing-gemfire-data-rest")?.name = "gs-accessing-gemfire-data-rest"
include("rest-apis:gs-reactive-rest-service")
findProject(":rest-apis:gs-reactive-rest-service")?.name = "gs-reactive-rest-service"
include("rest-apis:tut-rest:nonrest")
findProject(":rest-apis:tut-rest:nonrest")?.name = "nonrest"
include("rest-apis:tut-rest:rest")
findProject(":rest-apis:tut-rest:rest")?.name = "rest"
include("rest-apis:tut-rest:evolution")
findProject(":rest-apis:tut-rest:evolution")?.name = "evolution"
include("rest-apis:tut-rest:links")
findProject(":rest-apis:tut-rest:links")?.name = "links"
