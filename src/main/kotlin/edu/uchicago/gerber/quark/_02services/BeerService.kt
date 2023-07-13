package edu.uchicago.gerber.quarkus._02services



import com.fasterxml.jackson.databind.BeanDescription
import edu.uchicago.gerber.quarkus._03repositories.MongoBeerRepository
import edu.uchicago.gerber.quarkus._04models.Beer
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class BeerService  {

    @Inject
    lateinit var concreteRepository: MongoBeerRepository


    fun create(beer: Beer){
        concreteRepository._create(beer)
    }
    fun create(beers: List<Beer>){
        concreteRepository._create(beers)
    }
    fun readById(id:String): Beer{
      return  concreteRepository._readById(id)
    }
    fun readAll(): List<Beer>{
        return  concreteRepository._readAll()
    }
    fun update(updatedBeer: Beer){
        return  concreteRepository._update(updatedBeer)
    }
    fun deleteById(id:String){
        return  concreteRepository._deleteById(id)
    }
    fun deleteAll(){
        concreteRepository._deleteAll()
    }
    fun count() : Long{
       return concreteRepository._count()
    }
    fun findAll(): PanacheQuery<Beer>?{
        return concreteRepository._findAll()

    }



}
