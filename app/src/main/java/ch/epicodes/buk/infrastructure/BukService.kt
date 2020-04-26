package ch.epicodes.buk.infrastructure

import android.content.Context
import android.os.Environment
import android.util.Log
import ch.epicodes.buk.domain.Buk
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