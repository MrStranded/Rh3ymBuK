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
        viewAdapter = ChapterAdapter(buk)

        initBuk()

        chapterList.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        createChapter.setOnClickListener {
            buk.createChapter()

            val size = buk.chapters.size
            viewAdapter.notifyItemInserted(size - 1)
            viewAdapter.notifyItemRangeChanged(size - 1, size)
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
    }
}

