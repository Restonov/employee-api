package com.restonov.task.encoder

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import spock.lang.Specification

class PasswordEncoderSpec extends Specification {

    BCryptPasswordEncoder encoder

    def 'setup'() {
        encoder = new BCryptPasswordEncoder()
    }

    def 'encode'() {
        when:
        String pass = encoder.encode('123456')

        then:
        println pass
    }
}
