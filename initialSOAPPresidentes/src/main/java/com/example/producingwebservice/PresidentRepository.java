package com.example.producingwebservice;

import io.spring.guides.gs_producing_web_service.President;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PresidentRepository {
    private static final Map<String, President> listPresident = new HashMap<>();

    @PostConstruct
    public void initData() {
        President spain = new President();
        spain.setName("Sanchez");
        spain.setAge(42);

        listPresident.put(spain.getName(), spain);

        President poland = new President();
        poland.setName("Jaime");
        poland.setAge(54);

        listPresident.put(poland.getName(), poland);

        President uk = new President();
        uk.setName("Charles");
        uk.setAge(63);

        listPresident.put(uk.getName(), uk);
    }

    public President findPresident(String name) {
        Assert.notNull(name, "The president's name must not be null");
        return listPresident.get(name);
    }
}
