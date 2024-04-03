package generator

class MyCustomNameGenerator: Generator<Int, String> {

    override fun execute(value: Int): String {
        var output = "";
        for(i in 0..value) {
            output += Char(65 + i);
        }
        return output.split("").shuffled().joinToString("");
    }

}