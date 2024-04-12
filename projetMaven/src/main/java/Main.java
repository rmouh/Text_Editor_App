import Controler.Controler;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        //Vue vue = new Vue();
        Controler controler = new Controler(primaryStage);
        //FirstPage(vue, controler, primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

