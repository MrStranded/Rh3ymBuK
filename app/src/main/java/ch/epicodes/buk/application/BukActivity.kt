package ch.epicodes.buk.application

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.epicodes.buk.R
import ch.epicodes.buk.domain.Buk
import kotlinx.android.synthetic.main.activity_buk.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.chapter.view.*

class BukActivity: AppCompatActivity() {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ChapterAdapter

    private lateinit var buk: Buk

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buk)

        val name = intent.getStringExtra("buk")
        buk = Buk(this, name ?: "BuK")

        viewManager = LinearLayoutManager(this)
        viewAdapter = ChapterAdapter(this, buk)

        initBuk()

        chapterList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        
//        bukText.addTextChangedListener {
//            buk.update(bukText.text.toString())
//        }

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initBuk() {
        buk.load()
    }

    override fun onPause() {
        buk.save()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        buk.load()
//        bukText.setText(buk.text)
    }

    private fun EditText.addTextChangedListener(action: () -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                action()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

}

