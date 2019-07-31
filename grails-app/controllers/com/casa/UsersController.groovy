package com.casa

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UsersController {

    UsersService usersService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond usersService.list(params), model:[usersCount: usersService.count()]
    }

    def show(Long id) {
        respond usersService.get(id)
    }

    def create() {
        respond new Users(params)
    }

    def save(Users users) {
        if (users == null) {
            notFound()
            return
        }

        try {
            users.password=users.password.digest('SHA-256')
            usersService.save(users)
        } catch (ValidationException e) {
            respond users.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'users.label', default: 'Users'), users.id])
                redirect users
            }
            '*' { respond users, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond usersService.get(id)
    }

    def update(Users users) {
        if (users == null) {
            notFound()
            return
        }

        try {
            usersService.save(users)
        } catch (ValidationException e) {
            respond users.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'users.label', default: 'Users'), users.id])
                redirect users
            }
            '*'{ respond users, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        usersService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'users.label', default: 'Users'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'users.label', default: 'Users'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
