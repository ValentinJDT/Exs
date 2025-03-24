package test.target

import mutatorlocal.PseudoModel
import mutatorlocal.daoImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import muator.Person
import muator.PersonDAO
import muator.PersonModel
import org.bson.types.ObjectId
import test.*
import java.util.Random

class MutatorTest: TestClass() {

    override fun beans(): Map<String, Any> = mapOf(PersonDAO().toBean())

    @Test
    fun `insert value`(@Qualifier("personDao") personDAO: PersonDAO) {

        runBlocking<Unit> {
            personDAO.addPerson(Person(
                firstName = "John",
                lastName = "Doe",
                age = Random().nextInt(28)
            ))
        }
    }

    @Test
    fun `set update class`(@Qualifier("personDao") personDAO: PersonDAO) {

        runBlocking {
            val person = PersonModel(personDAO, ObjectId("660d284326387b03230e92fe"))

            println("Name ${person.firstName}")

            person.mutate {
                firstName = "Johnord"
                age = 14
            }

            println("Name ${person.firstName} and ${person.age} years old")

        }

    }


    @Test
    fun `get set out`() {
        val myObject = PseudoModel(daoImpl)

        runBlocking {
            launch {
                myObject.mutate {
                    value = "Other ${daoImpl.getTimestamp()}"
                }
            }
        }

        assertNotEq("Initial value", myObject.value)
    }

}
