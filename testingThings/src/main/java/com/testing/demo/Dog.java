package com.testing.demo;

public class Dog {

    private int legs;
    private String sound;
    private String name;

    public Dog(int legs, String sound, String name) {
        this.legs = legs;
        this.sound = sound;
        this.name = name;
    }

    public Dog createDog(Dog newDog){
        return newDog;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
