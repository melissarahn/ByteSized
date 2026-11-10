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
public class AthleteUser extends UserProfile {
    private String athleticIntensity;
    private double totalProteinTarget;
    
    public AthleteUser(String id, String name, String[] affirmativeStatements, String athleticIntensity, double totalProteinTarget) {
        super(id, name, affirmativeStatements);
        this.athleticIntensity = athleticIntensity;
        this.totalProteinTarget = totalProteinTarget;
    }
    
    // habit configuration for high performance
    @Override
    public Habit configureHabitStrategy() {
        return null;
    }
    
    @Override
    public String getRestrictions() {
        return "High energy expenditure profile. Intensity Level: " + athleticIntensity;
    }
    
    public String getAthleticIntensity() {
        return athleticIntensity;
    }
    
    public double getTotalProteinTarget() {
        return totalProteinTarget;
    }
    
}
