package ch.epicodes.buk.domain

import android.content.Context
import ch.epicodes.buk.infrastructure.loadBuk
import ch.epicodes.buk.infrastructure.saveBuk
import java.io.FileNotFoundException

class Buk(val context: Context, val name: String = "BuK", var text: String = "") {

    fun update(newText: String) {
        text = newText
    }

    fun load() {
        text = loadBuk(context, name)
    }

    fun save() {
        saveBuk(context, this)
    }
}