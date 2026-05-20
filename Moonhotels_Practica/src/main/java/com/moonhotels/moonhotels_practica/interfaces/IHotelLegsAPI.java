package com.moonhotels.moonhotels_practica.interfaces;

import com.moonhotels.moonhotels_practica.model.HotelLegsRequest;
import com.moonhotels.moonhotels_practica.model.HotelLegsResponse;

public interface IHotelLegsAPI {
    HotelLegsResponse search(HotelLegsRequest request);
}
