package ch.epicodes.buk.infrastructure

import android.content.Context
import android.os.Environment
import android.util.Log
import ch.epicodes.buk.domain.Buk
import ch.epicodes.buk.domain.CHAPTER_START
import ch.epicodes.buk.domain.Chapter
import java.io.File
import java.io.FileNotFoundException

fun loadBuks(context: Context): MutableList<Buk> =
    context.fileList().map { Buk(context, it) }.toMutableList()

fun saveBuks(context: Context, buks: MutableCollection<Buk>) {
    // TODO
}

fun renameBuk(context: Context, formerName: String, newName: String) =
    File(context.filesDir, formerName).renameTo(File(context.filesDir, newName))

fun deleteBuk(context: Context, name: String) = context.deleteFile(name)

fun loadBuk(context: Context, name: String): String {
    var content = ""
    try {
        val reader = context.openFileInput(name).bufferedReader()
        reader.use {
            reader.lines().forEach { content += it + System.getProperty("line.separator") }
        }
    } catch (e: FileNotFoundException) {
        println("BuK named '$name' does not yet exist")
    }
    return content
}

fun saveBuk(context: Context, buk: Buk) {
    val mergedText = buk.mergeChapters()
    Log.println(Log.DEBUG, "save", """buk text: $mergedText""")
    val writer = context.openFileOutput(buk.name, Context.MODE_PRIVATE).bufferedWriter()
    writer.use {
        writer.write(mergedText)
    }
}

fun loadChapters(context: Context, name: String): MutableList<Chapter> {
    var firstLine = true
    var chapter = Chapter(title = "Chapter 1")
    val chapters = mutableListOf<Chapter>()
    val separator = System.getProperty("line.separator") ?: '\n'

    try {
        val reader = context.openFileInput(name).bufferedReader()
        reader.use {
            reader.lines().forEach {
                println("""reading line: $it""")
                if (it.startsWith(CHAPTER_START)) {
                    if (! firstLine) { chapters.add(chapter) }

                    firstLine = false
                    chapter = Chapter(title = it.substring(CHAPTER_START.length))
                    println("""loading chapter: ${chapter.title}""")
                } else {
                    chapter.text += it + separator
                }
            }
        }
    } catch (e: FileNotFoundException) {
        println("BuK named '$name' does not yet exist")
    }

    chapters.add(chapter)

    return chapters
}