package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.Notice
import com.eazybytes.springsecuritybasic.repository.NoticeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.CacheControl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
class NoticesController(
    @Autowired
    private val noticeRepository: NoticeRepository
) {

    @GetMapping("/notices")
    fun getNotices(): ResponseEntity<List<Notice?>> {
        val notices = noticeRepository.findAllActiveNotices()
        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
            .body(notices)
    }

    /*@get:GetMapping("/notices")
    val notices: ResponseEntity<List<Notice?>>?
        get() {
            val notices = noticeRepository!!.findAllActiveNotices()
            return if (notices != null) {
                ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices)
            } else {
                null
            }
        }*/
}