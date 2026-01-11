package com.example.ateszleksuveg

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.RelativeLayout

class eredmeny : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eredmeny)


        val eredmeny = findViewById<TextView>(R.id.eredmenyed)

        val nyerteshaz = intent.getStringExtra("nyerteshaz")


        eredmeny.text = "A Teszlek Süveg döntése alapján a házad: $nyerteshaz"
        val mainLayout = findViewById<RelativeLayout>(R.id.main)

        val hatterkep = when (nyerteshaz) {
            "Griffendél" -> R.drawable.griffendel
            "Mardekár" -> R.drawable.mardekar
            "Hugrabug" -> R.drawable.hugrabug
            "Hollóhát" -> R.drawable.hollohat
            else -> R.color.sotetkek
        }
        mainLayout.setBackgroundResource(hatterkep)

        val buttonBackToMain = findViewById<Button>(R.id.visszaATeszthez)
        buttonBackToMain.setOnClickListener {
            finish()
        }
    }
}