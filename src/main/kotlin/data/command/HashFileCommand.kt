package org.hashify.data.command

import java.io.File
import java.security.MessageDigest
import org.hashify.data.Algorithm

class HashFileCommand @JvmOverloads constructor(
    private val inputPath: String,
    private val algorithm: Algorithm,
    private val outputPath: String? = null,
    private val hashColumn: Collection<Int>,
    private val ignoreRow: Collection<Int>? = null,
) : Command() {

    override fun execute() {
        val inputFile = File(inputPath)
        val lines = inputFile.readLines()

        val hashedData = lines.mapIndexed { index, line ->
            if (ignoreRow?.contains(index) == true) {
                line
            } else {
                val cells = line.split(",")
                line + cells.mapIndexedNotNull { colIndex, value ->
                    val cell = value.trim()
                    if (colIndex in hashColumn && cell.isNotBlank()) {
                        hashString(cell, algorithm)
                    } else {
                        null
                    }
                }.joinToString(",")
            }
        }

        if (outputPath != null) {
            File(outputPath).writeText(hashedData.joinToString("\n"))
            println("Result written in: $outputPath")
        } else {
            hashedData.forEach { println(it) }
        }
    }

    private fun hashString(input: String, algorithm: Algorithm): String {
        val digest = MessageDigest.getInstance(algorithm.name)
        val hashBytes = digest.digest(input.toByteArray())
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
}
