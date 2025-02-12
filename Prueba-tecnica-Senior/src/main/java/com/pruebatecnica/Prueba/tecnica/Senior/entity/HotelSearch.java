package com.pruebatecnica.Prueba.tecnica.Senior.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel_searches")
public class HotelSearch {

    @Id
    private String searchId;

    @Column(nullable = false)
    private String hotelId;

    @Column(nullable = false)
    private String checkIn;

    @Column(nullable = false)
    private String checkOut;

    @ElementCollection
    private List<Integer> ages;

    @Column(nullable = false)
    private int count;

    public HotelSearch() {}

    public HotelSearch(String searchId, String hotelId, String checkIn, String checkOut, List<Integer> ages, int count) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
        this.count = count;
    }

    public String getSearchId() {
        return searchId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public List<Integer> getAges() {
        return ages;
    }

    public int getCount() {
        return count;
    }
}

