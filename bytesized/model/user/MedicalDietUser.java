/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bytesized.model.user;

import com.mycompany.bytesized.model.habit.Habit;
import java.util.Arrays;
/**
 *
 * @author melissarahn
 */
public class MedicalDietUser extends UserProfile {
    private String[] allergenList;
    private String condition;
    
    public MedicalDietUser(String id, String name, String[] affirmativeStatements, String[] allergenList, String condition) {
        super(id, name, affirmativeStatements);
        this.allergenList = allergenList;
        this.condition = condition;
    }
    
    //configures habits tailored for user
    @Override
    public Habit configureHabitStrategy() {
        return null;
    }
    
    @Override
    public String getRestrictions() {
        return "Medical Condition: " + condition + " | Exluded Allergens: " + Arrays.toString(allergenList);
    }
    
    // safety filter 
    public boolean isSafe(String foodItem) {
        if (allergenList == null || foodItem == null) {
            return true;
        }
        for (String allergen : allergenList) {
            if (foodItem.equalsIgnoreCase(allergen) || foodItem.toLowerCase().contains(allergen.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
    
    public String[] getAllergenList() {
        return allergenList;
    }
    
    public String getCondition() {
        return condition;
    }
    
}
