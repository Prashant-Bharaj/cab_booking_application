package com.thinkify.CabBooking.commands;



import java.util.List;

import com.thinkify.CabBooking.model.Driver;
import com.thinkify.CabBooking.model.Location;
import com.thinkify.CabBooking.service.RideService;

public class FindRideCommand implements ICommand{
    RideService rideService;

    public FindRideCommand(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens) {
        Location source = new Location(Double.parseDouble(tokens.get(2)), Double.parseDouble(tokens.get(3)));
        Location destination = new Location(Double.parseDouble(tokens.get(4)), Double.parseDouble(tokens.get(5)));
        try{
            List<Driver> availableDrivers = rideService.findAvailableDrivers(tokens.get(1), source, destination);
            if(availableDrivers.size()==0){
                System.out.println("No availableDrivers found");
            } else {
                System.out.println("List of availableDrivers:");
            }
            for(Driver driver : availableDrivers) {
                System.out.println(driver.getName());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
