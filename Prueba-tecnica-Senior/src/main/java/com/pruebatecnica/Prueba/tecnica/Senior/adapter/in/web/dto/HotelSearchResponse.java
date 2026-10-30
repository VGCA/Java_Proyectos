package com.pruebatecnica.prueba.tecnica.senior.adapter.in.web.dto;

import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;

import java.util.List;

/** JSON shape of GET /count. Same fields the old entity exposed. */
public record HotelSearchResponse(String searchId, String hotelId, String checkIn,
                                  String checkOut, List<Integer> ages, int count) {

    public static HotelSearchResponse from(HotelSearch search) {
        return new HotelSearchResponse(search.getSearchId(), search.getHotelId(),
                search.getCheckIn(), search.getCheckOut(), search.getAges(), search.getCount());
    }
}
