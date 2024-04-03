package spera

import org.apache.commons.csv.CSVFormat


fun String.spera(vararg number: Int): List<String> {
    return this.split(";").toList().filterIndexed { value, _ -> number.contains(value)}
}

fun String.speraReverse(vararg number: Int): List<String> {
    return this.split(";").toList().filterIndexed { value, _ -> !number.contains(value)}
}


private val baseCsvFormat = CSVFormat.Builder.create(CSVFormat.DEFAULT)
    .setDelimiter(';')
    .setTrim(true)
    .setTrailingDelimiter(true)
    .setAllowMissingColumnNames(true)
    .setSkipHeaderRecord(true)

private val pprunnedBaseHeaders = arrayOf(
    "date", "job", "program", "start", "end", "run", "wait", "stop", "err_stop",
    "atchng", "atmeas", "atclean", "atc", "hits", "holes", "mserr", "man", "aut",
    "m1", "m2", "m3", "m4", "m5", "m6", "m7", "m8", "m9", "m10",
    "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12", "s13", "s14", "s15", "s16",
    "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "b10", "b11", "b12", "b13", "b14", "b15", "b16",
    "hits 1", "hits 2", "hits 3", "hits 4", "hits 5", "hits 6", "hits 7", "hits 8", "hits 9", "hits10",
    "hits11", "hits12", "hits13", "hits14", "hits15", "hits16",
    "rout1", "rout2", "rout3", "rout4", "rout5", "rout6", "rout7", "rout8", "rout9", "rout10",
    "rout11", "rout12", "rout13", "rout14", "rout15", "rout16"
)

val pprunnedBaseHeaderNb = pprunnedBaseHeaders.size
val pprunnedCsvFormat = baseCsvFormat.setHeader(*pprunnedBaseHeaders).build()
