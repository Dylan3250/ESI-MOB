package blackjack.repository

import blackjack.data.ScoreDao
import blackjack.dto.ScoreDto

object Repository {
    fun getAll() = ScoreDao.selectAll()
    fun add(item: ScoreDto) { ScoreDao.insert(item) }
}