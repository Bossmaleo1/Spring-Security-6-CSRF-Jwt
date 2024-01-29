package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.Contact
import com.eazybytes.springsecuritybasic.repository.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreFilter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.sql.Date
import java.util.*


@RestController
class ContactController {
    @Autowired
    private val contactRepository: ContactRepository? = null

    @PostMapping("/contact")
    //@PreFilter("filterObject.contactName != 'Test'")
    @PostFilter("filterObject.contactName != 'Test'")
    fun saveContactInquiryDetails(@RequestBody contacts: List<Contact>): MutableList<Contact> {
        var contact: Contact = contacts[0]
        contact.contactId = getServiceReqNumber()
        contact.createDt = Date(System.currentTimeMillis())
        contact = contactRepository!!.save(contact)
        val returnContacts: MutableList<Contact> = ArrayList()
        returnContacts.add(contact)
        return returnContacts
    }

    /*val serviceReqNumber: String
        get() {
            val random = Random()
            val ranNum = random.nextInt(999999999 - 9999) + 9999
            return "SR$ranNum"
        }*/

    fun getServiceReqNumber(): String {
        val random = Random()
        val ranNum = random.nextInt(999999999 - 9999) + 9999
        return "SR$ranNum"
    }
}