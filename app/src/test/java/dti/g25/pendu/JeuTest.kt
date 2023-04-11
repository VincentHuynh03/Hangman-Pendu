package dti.g25.pendu.modele


import org.junit.Assert.assertArrayEquals
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class JeuTest {


    @Test
        fun `Étant donné le mot à deviner est PENDU, lorsqu'on trouve 4 lettres correctes, on obtient 4 points`(){
            val jeu = Jeu(arrayOf("PENDU"))
            jeu.essayerUneLettre('P')
            jeu.essayerUneLettre('E')
             jeu.essayerUneLettre('E')
            jeu.essayerUneLettre('s')

            jeu.essayerUneLettre('N')
             jeu.essayerUneLettre('D')
            assertEquals(4, jeu.pointage)
        }




    @Test
    fun `Étant donné le mot à deviner est CHAT, lorsqu'on trouve une lettre parmi le mot qui n'as pas été essayés, on obtient 1 point`() {
        val listeDeMots = arrayOf("CHAT", "CHIEN", "OISEAU")
        val jeu = Jeu(listeDeMots)
        jeu.motÀDeviner = "CHAT"
        val lettre = 'C'
        val resultat = jeu.essayerUneLettre(lettre)
        assertTrue(resultat)
        assertTrue(lettre in jeu.lettresEssayées)
        assertEquals(1, jeu.pointage)
    }

    @Test
    fun `Étant donné le mot à deviner est CHAT, lorsqu'on essaie des lettres qui ne font pas partie du mot, on obtient 3 erreurs`() {
        val listeDeMots = arrayOf("CHAT")
        val jeu = Jeu(listeDeMots)
        val lettres = "zxy"
        lettres.forEach { jeu.essayerUneLettre(it) }
        assertEquals(3, jeu.nbErreurs)
    }

}








