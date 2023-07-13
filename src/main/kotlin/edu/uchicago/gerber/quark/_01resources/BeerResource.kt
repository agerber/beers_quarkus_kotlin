package edu.uchicago.gerber.quarkus._01resources


import edu.uchicago.gerber.quarkus._02services.BeerService
import edu.uchicago.gerber.quarkus._04models.Beer
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import java.lang.Exception


@Path("/beers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BeerResource {

    @Inject
    lateinit var beerService: BeerService

    val TOTAL_PER_PAGE = 10

    //equivalent to CREATE
    @POST
    fun create(beer: Beer): Beer {
        beerService.create(beer)
        //if successful, return it
        return beer
    }


    //equivalent to READ

    @GET
    fun readAll(): List<Beer>{
        return beerService.readAll()
    }

    @GET
    @Path("{id}")
    fun readById(@PathParam("id") id: String): Beer {
        return beerService.readById(id)

    }

    @GET
    @Path("/paged/{page}")
    fun paged(@PathParam("page") page: Int): List<Beer> {
        //this is lazy.
        val pagedBeers: PanacheQuery<Beer>? = beerService.findAll()
        return pagedBeers?.page(page, TOTAL_PER_PAGE)?.list() ?: throw Exception("Paged query returned null")


    }

    //equivalent to UPDATE

    @PUT
    fun update(updatedBeer: Beer): Beer {
        beerService.update(updatedBeer)
        //if successful, return it
        return updatedBeer
    }


    //equivalent to DELETE

    @DELETE
    @Path("/{id}")
    fun deleteById(@PathParam("id")id: String): Beer{
        val beer = beerService.readById(id)
        beerService.deleteById(id)
        //if successful, return the deleted beer
        return beer
    }

    @DELETE
    fun deleteAll():List<Beer>{
        beerService.deleteAll()
        //if successful, return empty list
        return emptyList()
    }



}
