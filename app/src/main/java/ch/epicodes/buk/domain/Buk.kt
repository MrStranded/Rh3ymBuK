package ch.epicodes.buk.domain

import android.content.Context
import ch.epicodes.buk.infrastructure.loadBuk
import ch.epicodes.buk.infrastructure.saveBuk

const val CHAPTER_START = "--- "

class Buk(val context: Context, val name: String = "BuK", var text: String = "") {

    val chapters = mutableListOf<Chapter>()

    init {
        splitChapters()
    }

    fun update(newText: String) {
        text = newText
    }

    fun load() {
        text = loadBuk(context, name)
    }

    fun save() {
        saveBuk(context, this)
    }

    private fun splitChapters() {
        val separator = System.getProperty("line.separator") ?: '\n'
        val lines = text.lines()

        lines.filter { it.startsWith(CHAPTER_START) }.map { it.substring(CHAPTER_START.length) }

        var hasChapters = false
        var chapter = Chapter()
        var text = ""

        for (line in lines) {
            if (line.startsWith(CHAPTER_START)) {
                if (hasChapters) {
                    chapter.text = text
                    chapters.add(chapter)
                }

                hasChapters = true
                text = ""
                chapter = Chapter(line.substring(CHAPTER_START.length))
            } else {
                text += line + separator
            }
        }
    }
}