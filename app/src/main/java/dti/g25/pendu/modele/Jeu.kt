package dti.g25.pendu.modele

import java.util.*
import kotlin.random.Random


class Jeu(val listeDeMots: Array<String>) {

    var lettresEssayées = CharArray(26)
    var motÀDeviner = ""
    var nbErreurs = 0

    var pointage: Int=0
        get() = motÀDeviner.count { it in lettresEssayées }

    /**
     * Constructeur qui reçoit un tableau de mots,
     * lance une exception s'il est vide, et initialise motÀDeviner avec un mot choisi
     * au hasard dans la liste.
     * @throws IllegalArgumentException si la liste de mots est vide
     */
    init {
        if (listeDeMots.isEmpty()) {
            throw IllegalArgumentException("Liste de mots vide")
        }
        motÀDeviner = listeDeMots.random().uppercase(Locale.ROOT)
    }

    /**
     * Essaye la lettre et vérifie si elle a été déja essayé avant.
     * Si elle est nouvelle, elle est stocké dans le tableau lettresEssayées qui remplace
     * la première place vide ( \u0000 )
     * @param lettre: lettre à essayer
     * @return true si la lettres est contenue dans le mot à deviner.
     */
    fun essayerUneLettre(lettre: Char): Boolean {

        val lettreEnMajuscule = lettre.uppercaseChar()
        if (lettreEnMajuscule in lettresEssayées) {
            return false
        }
        val resultat = motÀDeviner.contains(lettreEnMajuscule)

        if (resultat) {
            lettresEssayées[lettresEssayées.indexOf('\u0000')] = lettreEnMajuscule
            pointage++
        } else {
            nbErreurs++
        }
        return resultat

    }

    /**
     * Indique si le joueur a réussi à deviner le mot.
     * @return true si le pointage est égal au nombre de lettres du mot
     */
    fun estRéussi(): Boolean {
        return pointage == motÀDeviner.length
    }

    /**
     * Réinitialise le jeu
     */
    fun réinitialiser() {
        lettresEssayées = CharArray(26)
        motÀDeviner = listeDeMots.random().uppercase(Locale.ROOT)
    }

    /**
     * Retourne un tableau de l'état des lettres du mot à deviner.
     * Remplace les lettres non devinées par des underscores.
     * @return l'état des lettres du mot à deviner sous forme de chaîne de caractères
     */
    fun étatLettres(): String {
        return motÀDeviner.map { if (it in lettresEssayées) it else '_' }.joinToString(" ")
    }

}
