package Controler;

import Model.Model;
import Vue.Vue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;

public class Controler {
   private Model model;
    private Vue vue;
    Stage primaryst;
    @FXML
    private Button saveButton;

    @FXML
    private Button downloadButton;

    public Controler(Stage primaryStage) throws Exception {

        this.vue = new Vue();
        this.model = new Model();
        vue.Firstvue(primaryStage, this);
        //String str = model.readFileContent(model.selectedFile);

        //System.out.println(str);
        //this.vue.SecondPage(primaryStage,this);

        // code qui marche
        /*Parent root = FXMLLoader.load(getClass().getResource("../test.fxml"));
        // root = FXMLLoader.load(fxmlPath);
        primaryStage.setTitle("Interface Utilisateur");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();*/


        this.primaryst=primaryStage;
       /*Scene scene = new Scene(this.getLayout(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setScene(vue.Scenegetter());
        //primaryStage.show();


        */

    }
    public static Controler createWithStage(Stage primaryStage) {
        try {
            return new Controler(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void handleUploadButtonClick(Button uploadButton, int index ) {
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
                      // String str = model.readFileContent(model.getDownFile());

                        //System.out.println(str);
                        // Changer l'icône après la vérification du fichier texte
                        Image newImage = new Image(getClass().getResourceAsStream("/icon_txt_green.png"));
                        vue.replaceImageView(uploadButton, newImage);


                        if(model.verifyDownload()){
                            System.out.println("if reussi la diff ");
                            //String str = model.readFileContent(model.getDownFile(0));
                            //str = model.readFileContent(model.getDownFile(1));
                            /*try {
                                model.getDiff();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }*/
                            //System.out.println(str);
                            try {
                                redirectToSecondPage();
                            } catch (Exception e) {
                                //throw new RuntimeException(e);
                                e.printStackTrace();
                            }
                        }

                    } else {
                        System.out.println("Le fichier sélectionné n'est pas un fichier texte (.txt).");
                        // Afficher un message d'erreur ou une notification à l'utilisateur si le fichier n'est pas un fichier texte
                    }

            }
        });
    }

    private void redirectToSecondPage() throws Exception {
        // Créer la deuxième page avec les boutons et les éléments nécessaires
        // ...
        System.out.println("redirect to 2nd pg");
        //vue.SecondPage(this.primaryst,this); // Afficher la deuxième page
        //Parent root = FXMLLoader.load(getClass().getResource("../test2.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../secondview2.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        primaryst.setTitle("Interface Utilisateur");
        primaryst.setScene(new Scene(root, 800, 600));
        primaryst.show();
    }

    @FXML
    void handleSaveButtonClic(ActionEvent event) {
        // Cast de l'événement en tant que bouton cliqué
        //*Button clickedButton = (Button) event.getSource();

        // Récupérer l'image associée au bouton cliqué
        ImageView imageView = (ImageView) saveButton.getGraphic();

        // Modifier l'image associée
        Image newImage = new Image(getClass().getResourceAsStream("../bookmark.png"));
        imageView.setImage(newImage);

        // Afficher un autre bouton
        downloadButton.setVisible(true);// Assurez-vous que "autreBouton" est bien un autre bouton dans votre scène
    }





}

