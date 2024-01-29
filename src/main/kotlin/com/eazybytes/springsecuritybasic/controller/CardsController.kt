package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.Cards
import com.eazybytes.springsecuritybasic.repository.CardsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CardsController {
    @Autowired
    private val cardsRepository: CardsRepository? = null

    @GetMapping("/myCards")
    fun getCardDetails(@RequestParam id: Int): List<Cards?>? {
        val cards = cardsRepository!!.findByCustomerId(id)
        return cards
    }
}