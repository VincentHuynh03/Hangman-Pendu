package dti.g25.pendu

import android.app.AlertDialog
import android.content.Intent
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
    val btnLettres = CharArray(26) { ('A' + it) }
    val listeDeMots = arrayOf("chat", "pomme", "chapeau", "soleil", "fleur", "livre", "montagne", "plage", "guitare", "voiture")
    val couleurVert = 0xFF00FF00.toInt()
    val couleurBleu= 0xFF42DBEF.toInt()
    val couleurGris = 0xFF808080.toInt()
    lateinit var imgPendu: ImageView
    lateinit var tvScore: TextView
    val message by lazy { AlertDialog.Builder(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialisationVue()
        désactiverBouttons()

        présentateur.démarrer(listeDeMots)
    }
    /**
     * Initialise les éléments de la vue de l'activité principale.
     * Associe les boutons de lettres avec leur identifiant de ressource, et ajoute
     * un listener de clic pour chaque bouton. Initialise également les vues de l'image
     * du pendu et du score, ainsi que les boutons "Démarrer" et "Recommencer".
     */
    private fun initialisationVue() {
        for (i in btnLettres.indices) {
            val resID = resources.getIdentifier("btn${btnLettres[i]}", "id", packageName)
            val button = findViewById<Button>(resID)
            button.setOnClickListener(this)
        }

        imgPendu = findViewById(R.id.imgPendu)
        tvScore = findViewById(R.id.tvScore)

        val btnDémarrer = findViewById<Button>(R.id.btn_start)
        btnDémarrer.setOnClickListener {
            couleurRéinitialiser()
            btnDémarrer.isEnabled = false
        }

        val btnRecommencer = findViewById<Button>(R.id.btnRecommencer)
        btnRecommencer.setOnClickListener {
            présentateur.réinitialiser(listeDeMots)
        }
    }
    /**
     * Cette méthode est appelée lorsqu'un bouton est cliqué.
     * Elle enregistre la lettre associée au bouton cliqué dans la méthode sélectionnerLettre() du présentateur.
     * Elle change la couleur du bouton cliqué en vert si la lettre est dans le mot à deviner, sinon elle le change en gris.
     * Elle désactive le bouton cliqué.
     *
     * @param v la partie vue qui a été cliquée par l'utilisateur
     */
    override fun onClick(v: View) {
        if (v is Button) {
            val lettre = v.text.toString()[0]
            présentateur.sélectionnerLettre(lettre)
            val étatLettres = présentateur.ÉtatDuMot()
            couleurChange(v, étatLettres)
            v.isEnabled = false
        }
    }
    /**
     * Cette méthode affiche l'état actuel du mot à deviner.
     *
     * @param etat l'état actuel du mot à deviner
     */
     fun afficherÉtatLettres(etat: String) {
        val tv = findViewById<TextView>(R.id.tvMot)
        tv.text = etat
    }

    /**
     * Cette méthode affiche le score actuel.
     *
     * @param score le score actuel
     */
    fun afficherScore(score: Int) {
      tvScore.text = score.toString()
    }
    /**
     * Cette méthode change la couleur du bouton en fonction de l'état du mot à deviner.
     * Si la lettre correspondant au bouton est dans le mot à deviner, la couleur du bouton est verte.
     * Sinon, la couleur du bouton est grise.
     *
     * @param button le bouton dont la couleur doit être changée
     * @param etat l'état actuel du mot à deviner
     */
    fun couleurChange(button: Button, etat: String) {
        if (etat.contains(button.text.toString()[0], ignoreCase = true)) {
            button.setBackgroundColor(couleurVert)
        } else {
            button.setBackgroundColor(couleurGris)
        }
    }
    /**
     * Cette méthode réinitialise les couleurs des boutons à la couleur de base.
     * Elle réactive les boutons désactivés lorsqu'ils ont été cliqués.
     */
    fun couleurRéinitialiser(){
        for (i in btnLettres.indices) {
            val resID = resources.getIdentifier("btn${btnLettres[i]}", "id", packageName)
            val button = findViewById<Button>(resID)
            button.setBackgroundColor(couleurBleu)
            button.isEnabled = true
            button.setOnClickListener(this)
        }
    }
    /**
     * Cette méthode affiche l'image correspondant au pendu.
     *
     * @param image l'image correspondant au pendu
     */
    fun afficherImage(image: Drawable) {
        imgPendu.setImageDrawable(image)
    }
    /**
     * Cette méthode désactive tous les boutons de lettres.
     */
    fun désactiverBouttons(){
        for (i in btnLettres.indices) {
            val resID = resources.getIdentifier("btn${btnLettres[i]}", "id", packageName)
            val button = findViewById<Button>(resID)
            button.isEnabled = false
            button.setBackgroundColor(couleurGris)
            button.setOnClickListener(this)
        }
    }
    /**
     * Cette méthode affiche une fenêtre de dialogue annonçant que le joueur a gagné.
     * Elle affiche le mot à deviner dans la fenêtre de dialogue.
     */
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
    /**
     * Cette méthode affiche une fenêtre de dialogue annonçant que le joueur a perdu.
     * Elle affiche le mot à deviner dans la fenêtre de dialogue.
     */
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


