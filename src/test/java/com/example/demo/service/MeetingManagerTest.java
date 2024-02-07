package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class MeetingManagerTest {

    @Test
    void should_Invite_Participant_When_code_Is_1() {
        MeetingService meetingService = new MeetingService("Initiator");
        MeetingManager meetingManager = new MeetingManager(meetingService);

        String input = "Participant_1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        meetingManager.meetingOperation(1);
        Assertions.assertTrue(meetingService.getParticipants().contains("Participant_1"));

    }

    @Test
    @DisplayName("test case 3 when initiator end the meeting")
    void should_Invite_Participant_When_code_Is_3() {
        MeetingService meetingService = new MeetingService("Initiator");
        MeetingManager meetingManager = new MeetingManager(meetingService);

        String participant = "Initiator";
        InputStream in = new ByteArrayInputStream(participant.getBytes());
        System.setIn(in);

        meetingManager.meetingOperation(3);
        Assertions.assertTrue(meetingService.isEnded());
    }

}