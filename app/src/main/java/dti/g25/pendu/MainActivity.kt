package dti.g25.pendu

import android.app.AlertDialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dti.g25.pendu.presentateur.Présentateur


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val présentateur = Présentateur(this)


    val btnLettres = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')

    val listeDeMots = arrayOf("toaster", "microwave", "oven", "television", "kettle", "remote", "printer", "router", "tablet", "laptop","camera","microphone")


    val couleurVert = 0xFF00FF00.toInt()
    val couleurBleu= 0xFF42DBEF.toInt()
    val couleurGris = 0xFF808080.toInt()
    lateinit var imgPendu: ImageView
    lateinit var tvScore: TextView

    val message by lazy { AlertDialog.Builder(this) }








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        disableButtons()
        for (i in btnLettres.indices) {
            val resID = resources.getIdentifier("btn${btnLettres[i]}", "id", packageName)
            val button = findViewById<Button>(resID)
            button.setTag(btnLettres[i])
            button.setOnClickListener(this)
        }
        imgPendu = findViewById(R.id.imgPendu)



        var btnStart = findViewById( R.id.btn_start) as Button
        btnStart.setOnClickListener { présentateur.réinitialiser(listeDeMots); btnStart.isEnabled=false }



        var btnRecommencer = findViewById( R.id.btnRecommencer ) as Button
        btnRecommencer.setOnClickListener { présentateur.réinitialiser(listeDeMots) }
        tvScore = findViewById( R.id.tvScore )
        présentateur.démarrer(listeDeMots)
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



    fun afficherImage(image: Drawable) {
        imgPendu.setImageDrawable(image)
    }

    fun disableButtons(){
        for (i in btnLettres.indices) {
            val resID = resources.getIdentifier("btn${btnLettres[i]}", "id", packageName)
            val button = findViewById<Button>(resID)
            button.isEnabled = false
            button.setBackgroundColor(couleurGris)
            button.setOnClickListener(this)
        }
    }

    fun AfficherGagner(motAdeviner:String){
        message.setTitle("Pendu")
        message.setMessage("GAGNÉ! Vous avez bien trouver le mot " + motAdeviner)
        message.setPositiveButton("Quitter") { _, _ ->
            this.finish()
        }
        message.setNegativeButton("Rejouer") { _, _ ->
            this.recreate()
        }
        val dialog: AlertDialog = message.create()
        dialog.show()
    }

    fun AfficherPerdu(motAdeviner:String){
        message.setTitle("Pendu")
        message.setMessage("Perdu! le mot est " + motAdeviner)
        message.setPositiveButton("Quitter") { _, _ ->
            this.finish()
        }
        message.setNegativeButton("Rejouer") { _, _ ->
            this.recreate()
        }
        val dialog: AlertDialog = message.create()
        dialog.show()
    }





}


