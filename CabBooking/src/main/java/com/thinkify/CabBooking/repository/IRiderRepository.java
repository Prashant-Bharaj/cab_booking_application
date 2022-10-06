package com.thinkify.CabBooking.repository;

import com.thinkify.CabBooking.model.Rider;
import com.thinkify.CabBooking.model.User;

import java.util.Optional;

public interface IRiderRepository {
    public void save(Rider rider);
    public Optional<Rider> findByName(String name);
    public boolean riderExists(String name);
}
