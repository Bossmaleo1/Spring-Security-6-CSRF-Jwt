package com.eazybytes.springsecuritybasic.repository

import com.eazybytes.springsecuritybasic.model.Notice
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NoticeRepository : CrudRepository<Notice?, Long?> {
    @Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
    fun findAllActiveNotices(): List<Notice?>?
}