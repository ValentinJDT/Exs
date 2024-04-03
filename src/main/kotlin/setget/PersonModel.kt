package setget

import kotlinx.coroutines.runBlocking
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

data class Person(
    @BsonId
    val id: ObjectId = ObjectId(),
    @BsonProperty("first_name")
    val firstName: String,
    @BsonProperty("last_name")
    val lastName: String,
    val age: Int?
)


class PersonModel(val personDAO: PersonDAO, val id: ObjectId): CanMutate<PersonModel.PersonMutator> {

    val firstName: String
        get() = runBlocking {
            personDAO.getPerson(id.toString())?.firstName ?: "" // from cache ?
        }

    val lastName: String
        get() = runBlocking {
                personDAO.getPerson(id.toString())?.lastName ?: "" // from cache ?
            }

    val age: Int
        get() = runBlocking {
            personDAO.getPerson(id.toString())?.age ?: 0 // from cache ?
        }

    override suspend fun mutate(update: Boolean, block: suspend PersonMutator.() -> Unit) {
        val mutator = object : PersonMutator {
            override var id: ObjectId = this@PersonModel.id
            override var firstName = this@PersonModel.firstName
            override var lastName: String = this@PersonModel.lastName
            override var age: Int? = this@PersonModel.age
        }

        mutator.block()

        if(update) {
            personDAO.update(this@PersonModel.id, mutator)
        }
    }

    interface PersonMutator {
        var id: ObjectId
        var firstName: String
        var lastName: String
        var age: Int?
    }

}
