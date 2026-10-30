package com.pruebatecnica.prueba.tecnica.senior.adapter.in.web.dto;

import java.util.List;

public record SearchRequest(String hotelId, String checkIn, String checkOut, List<Integer> ages) {
}
