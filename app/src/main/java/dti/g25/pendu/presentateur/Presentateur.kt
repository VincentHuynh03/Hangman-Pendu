package dti.g25.pendu.presentateur

import dti.g25.pendu.MainActivity
import dti.g25.pendu.modele.Jeu

class Présentateur (var vue: MainActivity){

    private lateinit var jeu: Jeu

    //But générale du méthode
   //  Vérifie si la lettre est dans le mot à deviner.
   //  Si oui, afficher le nouveau score du jeu et vérifie si le jeu est terminé
     // Si non, vérifie si le joueur n'a pas dépasser le nombre d'erreurs max

    // Pas vraiment complète. Needs more work + bug

    fun sélectionnerLettre(lettre: Char) {
        if (jeu.essayerUneLettre(lettre)) {
            vue.afficherScore(jeu.pointage)
            if (jeu.estRéussi()) {

            }
        } else {
            if (jeu.nbErreurs == 6) {

            }
        }
    }

    /**
     *Démarre le jeu
     */


    fun démarrer(listeDeMots: Array<String>) {

        jeu = Jeu(listeDeMots)
        vue.afficherScore( 0 )
        vue.afficherEtatLettres(jeu.motÀDeviner)


    }
}