package com.moonhotels.Moonhotels_Practica.adapterTest;

import com.moonhotels.Moonhotels_Practica.adapter.HotelLegsAdapter;
import com.moonhotels.Moonhotels_Practica.model.HotelLegsRequest;
import com.moonhotels.Moonhotels_Practica.model.HubRequest;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class HotelLegsAdapterTest {

    @Test
    void shouldConvertHubRequestToHotelLegsRequest() {

        HubRequest hubRequest = new HubRequest();
        hubRequest.setHotelId(1);
        hubRequest.setCheckIn(LocalDate.of(2025, 3, 1));
        hubRequest.setCheckOut(LocalDate.of(2025, 3, 6)); // 5 nights
        hubRequest.setNumberOfGuests(3);
        hubRequest.setNumberOfRooms(2);
        hubRequest.setCurrency("USD");

        HotelLegsRequest hotelLegsRequest = HotelLegsAdapter.convertToHotelLegs(hubRequest);

        assertEquals(1, hotelLegsRequest.getHotel());
        assertEquals("2025-03-01", hotelLegsRequest.getCheckInDate());
        assertEquals(5, hotelLegsRequest.getNumberOfNights());
        assertEquals(3, hotelLegsRequest.getGuests());
        assertEquals(2, hotelLegsRequest.getRooms());
        assertEquals("USD", hotelLegsRequest.getCurrency());
    }
}
