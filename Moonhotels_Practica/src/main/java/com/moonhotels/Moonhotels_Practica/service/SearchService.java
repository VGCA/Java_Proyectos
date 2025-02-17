package com.moonhotels.Moonhotels_Practica.service;

import com.moonhotels.Moonhotels_Practica.adapter.HotelLegsAdapter;
import com.moonhotels.Moonhotels_Practica.interfaces.IHotelLegsAPI;
import com.moonhotels.Moonhotels_Practica.model.HotelLegsResponse;
import com.moonhotels.Moonhotels_Practica.model.HubRequest;
import com.moonhotels.Moonhotels_Practica.model.HubResponse;
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
