package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Recipe;
import com.recipeapp.model.Ingredient;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {
        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe(); // 新しいレシピを追加
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        try {
            // データを ArrayList<Recipe> 型の recipes に格納
            ArrayList<Recipe> recipes = dataHandler.readData(); // データを読み込む
            if (recipes.isEmpty()) {
                System.out.println("No recipes available."); // レシピが存在しない場合
            } else {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                for (Recipe recipe : recipes) { //forループで各レシピの名前と材料を表示
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.println("Main Ingredients: " + String.join(", ", recipe.getIngredients().stream().map(ingredient -> ingredient.getName()).toArray(String[]::new)));
                    System.out.println("-----------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage()); // 例外処理
        }
    }

    private void addNewRecipe() {
        try {
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();//レシピ名を取得し、recipeNameに保存

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");
            while (true) {
                System.out.print("Ingredient: ");
                String ingredientInput = reader.readLine();
                if (ingredientInput.equalsIgnoreCase("done")) {
                    break; // 入力が "done" の場合、ループを終了
                }
                ingredients.add(new Ingredient(ingredientInput)); // 新しい材料を追加
            }

            // Recipeオブジェクトを作成し、データハンドラーを使ってファイルに書き込む
            Recipe newRecipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(newRecipe);
            System.out.println("Recipe added successfully."); // 成功メッセージを表示
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage()); // エラーメッセージを表示
        }
    }
}


    
