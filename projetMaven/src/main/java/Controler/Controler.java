package Controler;

import Model.Model;
import Vue.Vue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.management.modelmbean.ModelMBean;
import java.io.File;
public class Controler {
    private Model model;
    private Vue vue;

    public Controler(Stage primaryStage) {

        this.vue = new Vue();
        this.model = new Model();
        vue.Firstvue(primaryStage, this);
       /*Scene scene = new Scene(this.getLayout(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setScene(vue.Scenegetter());
        //primaryStage.show();


        */

    }

    public void handleUploadButtonClick(Button uploadButton) {
        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner un fichier à télécharger");
            Stage stage = new Stage();
            File selectedFile = fileChooser.showOpenDialog(stage);
            // Traitez le fichier sélectionné (par exemple, téléchargez le fichier)
            if (selectedFile != null) {
                System.out.println("Téléchargement du fichier : " + selectedFile.getAbsolutePath());
                Image newImage = new Image(getClass().getResourceAsStream("/icon_txt_green.png"));
                vue.replaceImageView(uploadButton, newImage);
                //model.addDownloadedFile(selectedFile); // Ajoute le fichier téléchargé au modèle
                System.out.println("Fichiers téléchargés : " + model.getDownloadedFiles()); // Vérifiez les fichiers téléchargés
                if (model.getDownloadedFiles().size() == 2) {
                    redirectToSecondPage(); // Redirige vers la deuxième page si les deux fichiers sont téléchargés
                }

            }
        });
    }
    private void redirectToSecondPage() {
        // Créer la deuxième page avec les boutons et les éléments nécessaires
        // ...
        vue.showSecondPage(); // Afficher la deuxième page
    }

}

/*
public class Controler {
    private Vue vue;

    public Controler(Vue vue) {
        this.vue = vue;
    }

    public void handleUploadButtonClick(Button uploadButton) {
        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner un fichier à télécharger");
            Stage stage = new Stage();
            File selectedFile = fileChooser.showOpenDialog(stage);
            // Traitez le fichier sélectionné (par exemple, téléchargez le fichier)
            if (selectedFile != null) {
                System.out.println("Téléchargement du fichier : " + selectedFile.getAbsolutePath());
            }
        });
    }
}
*/