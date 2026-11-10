/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bytesized.model.user;

import com.mycompany.bytesized.model.habit.Habit;
/**
 *
 * @author melissarahn
 */
public class StandardUser extends UserProfile {
    private double waterGoal;
    private int dailyActivityMinutes;
    
    public StandardUser(String id, String name, String[] affirmativeStatements, double waterGoal, int dailyActivityMinutes) {
        super(id, name, affirmativeStatements);
        this.waterGoal = waterGoal;
        this.dailyActivityMinutes = dailyActivityMinutes;
    }
    
    // returns a standard daily habit routine
    @Override
    public Habit configureHabitStrategy() {
        return null;
    }
    
    @Override
    public String getRestrictions() {
        return "Standard balanced diet without custom clinical restrictions.";
    }
    
    public double getWaterGoal(){
        return waterGoal;
    }
    
    public int getDailyActivityMinutes() {
        return dailyActivityMinutes;
    }
    
}
