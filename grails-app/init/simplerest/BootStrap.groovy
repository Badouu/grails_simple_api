package simplerest

import com.casa.*

class BootStrap {

    def init = { servletContext ->
        def users = new Users()
        users.name="badara"
        users.email="dark@dark.com"
        users.surname="badou"
        users.password="casa"
        users.save()
        println(users.name)
    }
    def destroy = {
    }
}
