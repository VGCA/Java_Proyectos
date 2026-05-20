package com.moonhotels.moonhotels_practica.provider;

import com.moonhotels.moonhotels_practica.interfaces.IHotelLegsAPI;
import com.moonhotels.moonhotels_practica.model.HotelLegsRequest;
import com.moonhotels.moonhotels_practica.model.HotelLegsResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class HotelLegsProvider implements IHotelLegsAPI {

    @Override
    public HotelLegsResponse search(HotelLegsRequest request) {

        HotelLegsResponse response = new HotelLegsResponse();
        response.setResults(Collections.singletonList(
                new HotelLegsResponse.Result(1, 1, false, 123.48)
        ));
        return response;
    }
}
