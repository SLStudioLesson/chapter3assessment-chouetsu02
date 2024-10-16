package com.recipeapp.datahandler;

import com.recipeapp.model.Recipe;
import java.io.IOException;
import java.util.ArrayList;

public class JSONDataHandler implements DataHandler {

    @Override
    public String getMode() {
        return "JSON";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        // 実装は後で行うので、nullを返す
        return null;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {
        // 実装は後で行うので、何もしない
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        // 実装は後で行うので、nullを返す
        return null;
    }
    
}
