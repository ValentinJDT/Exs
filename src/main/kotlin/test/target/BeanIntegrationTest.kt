package test.target

import beanintegration.Door
import beanintegration.DoorDAO
import test.*

class BeanIntegrationTest : TestClass() {

    override fun beans(): Map<String, Any> = mapOf(
        DoorDAO::class.java.name to DoorDAO(),
        "subDoorDao" to DoorDAO()
    )

    @Test(order = 1)
    fun `add door`(doorDAO: DoorDAO) {
        doorDAO.addDoor(Door("front door", "brown", "wood", false))
        assert(doorDAO.getDoors().isNotEmpty())
    }

    @Test(order = 3)
    fun `change door state`(doorDAO: DoorDAO) {
        val beforeDoor = doorDAO.getDoorByName("front door")
        val afterDoor = doorDAO.changeDoorStateByName("front door", true)

        assertNotEq(beforeDoor?.opened, afterDoor?.opened)
    }

    @Test(order = 2)
    fun `get door name and material`(doorDAO: DoorDAO) {
        doorDAO.getDoorByName("front door")?.also {
            assertEq(it.name, "front door")
            assertEq(it.material, "wood")
        } ?: assert(false)
    }

    @Test(order = 5)
    fun `delete door`(doorDAO: DoorDAO) {
        doorDAO.removeDoorByName("front door")
    }

    @Test(order = 4)
    fun `door existance verification`(doorDAO: DoorDAO) {
        val result = doorDAO.doorExists("front door")
        assertTrue(result)
    }

    @Test
    fun `add sub door`(doorDAO: DoorDAO, @Qualifier("subDoorDao") subDoorDao: DoorDAO) {
        subDoorDao.addDoor(Door("back door", "white", "wood", false))

        assertNotEq(doorDAO, subDoorDao)
    }

}
