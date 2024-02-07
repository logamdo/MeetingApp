package com.example.demo.util;

import java.util.Scanner;

public class CommandLineRunner {

    public static String initiateCommandLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the meeting initiator: ");
        return scanner.nextLine();
    }
}
