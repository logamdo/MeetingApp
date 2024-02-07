package com.example.demo.service;


import com.example.demo.util.MenuProvider;

import java.util.Scanner;

public class MeetingManager {

    private final MeetingService meetingService;

    public MeetingManager(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    public void handleMeeting() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            MenuProvider.displayMenu();

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            meetingOperation(choice);
        }
    }

    public void meetingOperation(int choice) {

        Scanner scanner = new Scanner(System.in);

        switch (choice) {
            case 1:
                System.out.print("Enter the name of the participant to invite: ");
                String participant = scanner.nextLine();
                meetingService.invite(participant);
                break;
            case 2:
                System.out.print("Enter your name (participant): ");
                String responder = scanner.nextLine();
                System.out.print("Enter your response: ");
                String response = scanner.nextLine();
                meetingService.submitResponse(responder, response);
                break;
            case 3:
                System.out.print("Enter your name (initiator) to end the meeting: ");
                String endInitiator = scanner.nextLine();
                meetingService.endMeeting(endInitiator);
                break;
            case 4:
                meetingService.randomlyPickAnswer();
                break;
            case 5:
                System.out.println("Exiting the meeting application.");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                scanner.nextLine(); // Consume the newline character
        }
    }
}
