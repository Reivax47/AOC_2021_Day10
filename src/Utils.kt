import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Test an input ligne.
 * @return a Pair (Boolean True if corrupted, String : the illegal character or all the missing closing character if the line is not corrupted
 */
fun test_line(ligne: String): Pair<Boolean, String> {
    var ma_ligne = ligne
    val ouvrant = arrayOf("(", "[", "{", "<")
    val fermant = arrayOf(")", "]", "}", ">")
    var les_ouvertures = mutableListOf<String>()
    var erreur = false
    var celui_la = ""
    var les_manquant = ""
    while (!erreur && ma_ligne != "") {

        celui_la = ma_ligne.substring(0, 1)
        ma_ligne = ma_ligne.substring(1)
        if (ouvrant.contains(celui_la)) {
            les_ouvertures.add(celui_la)
        } else if (ouvrant.indexOf(les_ouvertures.last()) != fermant.indexOf(celui_la)){
            erreur = true
        } else {
            les_ouvertures.removeAt(les_ouvertures.size - 1)
        }
    }

    if (erreur) {
        return Pair(true, celui_la)
    } else {
        for (i in 0 until les_ouvertures.size) {
            les_manquant += (fermant[ouvrant.indexOf(les_ouvertures[les_ouvertures.size - 1 - i])])
        }
        return Pair(false, les_manquant)
    }

}
