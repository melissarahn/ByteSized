/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bytesized.model.habit;

/**
 *
 * @author melissarahn
 */
public abstract class Habit {
    private String name;
    private int frequencyPerDay;
    
    public Habit(String name, int frequencyPerDay) {
        this.name = name;
        this.frequencyPerDay = frequencyPerDay;
    }
    
    public abstract double calculateProgress();
    
    public String getName() {
        return name;
    }
    
    public int getFrequencyPerDay() {
        return frequencyPerDay;
    }
    
}
