package com.example.ateszleksuveg

import android.content.Intent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ateszleksuveg.databinding.ActivityMainBinding
import android.widget.Button
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val kerdesek = arrayOf(
        "Egy sötét erdőben válaszút elé érsz. Melyik ösvényen indulsz el?",
        "Mi az a tulajdonság, amit a legkevésbé viselnél el egy barátodban?",
        "Ha választhatnál egy mágikus tárgyat, melyiket vinnéd haza?",
        "Mivel szeretnéd leginkább, hogy emlékezzenek rád a jövőben?",
        "Egy vizsgán látsz valakit csalni. Mit teszel?"
    )

    private val valaszok = arrayOf(
        arrayOf("A jól kivilágított, széles úton, ahol látom, mi vár rám.", "Az eldugott, benőtt ösvényen, ami tele van ismeretlen és izgalmas veszélyekkel.", "A patak menti ösvényen, ahol különleges növényeket és állatokat figyelhetek meg.", "Amelyiken a barátaimmal közösen megegyezünk, hogy a legbiztonságosabb mindannyiunknak."),
        arrayOf("A gyávaságot.", "Az igazságtalanságot.", "A tudatlanságot vagy szűklátókörűséget.", "A gyengeséget vagy a becsvágy hiányát."),
        arrayOf("Egy könyvet, amely minden kérdésre megadja a választ.", "Egy gyűrűt, amely láthatatlanná tesz, hogy bárhová bejuthassak.", "Egy kardot, amivel megvédhetem a gyengéket.", "Egy bőségszarut, amely ételt és italt ad mindenkinek, aki éhes."),
        arrayOf("Hogy bölcs és zseniális voltam.", "Hogy hősies tetteket hajtottam végre.", "Hogy hatalmas befolyást és sikert értem el.", "Hogy jó barát és kedves ember voltam."),
        arrayOf("Szembesítem vele, mert ez nem fair a többiekkel szemben.", "Nem érdekel, amíg engem nem hátráltat a saját céljaim elérésében.", "Megjegyzem magamban a technikáját, de nem avatkozom bele.", "Azonnal jelzem a tanárnak, mert a szabályok és a becsület mindennél előbb való.")

    )

    private var griffendelPontok = 0
    private var mardekarPontok = 0
    private var hugrabugPontok = 0
    private var hollohatPontok = 0

    private var aktualisKerdes = 0






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        binding.opcio1.setOnClickListener { valasztas(0) }
        binding.opcio2.setOnClickListener { valasztas(1) }
        binding.opcio3.setOnClickListener { valasztas(2) }
        binding.opcio4.setOnClickListener { valasztas(3) }

        frissites()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val eredmenyActivityMegnyitasa = findViewById<Button>(R.id.eredmenyActivityMegnyitasa)
        eredmenyActivityMegnyitasa.setOnClickListener {

            val pontok = mapOf(
                "Griffendél" to griffendelPontok,
                "Mardekár" to mardekarPontok,
                "Hugrabug" to hugrabugPontok,
                "Hollóhát" to hollohatPontok
            )
            val nyertesHaz = pontok.maxByOrNull { it.value }?.key ?: "?"

            val intent = Intent(this, eredmeny::class.java)
            intent.putExtra("nyerteshaz", nyertesHaz)
            startActivity(intent)

            resetKviz()
        }


    }

    private fun frissites() {

        if (aktualisKerdes == 0) {
            binding.eredmenyActivityMegnyitasa.visibility = android.view.View.GONE
        }

        binding.kerdes.text = kerdesek[aktualisKerdes]
        binding.opcio1.text = valaszok[aktualisKerdes][0]
        binding.opcio2.text = valaszok[aktualisKerdes][1]
        binding.opcio3.text = valaszok[aktualisKerdes][2]
        binding.opcio4.text = valaszok[aktualisKerdes][3]
    }


    private fun valasztas(valaszIndex: Int){
        when(aktualisKerdes){
            0 -> when(valaszIndex){
                0 -> mardekarPontok++
                1 -> griffendelPontok++
                2 -> hollohatPontok++
                3 ->hugrabugPontok++
            }
            1 -> when(valaszIndex){
                0 -> griffendelPontok++
                1 -> hugrabugPontok++
                2 -> hollohatPontok++
                3 -> mardekarPontok++
            }
            2 -> when(valaszIndex){
                0 -> hollohatPontok++
                1 -> mardekarPontok++
                2 -> griffendelPontok++
                3 -> hugrabugPontok++
            }
            3 -> when(valaszIndex){
                0 -> hollohatPontok++
                1 -> griffendelPontok++
                2 -> mardekarPontok++
                3 -> hugrabugPontok++
            }
            4 -> when(valaszIndex){
                0 -> hugrabugPontok++
                1 -> mardekarPontok++
                2 -> hollohatPontok++
                3 -> griffendelPontok++
            }
        }
        if (aktualisKerdes < kerdesek.size - 1) {
            aktualisKerdes++
            frissites()
        }else{
            binding.kerdes.visibility = android.view.View.GONE
            binding.opcio1.visibility = android.view.View.GONE
            binding.opcio2.visibility = android.view.View.GONE
            binding.opcio3.visibility = android.view.View.GONE
            binding.opcio4.visibility = android.view.View.GONE

            binding.eredmenyActivityMegnyitasa.visibility = android.view.View.VISIBLE
            binding.eredmenyActivityMegnyitasa.text = "Eredmény megtekintése"
        }
    }
    private fun eredmeny() {
        val pontok = mapOf(
            "Griffendél" to griffendelPontok,
            "Mardekár" to mardekarPontok,
            "Hugrabug" to hugrabugPontok,
            "Hollóhát" to hollohatPontok
        )


        val nyertesHaz = pontok.maxByOrNull { it.value }?.key



        binding.kerdes.visibility = android.view.View.GONE
        binding.opcio1.visibility = android.view.View.GONE
        binding.opcio2.visibility = android.view.View.GONE
        binding.opcio3.visibility = android.view.View.GONE
        binding.opcio4.visibility = android.view.View.GONE

        binding.eredmenyActivityMegnyitasa.visibility = android.view.View.VISIBLE
        binding.eredmenyActivityMegnyitasa.text = "Eredmény megtekintése"
    }
    private fun resetKviz() {

        griffendelPontok = 0
        mardekarPontok = 0
        hugrabugPontok = 0
        hollohatPontok = 0

        aktualisKerdes = 0


        binding.kerdes.visibility = android.view.View.VISIBLE
        binding.opcio1.visibility = android.view.View.VISIBLE
        binding.opcio2.visibility = android.view.View.VISIBLE
        binding.opcio3.visibility = android.view.View.VISIBLE
        binding.opcio4.visibility = android.view.View.VISIBLE

        binding.eredmenyActivityMegnyitasa.visibility = android.view.View.GONE

        frissites()
    }


}