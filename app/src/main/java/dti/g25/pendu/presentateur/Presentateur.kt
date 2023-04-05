package dti.g25.pendu.presentateur

import android.util.Log
import dti.g25.pendu.MainActivity
import dti.g25.pendu.modele.Jeu

class Présentateur (var vue: MainActivity){

    private lateinit var jeu: Jeu


    fun sélectionnerLettre(lettre: Char) {
        if (jeu.essayerUneLettre(lettre)) {
            vue.afficherScore(jeu.pointage)
        }
        }


    fun démarrer(listeDeMots: Array<String>) {

        jeu = Jeu(listeDeMots)
        vue.afficherScore(jeu.pointage)
        vue.afficherEtatLettres(jeu.motÀDeviner)

    }
}