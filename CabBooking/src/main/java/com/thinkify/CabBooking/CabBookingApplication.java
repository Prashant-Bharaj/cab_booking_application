package com.thinkify.CabBooking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thinkify.CabBooking.appConfig.ApplicationConfig;
import com.thinkify.CabBooking.commands.CommandInvoker;


public class CabBookingApplication {

    public static void main(String[] args) {
        // System.out.println("Hello World");
       List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
       run(commandLineArgs);
    }

    public static void run(List<String> commandLineArgs) {
        // logic to run the complete program.
        try {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
            if(commandLineArgs.size() == 0) throw new RuntimeException("FILE_NAME_NOT_PROVIDED");
            String inputFile = commandLineArgs.get(0);

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0),tokens);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (RuntimeException | IOException e) {
            e.getMessage();
        }
    }

}
