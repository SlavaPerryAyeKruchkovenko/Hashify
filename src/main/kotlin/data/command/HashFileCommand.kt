package org.gigachad.data.command

import java.io.File
import java.security.MessageDigest
import org.gigachad.data.Algorithm

class HashFileCommand @JvmOverloads constructor(
    private val inputPath: String,
    private val algorithm: Algorithm,
    private val outputPath: String? = null,
) : Command() {

    override fun execute() {
        val inputFile = File(inputPath)
        val hashedData = inputFile.readLines().map { line ->
            line.split(",").joinToString(",") { cell -> hashString(cell.trim(), algorithm) }
        }
        if (outputPath != null) {
            File(outputPath).writeText(hashedData.joinToString("\n"))
            println("Result write in: $outputPath")
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
