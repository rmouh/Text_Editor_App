package Controler;

import Model.Model;
import Vue.Vue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.management.modelmbean.ModelMBean;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Controler {
    private Model model;
    private Vue vue;
    Stage primaryst;
    @FXML
    private Button saveButton;

    @FXML
    private Button downloadButton;

    public Controler(Stage primaryStage) {

        this.vue = new Vue();
        this.model = new Model();
        //vue.Firstvue(primaryStage, this);
        this.vue.SecondPage(primaryStage,this);
        this.primaryst=primaryStage;
       /*Scene scene = new Scene(this.getLayout(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setScene(vue.Scenegetter());
        //primaryStage.show();


        */

    }

    public void handleUploadButtonClick(Button uploadButton, int index) {
        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner un fichier à télécharger");
            Stage stage = new Stage();
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                //try {
                    // Vérifie si le fichier sélectionné est un fichier texte
                    if (selectedFile.getName().toLowerCase().endsWith(".txt")) {
                        model.addDownloadedFile(selectedFile, index);

                        //System.out.println("Contenu du fichier texte : \n" + content.toString());

                        // Changer l'icône après la vérification du fichier texte
                        Image newImage = new Image(getClass().getResourceAsStream("/icon_txt_green.png"));
                        vue.replaceImageView(uploadButton, newImage);


                        if(model.verifyDownload()){
                            System.out.println("if reussi");
                            redirectToSecondPage();
                        }

                    } else {
                        System.out.println("Le fichier sélectionné n'est pas un fichier texte (.txt).");
                        // Afficher un message d'erreur ou une notification à l'utilisateur si le fichier n'est pas un fichier texte
                    }

            }
        });
    }

    private void redirectToSecondPage() {
        // Créer la deuxième page avec les boutons et les éléments nécessaires
        // ...
        System.out.println("redirect to 2nd pg");
        vue.SecondPage(this.primaryst,this); // Afficher la deuxième page
    }

   /* @FXML
    void handleSaveButtonClic(ActionEvent event) {
        // Cast de l'événement en tant que bouton cliqué
        *//*Button clickedButton = (Button) event.getSource();

        // Récupérer l'image associée au bouton cliqué
        ImageView imageView = (ImageView) clickedButton.getGraphic();

        // Modifier l'image associée
        Image newImage = new Image(getClass().getResourceAsStream("downloads.png"));
        imageView.setImage(newImage);

        // Afficher un autre bouton
        downloadButton.setVisible(true); *//*// Assurez-vous que "autreBouton" est bien un autre bouton dans votre scène
    }
*/



}

