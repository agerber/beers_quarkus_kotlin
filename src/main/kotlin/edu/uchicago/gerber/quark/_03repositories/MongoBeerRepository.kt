package edu.uchicago.gerber.quarkus._03repositories

import com.github.javafaker.Faker
import edu.uchicago.gerber.quarkus._04models.Beer
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes

import org.bson.types.ObjectId

@ApplicationScoped
class MongoBeerRepository: PanacheMongoRepository<Beer> {

    val faker = Faker()
    //this will get fired when the quarkus microservice starts
    fun onStart(@Observes ev: StartupEvent?) {

            val list = mutableListOf<Beer>()
            repeat(23){

                val fakerBeer = faker.beer()
                val beer = Beer()
                //we allow mongo to generate the id's for us
                //beer.id = ObjectId.get()
                beer.name = fakerBeer.name()
                beer.hop = fakerBeer.hop()
                beer.malt = fakerBeer.malt()
                beer.style = fakerBeer.style()
                beer.yeast = fakerBeer.yeast()
                list.add(beer)
            }
          persist(list)

    }






    //CREATE

     fun _create(beer: Beer): Beer{
        this.persist(beer)
        return beer
    }


     fun _create(beers: List<Beer>): List<Beer>{
        this.persist(beers)
         return  listAll()

    }
    //READ
     fun _readById(id:String): Beer {
       val beerId = ObjectId(id)
        //findById will return null if not found, so use the elvis operator to throw.
        //These exceptions will be propagated automatically to the quarkus container
       return this.findById(beerId) ?: throw Exception("No beer with that ID")
    }


     fun _readAll(): List<Beer> {
        return  this.listAll()
    }

    //UPDATE

     fun _update(updatedBeer: Beer): Beer {
       this.update(updatedBeer)
       return  updatedBeer

    }

    //DELETE


     fun _deleteById(id:String): Beer{
      val beerId = ObjectId(id)
      val beer = _readById(id)
      this.deleteById(beerId)
       //if successful, return deleted beer
      return beer

    }


     fun _deleteAll(): List<Beer>{
        this.deleteAll()
         return emptyList()
    }


    //COUNT
     fun _count() : Long{
        return this.count()
    }

    //this returns a lazy query object
     fun _findAll(): PanacheQuery<Beer>? {
        return this.findAll()
    }





}
