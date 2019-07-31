package com.casa

import grails.rest.*

//@Resource(uri='/users')
class Users {
    String name
    String surname
    String password
    String email

    Users(String name, String username, String password, String email) {
		this()
		this.username = username
        this.name=name
        this.email=email
		password=password.digest('SHA-256')
		this.password = password
        println(this.password)
	}

    static constraints = {
        email unique: true
    }
}
