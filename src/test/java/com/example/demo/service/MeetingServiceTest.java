package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MeetingServiceTest {

    private MeetingService meetingService;

    @BeforeEach
    void setUp() {
        meetingService = new MeetingService("Test");
    }

    @Test
    void test_invite_participant() {
        // Given
        String participant = "New Participant";

        // When
        meetingService.invite(participant);

        // Then
        Assertions.assertTrue(meetingService.getParticipants().contains(participant));
    }
    @Test
    void test_invite_after_meeting_ended() {
        ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysOut));
        meetingService.endMeeting("Test");

        meetingService.invite("Participant");
        Assertions.assertTrue(sysOut.toString()
                .contains("The meeting has ended. You cannot invite more participants."));
    }

    @Test
    void test_submit_response() {

        meetingService.invite("Participant");
        meetingService.submitResponse("Participant", "2");
        Assertions.assertTrue(meetingService.getResponses().contains("Participant: 2"));
    }

    @Test
    void test_submit_response_for_invalid_participant() {
        ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysOut));

        meetingService.submitResponse("InvalidParticipant", "2");
        Assertions.assertTrue(sysOut.toString()
                .contains("Invalid participant or the meeting has ended."));
    }

    @Test
    void test_Submit_Response_After_Meeting_Ended() {

        meetingService.invite("Participant1");
        meetingService.endMeeting("Test");

        ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOut));

        meetingService.submitResponse("Participant1", "2");

        Assertions.assertTrue(systemOut.toString().contains("Invalid participant or the meeting has ended."));
    }

    @Test
    void test_End_Meeting_By_Initiator() {

        ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOut));

        meetingService.endMeeting("Test");

        Assertions.assertTrue(systemOut.toString().contains("The meeting has been ended by the initiator."));
        Assertions.assertTrue(meetingService.isEnded());
    }

    @Test
    void test_End_Meeting_By_Non_Initiator() {

        ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOut));

        meetingService.endMeeting("NonInitiator");

        Assertions.assertTrue(systemOut.toString().contains("Only the initiator can end the meeting."));
        Assertions.assertFalse(meetingService.isEnded());
    }

    @Test
    void test_Randomly_Pick_Answer_Ongoing_Meeting() {
        meetingService.invite("Participant1");

        ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOut));

        meetingService.randomlyPickAnswer();

        Assertions.assertTrue(systemOut.toString()
                .contains("The meeting is still ongoing. Cannot pick an answer yet."));
    }

    @Test
    void test_Randomly_Pick_Answer_No_Responses() {
        meetingService.endMeeting("Test");

        ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOut));

        meetingService.randomlyPickAnswer();

        Assertions.assertTrue(systemOut.toString().contains("No responses submitted. Cannot pick an answer."));
    }
    @Test
    void test_Randomly_Pick_Answer() {
        meetingService.invite("Participant1");
        meetingService.submitResponse("Participant1", "2");

        ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOut));

        meetingService.endMeeting("Test");
        meetingService.randomlyPickAnswer();

        Assertions.assertTrue(systemOut.toString()
                .contains("Randomly picked answer: Participant1: 2"));
    }


}