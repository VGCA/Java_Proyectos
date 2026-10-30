package com.pruebatecnica.prueba.tecnica.senior.application.port.in;

import java.util.List;

/** Data needed to create a search (input of the use case, independent of HTTP). */
public record CreateSearchCommand(String hotelId, String checkIn, String checkOut, List<Integer> ages) {
}
