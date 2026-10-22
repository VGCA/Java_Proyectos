package com.java.pruebas.model;

// Define a record to structure our header evaluation rules
public record HeaderCheck(String headerName, String description, boolean shouldBePresent) {}