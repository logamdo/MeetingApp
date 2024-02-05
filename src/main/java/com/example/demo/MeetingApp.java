package com.example.demo;

import java.util.Scanner;

public class MeetingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the meeting initiator: ");
        String initiator = scanner.nextLine();

        Meeting meeting = new Meeting(initiator);

        while (true) {
            System.out.println("\n1. Invite participant");
            System.out.println("2. Submit response");
            System.out.println("3. End meeting");
            System.out.println("4. Randomly pick answer");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (Exception e) {

            }
            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the participant to invite: ");
                    String participant = scanner.nextLine();
                    meeting.invite(participant);
                    break;
                case 2:
                    System.out.print("Enter your name (participant): ");
                    String responder = scanner.nextLine();
                    System.out.print("Enter your response: ");
                    String response = scanner.nextLine();
                    meeting.submitResponse(responder, response);
                    break;
                case 3:
                    System.out.print("Enter your name (initiator) to end the meeting: ");
                    String endInitiator = scanner.nextLine();
                    meeting.endMeeting(endInitiator);
                    break;
                case 4:
                    meeting.randomlyPickAnswer();
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
}
