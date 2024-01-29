package com.eazybytes.springsecuritybasic.repository

import com.eazybytes.springsecuritybasic.model.Contact
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : CrudRepository<Contact?, Long?>