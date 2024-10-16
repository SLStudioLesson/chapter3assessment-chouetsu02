package com.recipeapp.datahandler;

import com.recipeapp.model.Recipe;
import com.recipeapp.model.Ingredient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVDataHandler implements DataHandler {
    private String filePath; //レシピデータを格納するcsvファイルのパス

    // デフォルトコンストラクタ
    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    // 引数を受け取るコンストラクタ
    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();

        // recipes.csvファイルを読み込む
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // 各行を読み込み、Recipeオブジェクトを作成
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2); // レシピ名と材料に分ける
                String name = parts[0].trim();
                String[] ingredients = parts[1].split(","); // 材料をカンマで分割
                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                for (String ingredient : ingredients) {
                    ingredientList.add(new Ingredient(ingredient.trim())); // Ingredientオブジェクトを作成
                }
                recipes.add(new Recipe(name, ingredientList)); // Recipeオブジェクトを追加
            }
        }
        return recipes; // 読み込んだレシピのリストを返す
    }
        
    

    // 新しいレシピをrecipes.csvに追加
    public void writeData(Recipe recipe) throws IOException {
        // レシピの名前と材料をカンマ区切りで1行にしてファイルに書き込む処理
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // レシピ名を取得
            StringBuilder line = new StringBuilder(recipe.getName());

            // 材料をカンマ区切りで追加
            for (Ingredient ingredient : recipe.getIngredients()) {
                line.append(",").append(ingredient.getName());
            }

            // 書き込む行をファイルに追加
            writer.write(line.toString());
            writer.newLine(); // 新しい行を追加
        } catch (IOException e) {
            // IOExceptionが発生した場合は再スロー
            throw new IOException("Error writing to file: " + e.getMessage());
        }
    }


    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        // 実装は後で行うので、nullを返す
        return null;
    }
}

    

