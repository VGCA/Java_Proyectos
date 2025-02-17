package com.moonhotels.Moonhotels_Practica.interfaces;

import com.moonhotels.Moonhotels_Practica.model.HotelLegsRequest;
import com.moonhotels.Moonhotels_Practica.model.HotelLegsResponse;

public interface IHotelLegsAPI {
    HotelLegsResponse search(HotelLegsRequest request);
}
