# ByteSized
# 1. Project Overview
**Project Name** ByteSized
**Team Name** MelBee Tech
**Team Member** Melissa Rahn

### Motivation & Vision
My primary motivation for developing ByteSized comes from my academic background. My first degree is in dietetics, and I am currently working on my second degree in computer science. During my time working in the field of nutrition, I saw firsthand that traditional nutrition tracking tools often do more harm than good, leading into obsessive calorie counting, mental overwhelm, and abandoned health goals. The real key to long-term health isn’t about obsessing over mirco/macro nutrients rather than building sustainable daily habits. 

Now studying computer science, I realize that software engineering can help resolve this issue. I want to build ByteSized to create a wellness companion that shifts focus from restrictive logging and toward ‘byte-sized’ routines. In practice, ByteSized will be used as a desktop application centered around a clean, stress-free user dashboard. 

### Description
A wellness companion designed to simplify healthy living through actionable, living-tech habits. By transforming complex nutritional data into "byte-sized" steps, the goal is to take away the feeling of being overwhelmed with microscopic calorie counting. The application focuses on helping people build long-term, actionable habits.

# 2. UML Diagram

classDiagram
    class UserProfile {
        <<abstract>>
        -String id
        -String name
        -int baseCalories
        -String[] dailyAffirmations
        +String getId()
        +String getName()
        +String getRandomAffirmation()
        +String calculateAdjustedMacros()*
        +String getDietaryRestrictions()*
    }

    class StandardUser {
        -String[] preferences
        -double waterGoal
        +String calculateAdjustedMacros()
        +String getDietaryRestrictions()
    } 
    class AthleteUser {
        -String intensity
        -double proteinTarget
        +String calculateAdjustedMacros()
        +String getDietaryRestrictions()
    }

    class MedicalDietUser {
        -String[] allergens
        -String condition
        +String calculateAdjustedMacros()
        +String getDietaryRestrictions()
        +boolean isSafe(String foodItem)
    }

    class RecipeNode {
        -String itemName
        -int calories
        -ArrayList subComponents
        +void addComponent(RecipeNode component)
        +int getTotalCaloriesRecursive()
    }
UserProfile <|-- StandardUser
    UserProfile <|-- AthleteUser
    UserProfile <|-- MedicalDietUser
    RecipeNode "1" *-- "many" RecipeNode
