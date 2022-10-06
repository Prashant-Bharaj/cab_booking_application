package com.thinkify.CabBooking.commands;


import java.util.List;

import com.thinkify.CabBooking.model.Gender;
import com.thinkify.CabBooking.model.Rider;
import com.thinkify.CabBooking.repository.IRiderRepository;

public class AddRiderCommand implements ICommand{

    IRiderRepository riderRepository;

    public AddRiderCommand(IRiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    public void execute(List<String> tokens) {
        Character gender = tokens.get(2).charAt(0);
        riderRepository.save(new Rider(tokens.get(1), gender =='F'? Gender.FEMALE : gender == 'M' ? Gender.MALE : Gender.NonBinary, Integer.parseInt(tokens.get(3))));
    }
}
