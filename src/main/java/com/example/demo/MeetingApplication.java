package com.example.demo;

import com.example.demo.service.MeetingManager;
import com.example.demo.service.MeetingService;
import com.example.demo.util.CommandLineRunner;

public class MeetingApplication {

    public static void main(String[] args) {
        String initiator = CommandLineRunner.initiateCommandLine();
        MeetingManager meetingManager = new MeetingManager(new MeetingService(initiator));
        meetingManager.handleMeeting();
    }
}
