import Controler.Controler;
import Vue.Vue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Vue vue = new Vue();

        Controler controler = new Controler(primaryStage);

        //FirstPage(vue, controler, primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

