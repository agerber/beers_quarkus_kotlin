package edu.uchicago.gerber.quark

import edu.uchicago.gerber.quark._04models.Faked
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class BeerResourceTest {

    //https://quarkus.io/guides/getting-started-testing

    //CREATE

    //READ

    @Test
    fun testGetBeer() {

        given()
            .`when`().get("/beers/{id}", Faked.FAKE_ID)
        .then()
            .statusCode(200)
            .body("id", `is`(Faked.FAKE_ID));

    }
    @Test
    fun testGetBeers() {
        given()
            .`when`().get("/beers")
        .then()
            .statusCode(200)

    }

    //UPDATE

    //DELETE













}
