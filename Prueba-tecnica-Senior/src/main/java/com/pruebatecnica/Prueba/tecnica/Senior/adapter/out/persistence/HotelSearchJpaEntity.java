package com.pruebatecnica.prueba.tecnica.senior.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

/**
 * Persistence model (JPA). Not the domain model.
 * @Entity(name = "HotelSearch") keeps the JPA entity name, so Hibernate still maps the
 * ages collection to the same table as before and the existing schema is untouched.
 */
@Entity(name = "HotelSearch")
@Table(name = "hotel_searches")
public class HotelSearchJpaEntity {

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

    protected HotelSearchJpaEntity() {
        // required by JPA
    }

    public HotelSearchJpaEntity(String searchId, String hotelId, String checkIn,
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
