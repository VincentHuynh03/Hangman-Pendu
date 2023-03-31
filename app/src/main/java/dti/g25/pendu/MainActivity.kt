package dti.g25.pendu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.TextView

import dti.g25.pendu.presentateur.Présentateur


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val présentateur = Présentateur(this)


    val letters = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')

    val fruits = arrayOf("apple", "banana", "orange", "grape", "strawberry")

    /**
     * Couleur vert
     */
    val couleurCorrecte = 0xFF00FF00.toInt()

    /**
     * Couleur gris
     */
    val couleurEsssaye = 0xFF808080.toInt()

    // La zone de texte contenant le score
    lateinit var tvScore: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        for (i in letters.indices) {
            val resID = resources.getIdentifier("btn${letters[i]}", "id", packageName)
            val button = findViewById<Button>(resID)
            button.setOnClickListener { onClick(button) }
        }


        tvScore = findViewById( R.id.tvScore )
        présentateur.démarrer(fruits)
    }

    /**
     * Au click d'un boutton, la lettre correspondant est associé est enregistrer dans la méthode sélectionnerLettre()
     */
    override fun onClick(v: View) {
        if (v is Button) {
            val lettre = v.text.toString().single()
            présentateur.sélectionnerLettre(lettre)
        }
    }

    /**
     *Change la couleur du bouton quand elle est cliqué
     * Si Correcte, change en vert.
     * Si Erreur, change en gris.
     */
     fun afficherEtatLettres(etat: String) {
        val tv = findViewById<TextView>(R.id.tvMot)
        tv.text = etat
        for (i in letters.indices) {
            val resID = resources.getIdentifier("btn${letters[i]}", "id", packageName)
            val button = findViewById<Button>(resID)

            button.setOnClickListener {
                if (etat.contains(letters[i], ignoreCase = true)) {
                    button.setBackgroundColor(couleurCorrecte)
                } else {
                    button.setBackgroundColor(couleurEsssaye)
                }
            }
        }

    }


    /**
     * Affiche le score actuel
     */
    fun afficherScore(score: Int) {

        tvScore.text = score.toString()
    }

}