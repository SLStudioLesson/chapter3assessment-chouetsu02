import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            DataHandler dataHandler; // DataHandlerインスタンスを格納する変数
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");

            // ユーザの選択を取得
            String choice = reader.readLine();

            // ユーザーの選択に応じてデータハンドラーのインスタンスを生成
            if (choice.equals("1")) {
                dataHandler = new CSVDataHandler(); // CSVデータハンドラーを生成
            } else if (choice.equals("2")) {
                dataHandler = new JSONDataHandler(); // JSONデータハンドラーを生成
            } else {
                // 不正な入力の場合、CSVDataHandlerインスタンスを生成する
                dataHandler = new CSVDataHandler();
            }

            // 選択されたデータハンドラーのモードを表示
            System.out.println("Current mode: " + dataHandler.getMode());

            // RecipeUIインスタンスを生成し、選択されたデータハンドラーを渡す
            RecipeUI recipeUI = new RecipeUI(dataHandler);
            
            // メインメニューを表示
            recipeUI.displayMenu();
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}