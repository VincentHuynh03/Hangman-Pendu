package dti.g25.pendu.presentateur

import android.util.Log
import android.widget.Toast
import dti.g25.pendu.MainActivity
import dti.g25.pendu.modele.Jeu

class Présentateur (var vue: MainActivity) {

    private lateinit var jeu: Jeu



    fun sélectionnerLettre(lettre: Char) {
        if (jeu.essayerUneLettre(lettre)) {
            ÉtatDuMot()
            vue.afficherScore(jeu.pointage)
            if (jeu.estRéussi()) {
                Toast.makeText(vue, "Gagné!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun démarrer(listeDeMots: Array<String>) {
        jeu = Jeu(listeDeMots)
        jeu.réinitialiser()
        vue.couleurRéinitialiser()
        vue.afficherScore(jeu.pointage)
        ÉtatDuMot()

    }

    fun ÉtatDuMot(): String {
        val etat = jeu.étatLettres()
        vue.afficherEtatLettres(etat)
        vue.afficherScore(jeu.pointage)
        return etat
    }






}