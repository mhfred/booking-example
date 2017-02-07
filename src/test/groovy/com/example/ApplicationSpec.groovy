package com.example

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


/**
 * Created by deadlock on 7/2/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationSpec extends Specification {
    def 'Can boot up'() {
        expect:
        true
    }
}