package com.moonhotels.Moonhotels_Practica.model;
import java.util.List;

public class HubResponse {

    private List<Room> rooms;

    public HubResponse(List<Room> rooms) {
        this.rooms = rooms;
    }

    public HubResponse(){}

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public static class Room {
        private int roomId;
        private List<Rate> rates;

        public Room(int roomId, List<Rate> rates) {
            this.roomId = roomId;
            this.rates = rates;
        }
        public Room(){}

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public List<Rate> getRates() {
            return rates;
        }

        public void setRates(List<Rate> rates) {
            this.rates = rates;
        }
    }

    public static class Rate {
        private int mealPlanId;
        private boolean isCancellable;
        private double price;

        public Rate(int mealPlanId, boolean isCancellable, double price) {
            this.mealPlanId = mealPlanId;
            this.isCancellable = isCancellable;
            this.price = price;
        }

        public Rate(){}

        public int getMealPlanId() {
            return mealPlanId;
        }

        public void setMealPlanId(int mealPlanId) {
            this.mealPlanId = mealPlanId;
        }

        public boolean isCancellable() {
            return isCancellable;
        }

        public void setCancellable(boolean cancellable) {
            isCancellable = cancellable;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
