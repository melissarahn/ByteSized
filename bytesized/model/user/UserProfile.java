/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bytesized.model.user;

import com.mycompany.bytesized.model.habit.Habit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author melissarahn
 */

public abstract class UserProfile {
    private String id;
    private String name;
    private String[] affirmativeStatements;
    private List<Habit> currentHabitPlan;
    
    public UserProfile(String id, String name, String[] affirmativeStatements) {
        this.id = id;
        this.name = name;
        this.affirmativeStatements = affirmativeStatements;
        this.currentHabitPlan = new ArrayList<>();
    }
    
    // getters and setters
    public String getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Habit> getCurrentHabits() {
        return currentHabitPlan;
    } 
    
    public void addHabit(Habit habit) {
        if (habit != null) {
            this.currentHabitPlan.add(habit);
        }
    }

// affirmation generator
public String getPersonalReinforcement() {
    if (affirmativeStatements == null || affirmativeStatements.length == 0) {
        return "Small daily habits lead to long-term well-being!";
    }
    Random rand = new Random();
    return affirmativeStatements[rand.nextInt(affirmativeStatements.length)];
}

// abstract methods
public abstract Habit configureHabitStrategy();
public abstract String getRestrictions(); 

}
