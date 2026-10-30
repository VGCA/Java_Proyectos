package com.pruebatecnica.prueba.tecnica.senior.adapter.messaging;

import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;

import java.util.List;

/** Wire format of the Kafka topic. Kept apart from the domain so the topic contract can evolve alone. */
public record SearchMessage(String searchId, String hotelId, String checkIn,
                            String checkOut, List<Integer> ages, int count) {

    public static SearchMessage from(HotelSearch s) {
        return new SearchMessage(s.getSearchId(), s.getHotelId(), s.getCheckIn(),
                s.getCheckOut(), s.getAges(), s.getCount());
    }

    public HotelSearch toDomain() {
        return new HotelSearch(searchId, hotelId, checkIn, checkOut, ages, count);
    }
}
