package com.example.foosball.model

import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 11/2/17.
 */
class FoosballSpec extends Specification {
    @Subject
    foosball = new Foosball()

    def 'can play foosball'(){
        when:
        foosball.play()

        then:
        true
    }
}