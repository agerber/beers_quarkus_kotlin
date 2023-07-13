package edu.uchicago.gerber.quark._03repositories

import edu.uchicago.gerber.quark._04models.Faked
import edu.uchicago.gerber.quarkus._04models.Beer
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.enterprise.context.ApplicationScoped

import org.bson.types.ObjectId

@ApplicationScoped
class SomeBeerRepository: BeerRepoInterface {


    //CREATE

    override fun _create(beer: Beer){
        //do nothing
    }

    override fun _create(beers: List<Beer>){
        //do nothing
    }
    //READ
    override fun _readById(id:String): Beer {
      return Faked.genRawEntity()
    }

    //stream all
    override fun _readAll(): List<Beer> {
        return  Faked.genFakerBeers(50)
    }

    //UPDATE
    //update(updateBeer)

    override fun _update(updatedBeer: Beer) {
      //do nothing

    }

    //DELETE
    //delete(id)

    override fun _deleteById(id:String){
        //do nothing
    }

    override fun _deleteAll(){
        //do nothing
    }


    //COUNT
    override fun _count() : Long{
        return 100L
    }

    override fun _findAll(): PanacheQuery<Beer>? {
        return null
    }




}
