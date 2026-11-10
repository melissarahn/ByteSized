/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bytesized.model.habit;

/**
 *
 * @author melissarahn
 */
public class HydrationHabit extends Habit {
    private double dailyTargetVolume; // in liters
    private double currentVolume; // logged volume in liters
    
    public HydrationHabit(String name, int frequencyPerDay, double dailyTargetVolume) {
        super(name, frequencyPerDay);
        this.dailyTargetVolume = dailyTargetVolume;
        this.currentVolume = 0.0;
    }
    
    public void logWater(double liters) {
        if (liters > 0) {
        this.currentVolume += liters;
        }
    }
    
    @Override
    public double calculateProgress() {
        if (dailyTargetVolume <= 0) {
            return 0.0;
        }
        double progress = currentVolume / dailyTargetVolume;
        return progress > 1.0 ? 1.0 : progress; // cap progress at 100%
    }
    
    public double getDailyTargetVolume() {
        return dailyTargetVolume;
    }
    
    public double getCurrentVolume() {
        return currentVolume;
    }
    
    public void resetProgress() {
        this.currentVolume = 0.0;
    }
    
}
