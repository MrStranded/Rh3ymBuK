package ch.epicodes.buk.domain

import android.content.Context
import android.util.Log
import ch.epicodes.buk.infrastructure.loadChapters
import ch.epicodes.buk.infrastructure.saveBuk

const val CHAPTER_START = "--- "

class Buk(val context: Context, val name: String = "BuK", var text: String = "") {

    var chapters = mutableListOf<Chapter>()

    fun load() {
        chapters = loadChapters(context, name)
    }

    fun save() {
        saveBuk(context, this)
    }

    fun createChapter() {
        val chapter = Chapter()
        chapter.setTitleToCurrentDate()
        chapters.add(chapter)
    }

    fun deleteChapter(position: Int) {
        if (0 <= position && position < chapters.size) {
            chapters.removeAt(position)
        }

        if (chapters.size == 0) {
            createChapter()
        }
    }

    fun mergeChapters(): String {
        var mergedText = ""
        val separator = System.getProperty("line.separator") ?: '\n'

        for (chapter in chapters) {
            mergedText += CHAPTER_START + chapter.title + separator + chapter.text + separator
        }

        return mergedText
    }
}