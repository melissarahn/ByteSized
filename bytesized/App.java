package com.mycompany.bytesized;

import com.mycompany.bytesized.model.user.UserProfile;
import com.mycompany.bytesized.model.user.MedicalDietUser;
import com.mycompany.bytesized.model.user.AthleteUser;
import com.mycompany.bytesized.model.user.StandardUser;
import com.mycompany.bytesized.model.habit.HydrationHabit;
import com.mycompany.bytesized.model.recipe.RecipeNode;
import com.mycompany.bytesized.model.habit.ExerciseHabit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.ChoiceBox;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private UserProfile currentUser;
    private HydrationHabit waterHabit;
    private ExerciseHabit exerciseHabit;
    private RecipeNode dailyMealTracker;
    
    // profile setup
    private TextField userIdField;
    private TextField userNameField;
    private ChoiceBox<String> profileTypeBox;
    private TextField allergensField;
    private Label userNameLabel;
    private Label affirmationLabel;
    
    // hydration
    private ProgressBar waterProgressBar;
    private Label waterStatusLabel;
    
    // safety check
    private Label userRestrictionsLabel;
    private TextField foodInputField;
    private Label safetyResultLabel;
    
    // exercise elements
    private ProgressBar exerciseProgressBar;
    private Label exerciseStatusLabel;
    private TextField exerciseInputField;
    
    // macro tracker
    private TextField recipeNameField;
    private TextField caloriesField;
    private TextField proteinField;
    private TextField carbsField;
    private TextField fatField;
    private Label macroSummaryLabel;
    
    // action
    private Button saveAllButton;
    private Button resetDayButton;
    private Label globalStatusLabel;
    
    @Override
    public void start(Stage primaryStage) {
        String[] affirmations = {
            "Consistency over perfection.",
            "Nourish you body with care every day.",
            "Small habits build long-term wellness."
        };
        String[] allergens = {"Peanut", "Shellfish", "Gluten"};
        
        currentUser = new MedicalDietUser("U001", "Melissa", affirmations, allergens, "Medical Profile");
        waterHabit = new HydrationHabit("Drink 2.5L Water Daily", 1, 2.5);
        exerciseHabit = new ExerciseHabit("Daily Movement / Workout", 1, 30);
        dailyMealTracker = new RecipeNode("Daily Log");
        
        // user input
        Label setupHeader = new Label("Profile Setup: ");
        
        userIdField = new TextField();
        userIdField.setPromptText("User ID");
        userIdField.setPrefWidth(80);
        
        userNameField = new TextField();
        userNameField.setPromptText("Enter you name");
        userNameField.setPrefWidth(110);
        
        profileTypeBox = new ChoiceBox<>();
        profileTypeBox.getItems().addAll("Medical Diet", "Athlete", "Standard User");
        profileTypeBox.setValue("Medical Diet");
        
        allergensField = new TextField();
        allergensField.setPromptText("Allergens / Intensity");
        allergensField.setPrefWidth(150);
        
        Button saveProfileButton = new Button("Save Profile");
        saveProfileButton.setOnAction(e -> handleSaveProfile(affirmations));
        
        HBox profileInputBox = new HBox(10, userIdField, userNameField, profileTypeBox, allergensField, saveProfileButton);
        profileInputBox.setAlignment(Pos.CENTER_LEFT);
        
        VBox setupSection = new VBox(5, setupHeader, profileInputBox);
        
        // header
        userNameLabel = new Label("Welcome, " + currentUser.getName() + "!");
        HBox headerBox = new HBox(userNameLabel);
        headerBox.setAlignment(Pos.CENTER_LEFT);
        
        // affirmations
        Label affirmationHeader = new Label("Daily Affirmation: ");
        affirmationLabel = new Label("\"" + currentUser.getPersonalReinforcement() + "\"");
        VBox affirmationBox = new VBox(5, affirmationHeader, affirmationLabel);
        
        // Hydration tracket
        Label waterHeader = new Label("Hydration Progress: ");
        waterProgressBar = new ProgressBar(0.0);
        waterProgressBar.setPrefWidth(200);
        
        Button logWaterButton = new Button("Log Water");
        logWaterButton.setOnAction(e -> handleLogWater());
        
        waterStatusLabel = new Label();
        updateWaterProgress();
        
        HBox waterControlsBox = new HBox(10, waterProgressBar, logWaterButton);
        waterControlsBox.setAlignment(Pos.CENTER_LEFT);
        
        VBox hydrationBox = new VBox(5, waterHeader, waterControlsBox, waterStatusLabel);
        
        // exercise
        Label exerciseHeader = new Label("Exercise Progress: ");
        exerciseProgressBar = new ProgressBar(0.0);
        exerciseProgressBar.setPrefWidth(200);
        
        exerciseInputField = new TextField();
        exerciseInputField.setPromptText("Mins");
        exerciseInputField.setPrefWidth(80);
        
        Button logExerciseButton = new Button("Log Exercise");
        logExerciseButton.setOnAction(e -> handleLogExercise());
        
        exerciseStatusLabel = new Label();
        updateExerciseProgress();
        
        HBox exerciseControlsBox = new HBox(10, exerciseProgressBar, exerciseInputField, logExerciseButton);
        exerciseControlsBox.setAlignment(Pos.CENTER_LEFT);
        
        VBox exerciseBox = new VBox(5, exerciseHeader, exerciseControlsBox, exerciseStatusLabel);
        
        // allergen safety check
        Label safetyHeader = new Label("Check Food Safety: ");
        userRestrictionsLabel = new Label(currentUser.getRestrictions());
        
        foodInputField = new TextField();
        foodInputField.setPromptText("Enter food item");
        
        Button checkSafetyButton = new Button("Check");
        checkSafetyButton.setOnAction(e -> handleCheckSafety());
        
        safetyResultLabel = new Label();
        
        HBox safetyControlsBox = new HBox(10, foodInputField, checkSafetyButton);
        safetyControlsBox.setAlignment(Pos.CENTER_LEFT);
        
        VBox safetyBox = new VBox(5, safetyHeader, userRestrictionsLabel, safetyControlsBox, safetyResultLabel);
        
        // macro tracker
        Label macroHeader = new Label("Meal & Macro Tracker: ");
        
        recipeNameField = new TextField();
        recipeNameField.setPromptText("Meal Item");
        recipeNameField.setPrefWidth(110);
        
        caloriesField = new TextField();
        caloriesField.setPromptText("Cals");
        caloriesField.setPrefWidth(55);
        
        proteinField = new TextField();
        proteinField.setPromptText("Protein(g)");
        proteinField.setPrefWidth(70);
        
        carbsField = new TextField();
        carbsField.setPromptText("Carbs(g)");
        carbsField.setPrefWidth(65);
        
        fatField = new TextField();
        fatField.setPromptText("Fats (g)");
        fatField.setPrefWidth(55);
        
        Button logMealButton = new Button("Log Meal");
        logMealButton.setOnAction(e -> handleLogMeal());
        
        HBox macroInputBox = new HBox(8, recipeNameField, caloriesField, proteinField, carbsField, fatField, logMealButton);
        macroInputBox.setAlignment(Pos.CENTER_LEFT);
        
        macroSummaryLabel = new Label();
        updateMacroSummary();
        
        VBox macroBox = new VBox(5, macroHeader, macroInputBox, macroSummaryLabel);
        
        // save / reset
        saveAllButton = new Button("Save Data");
        saveAllButton.setOnAction(e -> handleSaveAll());
        
        resetDayButton = new Button("Reset Day");
        resetDayButton.setOnAction(e -> handleResetDay());
        
        globalStatusLabel = new Label();
        
        HBox actionControlsBox = new HBox(15, saveAllButton, resetDayButton, globalStatusLabel);
        actionControlsBox.setAlignment(Pos.CENTER_LEFT);
        
        VBox actionBox = new VBox(5, actionControlsBox);
        
        // main layout
        VBox mainLayout = new VBox(15, setupSection, headerBox, affirmationBox, hydrationBox, exerciseBox, safetyBox, macroBox, actionBox);
        mainLayout.setPadding(new Insets(15));
        
        // scene setup
        Scene scene = new Scene(mainLayout, 700, 710);
        primaryStage.setTitle("ByteSized - Desktop Wellness");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // background
        mainLayout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        // button colors and rounded corners
        Background blueCard = new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(8), Insets.EMPTY));
        hydrationBox.setBackground(blueCard);
        
        Background pinkCard = new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(8), Insets.EMPTY));
        safetyBox.setBackground(pinkCard);
        
        Background purpleCard = new Background(new BackgroundFill(Color.THISTLE, new CornerRadii(8), Insets.EMPTY));
        affirmationBox.setBackground(purpleCard);
        
        Background greenCard = new Background(new BackgroundFill(Color.web("#B5EAD7"), new CornerRadii(8), Insets.EMPTY));
        macroBox.setBackground(greenCard);
        
        Background grayCard = new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(8), Insets.EMPTY));
        setupSection.setBackground(grayCard);
        
        Background orangeCard = new Background(new BackgroundFill(Color.LEMONCHIFFON, new CornerRadii(8), Insets.EMPTY));
        exerciseBox.setBackground(orangeCard);
    }
    
    private void handleSaveProfile(String[] affirmations) {
        String inputId = userIdField.getText().trim();
        String inputName = userNameField.getText().trim();
        String selectedType = profileTypeBox.getValue();
        String rawAllergens = allergensField.getText().trim();
        
        if (inputId.isEmpty()) inputId = "U001";
        if (inputName.isEmpty()) inputName = "User";
        
        if ("Athlete".equals(selectedType)) {
            String intensity = rawAllergens.isEmpty() ? "High Intensity (Endurance)" : rawAllergens;
            double proteinTarget = 150.0;
            currentUser = new AthleteUser(inputId, inputName, affirmations, intensity, proteinTarget);
        } else if ("Standard User".equals(selectedType)) {
            currentUser = new StandardUser(inputId, inputName, affirmations, 2.5, 30);
        } else {
            String[] parsedAllergens = rawAllergens.isEmpty() ? new String[]{} : rawAllergens.split("\\s*,\\s*");
            currentUser = new MedicalDietUser(inputId, inputName, affirmations, parsedAllergens, "Medical Profile");
        }
        
        // new views
        userNameLabel.setText("Welcome, " + currentUser.getName() + "!");
        affirmationLabel.setText("\"" + currentUser.getPersonalReinforcement() + "\"");
        userRestrictionsLabel.setText(currentUser.getRestrictions());
        safetyResultLabel.setText("");
        globalStatusLabel.setText("✓ Profile saved successfully!");
        globalStatusLabel.setTextFill(Color.DARKGREEN);
    }
    
    private void handleLogWater() {
        waterHabit.logWater(0.5);
        updateWaterProgress();
    }
    
    private void updateWaterProgress() {
        double progress = waterHabit.calculateProgress();
        waterProgressBar.setProgress(progress);
        waterStatusLabel.setText(String.format("%.1f L / %.1f L logged", waterHabit.getCurrentVolume(), waterHabit.getDailyTargetVolume()));
    }
    
    private void handleLogExercise() {
        String inputMins = exerciseInputField.getText().trim();
        if (inputMins.isEmpty()) {
            exerciseStatusLabel.setText("Please enter minutes to log.");
            return;
        }
        
        try {
            int minutes = Integer.parseInt(inputMins);
            exerciseHabit.logExercise(minutes);
            exerciseInputField.clear();
            updateExerciseProgress();
        } catch (NumberFormatException e) {
            exerciseStatusLabel.setText("Please enter vaild numeric minutes.");
        }
    }
    
    private void updateExerciseProgress() {
        double progress = exerciseHabit.calculateProgress();
        exerciseProgressBar.setProgress(progress);
        exerciseStatusLabel.setText(String.format("%d mins / %d mins logged",
                exerciseHabit.getCurrentMinutesLogged(),
                exerciseHabit.getTargetDurationMinutes()));
    }
    
    private void handleCheckSafety() {
        String inputFood = foodInputField.getText().trim();
        if (inputFood.isEmpty()) {
            safetyResultLabel.setText("Please enter an ingredient to check.");
            return;
        }
        if (currentUser instanceof MedicalDietUser) {
            boolean safe = ((MedicalDietUser) currentUser).isSafe(inputFood);
            if (safe) {
                safetyResultLabel.setText("✓ " + inputFood + " is safe for your profile!");
            } else {
                safetyResultLabel.setText("⚠ Warning: " + inputFood + " contains a restricted allergen!");
            }
        } else {
            safetyResultLabel.setText("✓ " + inputFood + " is logged for your athletic profile!");
        }
    }
    
    private void handleLogMeal() {
        String item = recipeNameField.getText().trim();
        if (item.isEmpty()) {
            item = "Meal Item";
        }
        
        try {
            double cals = caloriesField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(caloriesField.getText().trim());
            double protein = proteinField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(proteinField.getText().trim());
            double carbs = carbsField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(carbsField.getText().trim());
            double fat = fatField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(fatField.getText().trim());
            
            RecipeNode meal = new RecipeNode(item, cals, protein, carbs, fat);
            dailyMealTracker.addIngredient(meal);
            
            recipeNameField.clear();
            caloriesField.clear();
            proteinField.clear();
            carbsField.clear();
            fatField.clear();
            
            updateMacroSummary();
        } catch (NumberFormatException e) {
            macroSummaryLabel.setText(" Please enter valid numeric values for macros.");
        }
    
    }
       
        private void updateMacroSummary() {
            macroSummaryLabel.setText(String.format(
                    "Daily Totals - Cals: %.0f kcal | Protein: %.1fg | Carbs: %.1fg | Fat: %.1fg",
                    dailyMealTracker.getTotalCalories(),
                    dailyMealTracker.getTotalProtein(),
                    dailyMealTracker.getTotalCarbs(),
                    dailyMealTracker.getTotalFat()
            ));
        }
        
        private void handleSaveAll() {
            globalStatusLabel.setText("✓ All profiles and daily logs saved successfully!");
            globalStatusLabel.setTextFill(Color.DARKGREEN);
        }
        
        private void handleResetDay() {
            waterHabit = new HydrationHabit("Drink 2.5L Water Daily", 1, 2.5);
            exerciseHabit = new ExerciseHabit("Daily Movement / Workout", 1, 30);
            dailyMealTracker = new RecipeNode("Daily Log");
            
            foodInputField.clear();
            recipeNameField.clear();
            caloriesField.clear();
            proteinField.clear();
            carbsField.clear();
            fatField.clear();
            exerciseInputField.clear();
            safetyResultLabel.setText("");
            
            updateWaterProgress();
            updateExerciseProgress();
            updateMacroSummary();
            
            globalStatusLabel.setText("🔄 Day reset! Ready for a fresh start.");
            globalStatusLabel.setTextFill(Color.DARKRED);
        }

    public static void main(String[] args) {
        launch(args);
    }
}


