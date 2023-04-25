package dti.g25.pendu.presentateur

import android.app.AlertDialog
import androidx.core.content.ContextCompat
import dti.g25.pendu.MainActivity
import dti.g25.pendu.R
import dti.g25.pendu.modele.Jeu

class Présentateur (var vue: MainActivity) {

    private lateinit var jeu: Jeu

    /**
     * Essaie la lettre donnée par l'utilisateur pour le mot à deviner.
     * Si la lettre est correcte, met à jour l'état du mot et le score du jeu,
     * puis vérifie si le jeu est terminé (le mot a été trouvé).
     * Si la lettre est incorrecte, affiche l'image correspondant au nombre d'erreurs et
     * vérifie si le jeu est terminé (toutes les tentatives ont été utilisées).
     *
     * @param lettre la lettre entrée par l'utilisateur.
     */
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
                vue.désactiverBouttons()
                vue.AfficherPerdu(jeu.motÀDeviner)

            }

            }
        }

    /**
     * Initialise l'objet Jeu avec la liste de mots donnée en paramètre, réinitialise le jeu,
     * met à jour l'affichage du score et l'état du mot, et affiche l'image de départ.
     *
     * @param listeDeMots la liste de mots utilisée pour le jeu.
     */
        fun démarrer(listeDeMots: Array<String>) {
        jeu = Jeu(listeDeMots)
        jeu.réinitialiser()
        vue.afficherScore(jeu.pointage)
        ÉtatDuMot()
        vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus1)!!)
    }


    /**
     * Affiche une boîte de dialogue de confirmation et réinitialise le jeu si l'utilisateur confirme.
     *
     * @param listeDeMots la liste de mots utilisée pour le jeu.
     */
    fun réinitialiser(listeDeMots: Array<String>) {
        val builder = AlertDialog.Builder(vue)
        builder.setTitle("Confirmation")
        builder.setMessage("Voulez-vous recommencer?")
        builder.setPositiveButton("Recommencer") { _, _ ->
            jeu = Jeu(listeDeMots)
            jeu.réinitialiser()
            vue.couleurRéinitialiser()
            vue.afficherScore(jeu.pointage)
            ÉtatDuMot()
            vue.afficherImage(ContextCompat.getDrawable(vue, R.drawable.amongus1)!!)
        }
        builder.setNegativeButton("Annuler") { _, _ -> }
        builder.show()
    }


    /**
     * Met à jour l'état du mot, l'affichage du score et retourne l'état du mot.
     *
     * @return l'état du mot.
     */
    fun ÉtatDuMot(): String {
        val état = jeu.étatLettres()
        vue.afficherÉtatLettres(état)
        vue.afficherScore(jeu.pointage)
        return état
    }

}