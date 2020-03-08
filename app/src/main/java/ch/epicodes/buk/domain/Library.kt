package ch.epicodes.buk.domain

import android.content.Context
import ch.epicodes.buk.infrastructure.loadBuks

class Library(val context: Context) {

    lateinit var buks: MutableList<Buk>

    fun load() {
        buks = loadBuks(context)
//        buks = mutableListOf()
    }

    fun save() {
//        saveBuks(context, buks)
    }

    fun addBuk(name: String) = buks.add(
        Buk(
            context,
            name
        )
    )
}