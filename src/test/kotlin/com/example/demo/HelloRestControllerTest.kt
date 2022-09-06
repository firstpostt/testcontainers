package com.example.demo

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it-test")
@AutoConfigureWireMock(port = 1234)
@RunWith(SpringRunner::class)
@Testcontainers
class HelloRestControllerTest {
    @LocalServerPort
    final val portNumber = 0

    val baseUrl = "http://localhost:$portNumber"

    companion object {
        @Container
        var postgreSQL: PostgreSQLContainer<*> = PostgreSQLContainer<Nothing>("postgres:latest")

        @DynamicPropertySource
        fun postgreSQLProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.driver-class-name") { postgreSQL.driverClassName }
            registry.add("spring.datasource.username") { postgreSQL.username }
            registry.add("spring.datasource.password") { postgreSQL.password }
        }
    }

    @Test
    fun test() {
        val helloResponse = given()
            .contentType(ContentType.JSON)
            .get("$baseUrl$portNumber/hello")
            .andReturn()
        println(helloResponse.body)
    }
}

/*class CustomPostgreSQLContainer : PostgreSQLContainer<Nothing>("postgres:latest") {

    override fun getJdbcUrl(): String {
        //jdbc:postgresql://localhost/demo?stringtype=unspecified
        val additionalUrlParams = constructUrlParameters("?", "&")
        return ("jdbc:postgresql://localhost" + ":" + getMappedPort(POSTGRESQL_PORT)
                + "/" + databaseName + additionalUrlParams)
    }
    override fun getDriverClassName(): String {
        return "org.postgresql.Driver"
    }
}*/

