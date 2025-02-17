package com.moonhotels.Moonhotels_Practica.model;

import java.util.List;

public class HotelLegsResponse {
    private List<Result> results;

    public HotelLegsResponse(){}

    public HotelLegsResponse(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {
        private int room;
        private int meal;
        private boolean canCancel;
        private double price;

        public Result(int room, int meal, boolean canCancel, double price) {
            this.room = room;
            this.meal = meal;
            this.canCancel = canCancel;
            this.price = price;
        }

        public Result(){}

        public int getRoom() {
            return room;
        }

        public void setRoom(int room) {
            this.room = room;
        }

        public int getMeal() {
            return meal;
        }

        public void setMeal(int meal) {
            this.meal = meal;
        }

        public boolean isCanCancel() {
            return canCancel;
        }

        public void setCanCancel(boolean canCancel) {
            this.canCancel = canCancel;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
