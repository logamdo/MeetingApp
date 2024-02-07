package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MeetingService {

    private String initiator;
    private List<String> participants;
    private List<String> responses;
    private boolean ended;

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public MeetingService(String initiator) {
        this.initiator = initiator;
        this.participants = new ArrayList<>();
        this.responses = new ArrayList<>();
        this.ended = false;
    }

    public void invite(String participant) {
        if (!ended) {
            participants.add(participant);
            System.out.println(participant + " has been invited to the meeting.");
        } else {
            System.out.println("The meeting has ended. You cannot invite more participants.");
        }
    }

    public void submitResponse(String participant, String response) {
        if (!ended && participants.contains(participant)) {
            responses.add(participant + ": " + response);
            System.out.println(participant + " submitted response: " + response);
        } else {
            System.out.println("Invalid participant or the meeting has ended.");
        }
    }

    public void endMeeting(String initiator) {
        if (initiator.equals(this.initiator)) {
            System.out.println("The meeting has been ended by the initiator.");
            ended = true;
        } else {
            System.out.println("Only the initiator can end the meeting.");
        }
    }

    public void randomlyPickAnswer() {
        if (ended) {
            if (responses.isEmpty()) {
                System.out.println("No responses submitted. Cannot pick an answer.");
            } else {
                Random random = new Random();
                int randomIndex = random.nextInt(responses.size());
                String pickedAnswer = responses.get(randomIndex);
                System.out.println("Randomly picked answer: " + pickedAnswer);
            }
        } else {
            System.out.println("The meeting is still ongoing. Cannot pick an answer yet.");
        }
    }
}


