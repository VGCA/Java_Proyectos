package com.pruebatecnica.prueba.tecnica.senior.domain.model;

import java.util.List;

/**
 * Domain model: a hotel availability search.
 * Plain Java on purpose: no JPA, no Jackson, no Spring annotations.
 */
public class HotelSearch {

    private final String searchId;
    private final String hotelId;
    private final String checkIn;
    private final String checkOut;
    private final List<Integer> ages;
    private final int count;

    public HotelSearch(String searchId, String hotelId, String checkIn,
                       String checkOut, List<Integer> ages, int count) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
        this.count = count;
    }

    public String getSearchId() { return searchId; }
    public String getHotelId() { return hotelId; }
    public String getCheckIn() { return checkIn; }
    public String getCheckOut() { return checkOut; }
    public List<Integer> getAges() { return ages; }
    public int getCount() { return count; }
}
