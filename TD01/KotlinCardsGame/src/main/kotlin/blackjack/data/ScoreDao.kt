package blackjack.data

import blackjack.dto.ScoreDto
import java.io.File
import java.lang.Exception
import java.net.URLDecoder

object ScoreDao {
    private val file: File

    init {
        val resources = ClassLoader.getSystemResource("myFile.txt").file
        file = File(URLDecoder.decode(resources, Charsets.UTF_8))
    }

    fun selectAll(): List<ScoreDto> {
        val scores = mutableListOf<ScoreDto>()
        val list = file.readLines(Charsets.UTF_8)
        for (text in list) {
            try {
                val line = text.split(" ")
                val current = ScoreDto(line[0].toInt(), line[1].toInt())
                scores.add(current)
            } catch (ex : Exception) {
                // do nothing
            }
        }
        return scores
    }

    fun insert(item: ScoreDto) {
        file.appendText("\n$item", Charsets.UTF_8)
    }
}