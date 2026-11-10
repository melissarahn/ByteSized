/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bytesized.model.recipe;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author melissarahn
 */
public class RecipeNode {
    private String name;
    private double calories;
    private double proteinGrams;
    private double carbsGrams;
    private double fatGrams;
    private List<RecipeNode> ingredients;
    
    // simple food item
    public RecipeNode(String name, double calories, double proteinGrams, double carbsGrams, double fatGrams) {
        this.name = name;
        this.calories = calories;
        this.proteinGrams = proteinGrams;
        this.carbsGrams = carbsGrams;
        this.fatGrams = fatGrams;
        this.ingredients = new ArrayList<>();
    }
    
    // recipe
    public RecipeNode(String name) {
        this(name, 0.0, 0.0, 0.0, 0.0);
    }
    
    public void addIngredient(RecipeNode node) {
        if (node != null) {
            ingredients.add(node);
        }
    }
    
    public double getTotalCalories() {
        if (ingredients.isEmpty()) {
            return calories;
        }
        double total = 0.0;
        for (RecipeNode ingredient : ingredients) {
            total += ingredient.getTotalCalories();
        }
        return total;
    }
    
    public double getTotalProtein() {
        if (ingredients.isEmpty()) {
            return proteinGrams;
        }
        double total = 0.0;
        for (RecipeNode ingredient : ingredients) {
            total += ingredient.getTotalProtein();
        }
        return total;
    }
    
    public double getTotalCarbs() {
        if (ingredients.isEmpty()) {
            return carbsGrams;
        }
        double total = 0.0;
        for (RecipeNode ingredient : ingredients) {
            total += ingredient.getTotalCarbs();
        }
        return total;
    }
    
    public double getTotalFat() {
        if (ingredients.isEmpty()) {
            return fatGrams;
        }
        double total = 0.0;
        for (RecipeNode ingredient : ingredients) {
            total += ingredient.getTotalFat();
        }
        return total;
    }
    
    public List<RecipeNode> getIngredients() {
        return ingredients;
    }
    
}
