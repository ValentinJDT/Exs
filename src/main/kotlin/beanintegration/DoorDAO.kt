package beanintegration

data class DoorDAO(
    private val doors: MutableList<Door> = mutableListOf()
) {

    fun addDoor(door: Door) {
        doors.add(door)
    }

    fun getDoors(): List<Door> {
        return doors
    }

    fun getDoorByName(name: String): Door? {
        return doors.find { it.name == name }
    }

    fun removeDoorByName(name: String): Boolean {
        val door = doors.find { it.name == name }
        return doors.remove(door)
    }

    fun openDoorByName(name: String): Door? {
        return changeDoorStateByName(name, true)
    }

    fun closeDoorByName(name: String): Door? {
        return changeDoorStateByName(name, false)
    }

    fun changeDoorStateByName(name: String, open: Boolean): Door? {
        val door = doors.find { it.name == name }

        val index = doors.indexOfFirst { it.name == name }


        val newDoor = door?.let {
            doors[index] = it.copy(opened = open)

            doors[index]
        }

        return newDoor ?: door
    }

    fun doorExists(name: String): Boolean {
        return doors.any { it.name == name }
    }

}
