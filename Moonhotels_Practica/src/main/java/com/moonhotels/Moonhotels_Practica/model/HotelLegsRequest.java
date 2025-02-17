package com.moonhotels.Moonhotels_Practica.model;

public class HotelLegsRequest {

    private int hotel;
    private String checkInDate;
    private int numberOfNights;
    private int guests;
    private int rooms;
    private String currency;

    public HotelLegsRequest(){}

    public HotelLegsRequest(int hotel, String checkInDate, int numberOfNights, int guests, int rooms, String currency) {
        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.numberOfNights = numberOfNights;
        this.guests = guests;
        this.rooms = rooms;
        this.currency = currency;
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
