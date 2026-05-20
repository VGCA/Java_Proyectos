package com.moonhotels.moonhotels_practica.service;

import com.moonhotels.moonhotels_practica.adapter.HotelLegsAdapter;
import com.moonhotels.moonhotels_practica.interfaces.IHotelLegsAPI;
import com.moonhotels.moonhotels_practica.model.HotelLegsResponse;
import com.moonhotels.moonhotels_practica.model.HubRequest;
import com.moonhotels.moonhotels_practica.model.HubResponse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final IHotelLegsAPI hotelLegsAPI;

    public SearchService(IHotelLegsAPI hotelLegsAPI) {
        this.hotelLegsAPI = hotelLegsAPI;
    }

    public HubResponse search(HubRequest request) {

        HotelLegsResponse providerResponse = hotelLegsAPI.search(HotelLegsAdapter.convertToHotelLegs(request));


        HubResponse response = new HubResponse();
        List<HubResponse.Room> rooms = new ArrayList<>();

        providerResponse.getResults().forEach(result -> {
            HubResponse.Room room = new HubResponse.Room();
            room.setRoomId(result.getRoom());

            HubResponse.Rate rate = new HubResponse.Rate();
            rate.setMealPlanId(result.getMeal());
            rate.setCancellable(result.isCanCancel());
            rate.setPrice(result.getPrice());

            rooms.add(room);
        });

        response.setRooms(rooms);
        return response;
    }
}
