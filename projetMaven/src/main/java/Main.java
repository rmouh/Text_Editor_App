import Controler.Controler;
import Vue.Vue;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Vue vue = new Vue();
        Controler controler = new Controler(vue);
        FirstPage(vue, controler, primaryStage);
    }
    public void FirstPage (Vue vue, Controler controler, Stage primaryStage)
    {
        // Créer les boutons dans Vue
        Button uploadOriginalFileButton = vue.createUploadButton("Upload Original File", 179.0, 1);
        Button uploadEditedFileButton = vue.createUploadButton("Upload Edited File", 179.0, 2);

        // Chargement de l'image depuis les ressources du projet
        Image image = new Image(getClass().getResourceAsStream("/icon_txt.png"));
        ImageView imageView1 = vue.createImageView(image, 150.0, 200.0, 1);
        ImageView imageView2 = vue.createImageView(image, 150.0, 200.0, 2);
        // Ajouter les boutons à la vue
        vue.addToView(uploadOriginalFileButton,uploadEditedFileButton, imageView1, imageView2 );


        // Créer la scène et ajouter les boutons de la vue à celle-ci
        Scene scene = new Scene(vue.getLayout(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Lier les boutons aux gestionnaires d'événements dans le contrôleur
        controler.handleUploadButtonClick(uploadOriginalFileButton);
        controler.handleUploadButtonClick(uploadEditedFileButton);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
public class Main {
    Vue vue = new Vue()

    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();

       /* Button uploadOriginalFileButton = new Button("Upload Original File");
        uploadOriginalFileButton.setPrefWidth(179.0);
        uploadOriginalFileButton.setFont(Font.font("Cooper Black", 15.0));
        AnchorPane.setTopAnchor(uploadOriginalFileButton, 94.0);
        AnchorPane.setLeftAnchor(uploadOriginalFileButton, 66.0);

        Button uploadEditedFileButton = new Button("Upload Edited File");
        uploadEditedFileButton.setPrefWidth(179.0);
        uploadEditedFileButton.setFont(Font.font("Cooper Black", 15.0));
        AnchorPane.setTopAnchor(uploadEditedFileButton, 95.0);
        AnchorPane.setRightAnchor(uploadEditedFileButton, 66.0);


        Button firstbutton= createUploadButton("Upload Original File", 179.0, 1 );
        Button secondboutton = createUploadButton("Upload Edited File", 179.0, 0 );
        controler.handleUploadButtonClick(firstbutton);
        controler.handleUploadButtonClick(secondboutton);

        // Chargement de l'image depuis les ressources du projet
        Image image = new Image(getClass().getResourceAsStream("/icon_txt.png"));
        ImageView imageView1 =  createImageView(image, 150.0, 200,  1);
        ImageView imageView2 =  createImageView(image, 150.0, 200, 0);


        /*ImageView imageView1 = new ImageView(image);
        imageView1.setFitHeight(150.0);
        imageView1.setFitWidth(200.0);
        AnchorPane.setTopAnchor(imageView1, 200.0);
        AnchorPane.setLeftAnchor(imageView1, 100.0);

        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(150.0);
        imageView2.setFitWidth(200.0);
        AnchorPane.setTopAnchor(imageView2, 200.0);
        AnchorPane.setRightAnchor(imageView2, 100.0);

        root.getChildren().addAll(firstbutton, secondboutton, imageView1, imageView2);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
*/