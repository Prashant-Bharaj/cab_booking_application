package com.thinkify.CabBooking.repository;

import com.thinkify.CabBooking.model.Driver;
import com.thinkify.CabBooking.model.User;

import java.util.Map;
import java.util.Optional;

public interface IDriverRepository {
    public Map<String, Driver> getDriverMap();
    public void save(Driver driver);
    public Optional<Driver> findByName(String name);
}
