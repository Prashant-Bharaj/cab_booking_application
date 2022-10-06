package com.thinkify.CabBooking.commands;



import java.util.List;

import com.thinkify.CabBooking.model.Driver;
import com.thinkify.CabBooking.model.Gender;
import com.thinkify.CabBooking.model.Location;
import com.thinkify.CabBooking.model.Vehicle;
import com.thinkify.CabBooking.repository.IDriverRepository;

public class AddDriverCommand implements ICommand{
    IDriverRepository driverRepository;

    public AddDriverCommand(IDriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public void execute(List<String> tokens) {
        Character gender = tokens.get(2).charAt(0);
        driverRepository.save(new Driver(tokens.get(1), gender =='F'? Gender.FEMALE : gender == 'M' ? Gender.MALE : Gender.NonBinary, Integer.parseInt(tokens.get(3)),  new Vehicle(tokens.get(4), tokens.get(5)), new Location(Double.parseDouble(tokens.get(6)), Double.parseDouble(tokens.get(7)))));
    }
}
