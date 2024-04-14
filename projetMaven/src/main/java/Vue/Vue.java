package Vue;
import Controler.Controler;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


public class Vue {
    private AnchorPane root;
    private Scene scene;
    private Button uploadOriginalFileButton;
    private Button uploadEditedFileButton;

    public Vue() {
        // Créer les boutons dans Vue
        this.uploadEditedFileButton = this.createUploadButton("Upload Edited File", 179.0, 2);
        this.uploadOriginalFileButton = this.createUploadButton("Upload Original File", 179.0, 1);
        root = new AnchorPane();
        root.setStyle("-fx-background-color: #ffd8cf;");
        this.scene = new Scene(this.getLayout(), 600, 400);

        //initializeComponents();
    }
    public Button OriginalBouttongetter()
    {
        return (this.uploadOriginalFileButton);
    }
    public Button EditedBouttongetter()
    {
        return (this.uploadEditedFileButton);
    }
    public Scene Scenegetter()
    {
        return (this.scene);
    }
    public void Firstvue ( Stage primaryStage, Controler controler)
    {
        // Chargement de l'image depuis les ressources du projet
        Image image = new Image(getClass().getResourceAsStream("/icon_txt.png"));
        ImageView imageView1 = this.createImageView(image, 150.0, 200.0, 1);
        ImageView imageView2 = this.createImageView(image, 150.0, 200.0, 2);
        // Ajouter les boutons à la vue
        this.addToView(this.uploadOriginalFileButton,this.uploadEditedFileButton, imageView1, imageView2 );


        // Créer la scène et ajouter les boutons de la vue à celle-ci
        //this.scene = new Scene(this.getLayout(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Lier les boutons aux gestionnaires d'événements dans le contrôleur
        // index 0 for original and 1 for edited
        controler.handleUploadButtonClick(uploadOriginalFileButton,0);
        controler.handleUploadButtonClick(uploadEditedFileButton,1);
    }

   /* public void SecondPage ( Stage primaryStage, Controler controler) throws Exception
    {
        System.out.println("2nd page");
        Parent root = FXMLLoader.load(getClass().getResource("../secondview2.fxml"));
        primaryStage.setTitle("Interface Utilisateur");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        //loadFXML(primaryStage,controler,"../test.fxml");

    }*/
    public Button createUploadButton(String buttonText, double width, int n) {
        Button uploadButton = new Button(buttonText);
        uploadButton.setPrefWidth(width);
        uploadButton.setFont(Font.font("Cooper Black", 15.0));
        AnchorPane.setTopAnchor(uploadButton, 94.0);
        if (n == 1)
            AnchorPane.setRightAnchor(uploadButton, 66.0);
        else
            AnchorPane.setLeftAnchor(uploadButton, 66.0);
        return uploadButton;
    }

    public void addToView(Button button1, Button button2, ImageView imageView1 , ImageView imageView2) {
        root.getChildren().addAll(button1, button2, imageView1, imageView2);
    }

    public AnchorPane getLayout() {
        return root;
    }
    public ImageView createImageView (Image image, double height, double width, int n) {
        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(height);
        imageView2.setFitWidth(width);
        AnchorPane.setTopAnchor(imageView2, 200.0);
        if (n==1)
            AnchorPane.setRightAnchor(imageView2, 100.0);
        else
            AnchorPane.setLeftAnchor(imageView2, 100.0);
        return imageView2;
    }

    public void replaceImageView(Button button, Image newImage) {
        for (Node node : root.getChildren()) {
            if (node instanceof ImageView && root.getChildren().indexOf(button) == root.getChildren().indexOf(node) - 2) {
                ImageView imageView = (ImageView) node;
                imageView.setImage(newImage);
                break; // Sortir de la boucle une fois que l'image a été remplacée
            }
        }
    }



}



