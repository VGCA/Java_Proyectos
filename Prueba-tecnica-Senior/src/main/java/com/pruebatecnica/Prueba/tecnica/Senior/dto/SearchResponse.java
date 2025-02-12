package com.pruebatecnica.Prueba.tecnica.Senior.dto;

public class SearchResponse {

    public SearchResponse(String searchId) {
        this.searchId = searchId;
    }

    private String searchId;

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
}
