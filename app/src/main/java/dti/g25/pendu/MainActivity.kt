package dti.g25.pendu

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dti.g25.pendu.presentateur.Présentateur


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val présentateur = Présentateur(this)


    val btnLettres = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')

    val fruits = arrayOf("apple", "banana", "orange", "grape", "strawberry")

    val couleurVert = 0xFF00FF00.toInt()
    val couleurBleu= 0xFF42DBEF.toInt()
    val couleurGris = 0xFF808080.toInt()

    lateinit var tvScore: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in btnLettres.indices) {
            val resID = resources.getIdentifier("btn${btnLettres[i]}", "id", packageName)
            val button = findViewById<Button>(resID)
            button.setTag(btnLettres[i])
            button.setOnClickListener(this)
        }

        var btnRecommencer = findViewById( R.id.btnRecommencer ) as Button
        btnRecommencer.setOnClickListener { présentateur.démarrer(fruits) }
        tvScore = findViewById( R.id.tvScore )
        présentateur.démarrer(fruits)
    }

    /**
     * Au click d'un boutton, la lettre correspondant est associé est enregistrer dans la méthode sélectionnerLettre()
     */
    override fun onClick(v: View) {
        if (v is Button) {
            val lettre = v.text.toString()[0]
            présentateur.sélectionnerLettre(lettre)
            val etatLettres = présentateur.ÉtatDuMot()
            couleurChange(v, etatLettres)
            v.isEnabled = false
        }
    }


     fun afficherEtatLettres(etat: String) {
        val tv = findViewById<TextView>(R.id.tvMot)
        tv.text = etat
    }


    /**
     * Affiche le score actuel
     */
    fun afficherScore(score: Int) {
      tvScore.text = score.toString()
    }

    fun couleurChange(button: Button, etat: String) {
        if (etat.contains(button.text.toString()[0], ignoreCase = true)) {
            button.setBackgroundColor(couleurVert)
        } else {
            button.setBackgroundColor(couleurGris)
        }
    }


    fun couleurRéinitialiser(){
        for (i in btnLettres.indices) {
            val resID = resources.getIdentifier("btn${btnLettres[i]}", "id", packageName)
            val button = findViewById<Button>(resID)
            button.setTag(btnLettres[i])
            button.setBackgroundColor(couleurBleu)
            button.isEnabled = true
            button.setOnClickListener(this)
        }
    }
}


