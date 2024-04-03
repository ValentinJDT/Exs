package test.target

import spera.pprunnedBaseHeaderNb
import spera.pprunnedCsvFormat
import spera.spera
import spera.speraReverse
import test.Test
import test.TestClass

class SperaTest: TestClass() {


//    @Test
    fun `get deleted columns`() {

        val columns = "date    ;job                             ;program                         ;start;  end;     run;    wait;    stop;err_stop;  atchng;  atmeas; atclean; atc;  hits; holes;mserr;man;aut; m1; m2; m3; m4; m5; m6; m7; m8; m9;m10; s1; s2; s3; s4; s5; s6; s7; s8; s9;s10;s11;s12;s13;s14;s15;s16; b1; b2; b3; b4; b5; b6; b7; b8; b9;b10;b11;b12;b13;b14;b15;b16;hits 1;hits 2;hits 3;hits 4;hits 5;hits 6;hits 7;hits 8;hits 9;hits10;hits11;hits12;hits13;hits14;hits15;hits16; rout1; rout2; rout3; rout4; rout5; rout6; rout7; rout8; rout9;rout10;rout11;rout12;rout13;rout14;rout15;rout16".trim();
        val classic = columns.spera(1, 5, 6,7,8,9,10,11,12,15,16,
                17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32,
                33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48,
                49, 50, 51, 52, 53, 54, 55, 56,
                57, 58, 59, 60, 61, 62, 63, 64,
                65, 66, 67, 68, 69, 70, 71, 72,
                73, 74, 75, 76, 77, 78, 79, 80,
                81, 82, 83, 84, 85, 86, 87, 88,
                89, 90, 91, 94, 97, 100, 103,
                106, 109, 112, 115, 118, 121,
                124, 127, 130, 133, 136, 139,
                142, 145, 148, 151, 154, 157,
                160, 163, 166)

        val reverse = columns.speraReverse(1, 5, 6,7,8,9,10,11,12,15,16,
            17, 18, 19, 20, 21, 22, 23, 24,
            25, 26, 27, 28, 29, 30, 31, 32,
            33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48,
            49, 50, 51, 52, 53, 54, 55, 56,
            57, 58, 59, 60, 61, 62, 63, 64,
            65, 66, 67, 68, 69, 70, 71, 72,
            73, 74, 75, 76, 77, 78, 79, 80,
            81, 82, 83, 84, 85, 86, 87, 88,
            89, 90, 91, 94, 97, 100, 103,
            106, 109, 112, 115, 118, 121,
            124, 127, 130, 133, 136, 139,
            142, 145, 148, 151, 154, 157,
            160, 163, 166)


        println(classic)
        println(reverse)

    }

//    @Test
    fun `for`() {

        for(i in 1..25) {
            println("""
                        private const val DIAMETER_${i}_PROP = "diameter_${i}"
                        private const val DIAMETER_${i}_NUM_PROP = "diameter_${i}_amount"
            """.trimIndent())
        }
    }

    @Test
    fun `read csv file`() {
        val csvFile = "P:\\workfao\\Corentin\\Depot GIT POC 2020\\poc-tranche-3\\GIDOMA\\Database\\Percage\\fichier_percage\\MONO.0203\\pprunned.06"

        val records = pprunnedCsvFormat.parse(java.io.FileReader(csvFile)).records


        records.forEach {

            val unamedColumns = it.values().withIndex().filter { entry -> entry.index > pprunnedBaseHeaderNb-1 }.groupBy { entry -> (entry.index + 1) / 3 }.values.map { entry -> entry.map { it.value } }

//            print("Row : ")
//            println(unamedColumns)
       }

    }

}
