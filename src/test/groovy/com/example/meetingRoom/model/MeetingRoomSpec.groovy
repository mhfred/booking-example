package com.example.meetingRoom.model

import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 11/2/17.
 */
class MeetingRoomSpec extends Specification {
    @Subject
    meetingRoom = new MeetingRoom()

    def 'can have meeting'(){
        when:
        meetingRoom.meeting()

        then:
        true
    }
}