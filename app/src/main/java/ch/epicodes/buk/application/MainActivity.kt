package ch.epicodes.buk.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.epicodes.buk.R
import ch.epicodes.buk.domain.Library
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private val library = Library(this)

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: BukAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        library.load()

        viewManager = LinearLayoutManager(this)
        viewAdapter = BukAdapter(this, library)

        bukList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        createNew.setOnClickListener {
            var name = bukName.text.toString()

            if (name.isNotEmpty()) {
                library.addBuk(name)
                viewAdapter.notifyDataSetChanged()
            }

            bukName.setText("")
        }
    }
}
