package ch.epicodes.buk.application

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ch.epicodes.buk.R
import ch.epicodes.buk.domain.Buk
import ch.epicodes.buk.infrastructure.deleteBuk
import ch.epicodes.buk.infrastructure.renameBuk
import kotlinx.android.synthetic.main.activity_buk.*
import kotlinx.android.synthetic.main.activity_buk.back
import kotlinx.android.synthetic.main.activity_buk_settings.*

class BukSettingsActivity: AppCompatActivity() {

    private lateinit var buk: Buk

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buk_settings)

        val name = intent.getStringExtra("buk")
        buk = Buk(this, name ?: "BuK")

        renameValue.setText(buk.name)
        
        renameButton.setOnClickListener {
            renameBuk(this, buk.name, renameValue.text.toString())
            buk = Buk(this, renameValue.text.toString())
        }

        deleteButton.setOnClickListener {
            deleteBuk(this, buk.name)
            back()
        }

        back.setOnClickListener {
            back()
        }
    }

    fun back() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}

