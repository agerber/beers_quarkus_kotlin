package edu.uchicago.gerber.quarkus._03repositories

import com.github.javafaker.Faker
import edu.uchicago.gerber.quark._03repositories.BeerRepoInterface
import edu.uchicago.gerber.quark._04models.Faked
import edu.uchicago.gerber.quarkus._04models.Beer
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import io.quarkus.runtime.StartupEvent
import jakarta.annotation.PreDestroy
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes

import org.bson.types.ObjectId

@ApplicationScoped
class MongoBeerRepository: PanacheMongoRepository<Beer>, BeerRepoInterface {

    //this will get fired when the quarkus microservice starts
    fun onStart(@Observes ev: StartupEvent?) {


        if (count() == 0L){
            val list = mutableListOf<Beer>()
            repeat(23){ list.add(Faked.genRawEntity()) }
            persist(list)
            //rather than allowing MongoDB to generate the id for us, we add a single beer
            // with our own id which we will use for testing. (DO THIS FOR TESTING ONLY)
            persist(Faked.genTestBeer(Faked.FAKE_ID))
        }


    }



    //CREATE

    override fun _create(beer: Beer){
        this.persist(beer)
    }


    override fun _create(beers: List<Beer>){
        this.persist(beers)
    }
    //READ
    override fun _readById(id:String): Beer {
       val beerId = ObjectId(id)
        //findById will return null if not found, so use the elvis operator to throw.
        //These exceptions will be propagated automatically to the quarkus container
       return this.findById(beerId) ?: throw Exception("No beer with that ID")
    }


    override fun _readAll(): List<Beer> {
        return  this.listAll()
    }

    //UPDATE

    override fun _update(updatedBeer: Beer) {
       this.update(updatedBeer)

    }

    //DELETE


    override fun _deleteById(id:String){
      val beerId = ObjectId(id)
      this.deleteById(beerId)


    }


    override fun _deleteAll() {
        this.deleteAll()
    }


    //COUNT
    override fun _count() : Long{
        return this.count()
    }

    //this returns a lazy query object
    override fun _findAll(): PanacheQuery<Beer>? {
        return this.findAll()
    }






}
