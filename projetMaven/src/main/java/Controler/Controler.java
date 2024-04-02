package Controler;

import Vue.Vue;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
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
            System.out.println("Téléchargement du fichier : ");

            // Traitez le fichier sélectionné (par exemple, téléchargez le fichier)
            if (selectedFile != null) {
                System.out.println("Téléchargement du fichier : " + selectedFile.getAbsolutePath());
            }
        });
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