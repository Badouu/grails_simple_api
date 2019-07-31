package com.casa

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UsersServiceSpec extends Specification {

    UsersService usersService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Users(...).save(flush: true, failOnError: true)
        //new Users(...).save(flush: true, failOnError: true)
        //Users users = new Users(...).save(flush: true, failOnError: true)
        //new Users(...).save(flush: true, failOnError: true)
        //new Users(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //users.id
    }

    void "test get"() {
        setupData()

        expect:
        usersService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Users> usersList = usersService.list(max: 2, offset: 2)

        then:
        usersList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        usersService.count() == 5
    }

    void "test delete"() {
        Long usersId = setupData()

        expect:
        usersService.count() == 5

        when:
        usersService.delete(usersId)
        sessionFactory.currentSession.flush()

        then:
        usersService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Users users = new Users()
        usersService.save(users)

        then:
        users.id != null
    }
}
