package com.recipeapp.model;

import java.util.ArrayList;

public class Recipe {
    private String name; // レシピの名前
    private ArrayList<Ingredient> ingredients; // レシピの材料リスト

    // コンストラクタ
    public Recipe(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    // nameフィールドを返すメソッド
    public String getName() {
        return name;
    }

    // ingredientsフィールドを返すメソッド
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}