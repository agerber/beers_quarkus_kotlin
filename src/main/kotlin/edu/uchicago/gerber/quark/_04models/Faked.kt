package edu.uchicago.gerber.quark._04models

import com.github.javafaker.Faker
import edu.uchicago.gerber.quarkus._04models.Beer
import org.bson.types.ObjectId

object Faked {
    //static prop
    val faker = Faker()
    val FAKE_ID = "5063114bd386d8fadbd6b004"

     fun genRawEntity(): Beer {

        val fakerBeer = faker.beer()
        val beer = Beer()
        //we allow mongo to generate the id's for us
        //beer.id = ObjectId.get()
        beer.name = fakerBeer.name()
        beer.hop = fakerBeer.hop()
        beer.malt = fakerBeer.malt()
        beer.style = fakerBeer.style()
        beer.yeast = fakerBeer.yeast()
        return beer
    }

    fun genFakerBeers(num: Int): List<Beer>{
        val list = mutableListOf<Beer>()
        repeat(num){ list.add(genRawEntity()) }
        return list
    }

    //use a string such as "5063114bd386d8fadbd6b004"
    fun genTestBeer(hash: String): Beer{
         val beer = genRawEntity()
         beer.id = ObjectId(hash)
        return  beer

    }

}
