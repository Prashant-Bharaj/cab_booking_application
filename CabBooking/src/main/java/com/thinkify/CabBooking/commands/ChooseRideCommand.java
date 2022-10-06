package com.thinkify.CabBooking.commands;


import java.util.List;

import com.thinkify.CabBooking.service.RideService;

public class ChooseRideCommand implements ICommand{
    RideService rideService;

    public ChooseRideCommand(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            boolean status = rideService.chooseRide(tokens.get(1), tokens.get(2));
            if(status) {
                System.out.println("Booked Sucessfully");
            } else {
                throw new Exception("Unkown error");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
