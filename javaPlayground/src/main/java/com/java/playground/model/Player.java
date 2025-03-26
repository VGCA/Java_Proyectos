package com.java.playground.model;

public class Player {
    private int maxHealth;
    private int currentHealth;
    private int defense;

    public Player(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.defense = 0;
    }

    // Apply damage considering defense
    public void takeDamage(int damage) {
        int remainingDamage = damage;

        // First reduce defense
        if (defense > 0) {
            int defenseReduction = Math.min(defense, remainingDamage);
            defense -= defenseReduction;
            remainingDamage -= defenseReduction;
        }

        // Then reduce health if there's remaining damage
        if (remainingDamage > 0) {
            currentHealth = Math.max(0, currentHealth - remainingDamage);
        }
    }

    public void addDefense(int defensePoints) {
        this.defense += defensePoints;
    }

    public boolean isDefeated() {
        return currentHealth <= 0;
    }

    // Getters
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public int getDefense() { return defense; }

    @Override
    public String toString() {
        return String.format("Health: %d/%d | Defense: %d",
                currentHealth, maxHealth, defense);
    }
}

