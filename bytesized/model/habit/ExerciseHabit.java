/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bytesized.model.habit;

/**
 *
 * @author melissarahn
 */
public class ExerciseHabit extends Habit {
    private int targetDurationMinutes;
    private int currentMinutesLogged;
    
    public ExerciseHabit(String name, int frequencyPerDay, int targetDurationMinutes) {
        super(name, frequencyPerDay);
        this.targetDurationMinutes = targetDurationMinutes;
        this.currentMinutesLogged = 0;
    }
    
    @Override
    public double calculateProgress() {
        if (targetDurationMinutes <= 0) return 0.0;
        double progress = (double) currentMinutesLogged / targetDurationMinutes;
        return Math.min(progress, 1.0);
    }
    
    public void logExercise(int minutes) {
        if (minutes > 0) {
            this.currentMinutesLogged += minutes;
        }
    }
    
    public int getCurrentMinutesLogged() {
        return currentMinutesLogged;
    }
    
    public int getTargetDurationMinutes() {
        return targetDurationMinutes;
    }
    
}
