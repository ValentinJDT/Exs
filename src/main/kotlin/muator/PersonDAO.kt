package muator

import com.mongodb.MongoClientSettings
import com.mongodb.MongoException
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Updates.set
import com.mongodb.client.result.InsertOneResult
import com.mongodb.kotlin.client.coroutine.ClientSession
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.bson.BsonInt64
import org.bson.Document
import org.bson.UuidRepresentation
import org.bson.types.ObjectId

class PersonDAO {

    private var setupConnection: Pair<ClientSession, MongoDatabase>? = null
        set(value) {
            if (field == null) {
                field = value
            } else {
                throw IllegalStateException("Database is already connected")
            }
        }

    var database: MongoDatabase? = null
        get() = setupConnection?.second

    init {
        runBlocking {
            setupConnection = setupConnection()

            MongoClientSettings.builder()
                .uuidRepresentation(UuidRepresentation.JAVA_LEGACY)
                .build()
        }
    }


    suspend fun update(id: ObjectId, person: PersonModel.PersonMutator) {
        val collection = database?.getCollection<PersonModel.PersonMutator>(collectionName = "person")

        val queryParams = eq("_id", id.toString())

        collection?.updateOne(queryParams, listOf(set("first_name", person.firstName), set("last_name", person.lastName), set("age", person.age)))
    }

    suspend fun addPerson(person: Person): InsertOneResult? {
        val collection = database?.getCollection<Person>(collectionName = "person")
        return collection?.insertOne(person)
    }

    suspend fun getPerson(id: String) : Person? {
        val collection = database?.getCollection<Person>(collectionName = "person")

        val queryParams = eq("_id", ObjectId(id))

        return collection?.find(queryParams)?.firstOrNull()
    }

}

suspend fun setupConnection(
    databaseName: String = "training_db",
    connectionEnvVariable: String = "MONGODB_URI"
): Pair<ClientSession, MongoDatabase>? {
    val connectString = if (System.getenv(connectionEnvVariable) != null) {
        System.getenv(connectionEnvVariable)
    } else {
        "mongodb+srv://<username>:<password>@cluster0.sq3aiau.mongodb.net/?retryWrites=true&w=majority"
    }

    val client = MongoClient.create(connectionString = connectString)
    val database = client.getDatabase(databaseName = databaseName)

    return try {
        // Send a ping to confirm a successful connection
        val command = Document("ping", BsonInt64(1))
        database.runCommand(command)
        println("Pinged your deployment. You successfully connected to MongoDB!")
        println(connectString)
        client.startSession() to database
    } catch (me: MongoException) {
        System.err.println(me)
        null
    }
}
