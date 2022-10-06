package com.thinkify.CabBooking.service;

import com.thinkify.CabBooking.model.Driver;
import com.thinkify.CabBooking.model.Location;
import com.thinkify.CabBooking.model.Rider;
import com.thinkify.CabBooking.repository.IDriverRepository;
import com.thinkify.CabBooking.repository.IRiderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RideService {
    IDriverRepository driverRepository;
    IRiderRepository riderRepository;

    List<Driver> driverList;

    public RideService(IDriverRepository driverRepository, IRiderRepository riderRepository) {
        this.driverRepository = driverRepository;
        this.riderRepository = riderRepository;
    }

    public List<Driver> findAvailableDrivers(String name, Location source, Location destination) throws Exception {
        Optional<Rider> rider = riderRepository.findByName(name);
        if(rider.isEmpty()) throw new Exception("rider not exists in database");
        riderRepository.save(new Rider(rider.get(), source, destination));
        driverList = new ArrayList<>();
        for(Driver driver : driverRepository.getDriverMap().values()){
            if(driver.getAvailability() && distance(source, driver.getLocation()) < 5){
                driverList.add(driver);
            }
        }
        return driverList;
    }

    //TODO: cache the search results
    public boolean chooseRide(String riderName, String driverName) throws Exception {
        Optional<Rider> rider = riderRepository.findByName(riderName);
        if(rider.isEmpty()) throw new Exception("rider not exists in database");
        Location source = rider.get().getSource();
        Location destination = rider.get().getDestination();
        if(source==null || destination == null) throw new Exception("Please find available drivers first");

        driverList = new ArrayList<>();
        for(Driver driver : driverRepository.getDriverMap().values()){
            if(driver.getAvailability() && distance(rider.get().getSource(), driver.getLocation()) < 5){
                driverList.add(driver);
            }
        }
        // it is essential before choosingRide, rider search for available rides
        if(driverList.isEmpty()) throw new Exception("Not any driver available");

        for(Driver driver : driverList){
            if(driver.getName().equals(driverName)){
                if(!driver.getAvailability()){
                    throw new Exception("Someone else booked the driver");
                } else {
                    driverRepository.save(new Driver(driver, false));
                    return true;
                }
            }
        }
        return false;
    }

    public double distance(Location riderLocation, Location driverLocation){
        // to calculate distance [[https://www.movable-type.co.uk/scripts/latlong.html]]
        // Assumption xy-coordinate
        return Math.sqrt(Math.pow(riderLocation.getLatitude() - driverLocation.getLatitude(), 2) + Math.pow(riderLocation.getLongitude() - driverLocation.getLongitude(), 2));
    }
}
