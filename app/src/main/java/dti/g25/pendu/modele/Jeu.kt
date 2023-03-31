package dti.g25.pendu.modele

import java.util.*
import kotlin.random.Random

class Jeu(val listeDeMots: Array<String>) {


    var lettresEssayées = CharArray(26)
    var motÀDeviner = ""

    /**
     *  pointage représente le nombre de caractères de la chaîne
    *  motÀDeviner qui ont été correctement devinés et qui sont
    *  inclus dans la liste des lettresEssayées.
     */

    val pointage: Int
        get() = motÀDeviner.count { it in lettresEssayées }

    /**
     * nbErreurs représente le nombre de caractère dans lettresEssayé qui ne font pas partie du mots à deviner
     */
    val nbErreurs: Int
        get() = lettresEssayées.count { it !in motÀDeviner }

    /**
     * Constructeur qui reçoit un tableau de mots,
     * lance une exception s'il est vide, et initialise motÀDeviner avec un mot choisi
     * au hasard dans la liste.
    */
    init {
        if (listeDeMots.isEmpty()) {
            throw IllegalArgumentException("Liste de mots vide")
        }
        motÀDeviner = listeDeMots[Random.nextInt(listeDeMots.size)].uppercase(Locale.ROOT)
    }


    /**
     * Essaye la lettre et vérifie si elle a été déja essayé avant.
     * Si elle est nouvelle, elle est stocké dans le tableau lettresEssayées qui remplace
     * la première place vide ( \u0000 )
     * @return true si la lettres est contenue dans le mot à deviner.
     */
    fun essayerUneLettre(lettre: Char): Boolean {

        val lettreEnMajuscule = lettre.uppercaseChar()
        if (lettreEnMajuscule in lettresEssayées) {
            return false
        }
        lettresEssayées[lettresEssayées.indexOf('\u0000')] = lettreEnMajuscule
        return motÀDeviner.contains(lettreEnMajuscule)

    }

    /**
     * Réussi quand le pointage est égale au nombre de lettres du mot
     */


    fun estRéussi(): Boolean {
        return pointage == motÀDeviner.length
    }

    /**
     * Réinitialise le jeu
     */

    fun réinitialiser() {
        lettresEssayées = CharArray(26)
        motÀDeviner = listeDeMots[Random.nextInt(listeDeMots.size)].uppercase(Locale.ROOT)
    }



    /**
     * Devrait retourner un tableau de l'état des lettres du mot à deviner.
     * Remplace les lettres non devinées par des underscores.
     */

    fun étatLettres(): CharArray {
        return motÀDeviner.map { if (it in lettresEssayées) it.uppercaseChar() else '_' }.toCharArray()
    }

}