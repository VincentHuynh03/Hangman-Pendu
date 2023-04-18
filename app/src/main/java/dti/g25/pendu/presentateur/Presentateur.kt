package dti.g25.pendu.presentateur

import androidx.core.content.ContextCompat
import dti.g25.pendu.MainActivity
import dti.g25.pendu.R
import dti.g25.pendu.modele.Jeu

class Présentateur (var vue: MainActivity) {

    private lateinit var jeu: Jeu



    fun sélectionnerLettre(lettre: Char) {
        if (jeu.essayerUneLettre(lettre)) {
            ÉtatDuMot()
            vue.afficherScore(jeu.pointage)
            if (jeu.estRéussi()) {
                vue.AfficherGagner(jeu.motÀDeviner)
            }
        } else {
            if (jeu.nbErreurs < 6) {
                when (jeu.nbErreurs) {
                    1 -> vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus2)!!)
                    2 -> vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus3)!!)
                    3 -> vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus4)!!)
                    4 -> vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus5)!!)
                    5 -> vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus6)!!)
                }
            } else {
                vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus7)!!)
                vue.disableButtons()
                vue.AfficherPerdu(jeu.motÀDeviner)

            }

            }
        }



    fun démarrer(listeDeMots: Array<String>) {
        jeu = Jeu(listeDeMots)
        jeu.réinitialiser()
        vue.afficherScore(jeu.pointage)
        ÉtatDuMot()
        vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus1)!!)
    }


    fun réinitialiser(listeDeMots: Array<String>) {
        jeu = Jeu(listeDeMots)
        jeu.réinitialiser()
        vue.couleurRéinitialiser()
        vue.afficherScore(jeu.pointage)
        ÉtatDuMot()
        vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus1)!!)
    }
    fun ÉtatDuMot(): String {
        val etat = jeu.étatLettres()
        vue.afficherEtatLettres(etat)
        vue.afficherScore(jeu.pointage)
        return etat
    }






}