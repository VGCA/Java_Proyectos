package com.moonhotels.moonhotels_practica.adapter;

import com.moonhotels.moonhotels_practica.model.HotelLegsRequest;
import com.moonhotels.moonhotels_practica.model.HubRequest;

import java.time.temporal.ChronoUnit;

public class HotelLegsAdapter {

    private HotelLegsAdapter(){}

    public static HotelLegsRequest convertToHotelLegs(HubRequest request) {
        HotelLegsRequest hotelLegsRequest = new HotelLegsRequest();
        hotelLegsRequest.setHotel(request.getHotelId());
        hotelLegsRequest.setCheckInDate(request.getCheckIn().toString());
        hotelLegsRequest.setNumberOfNights((int) ChronoUnit.DAYS.between(request.getCheckIn(), request.getCheckOut()));
        hotelLegsRequest.setGuests(request.getNumberOfGuests());
        hotelLegsRequest.setRooms(request.getNumberOfRooms());
        hotelLegsRequest.setCurrency(request.getCurrency());
        return hotelLegsRequest;
    }


}
