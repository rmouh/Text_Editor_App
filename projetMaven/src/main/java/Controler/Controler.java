package Controler;

import Model.Model;
import Vue.Vue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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
    @FXML
    private TextArea originalFileContentLabel;
    @FXML
    private TextArea editedFileContentLabel;
    @FXML
    private Button editButton;

    @FXML
    private Button commentButton;

    @FXML
    private Button deleteButton;

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

    @FXML
    private void initialize() {
        // Désactiver les boutons initialement
        editButton.setDisable(false);
        commentButton.setDisable(true);
        deleteButton.setDisable(true);

        // Désactiver l'édition initialement
        //editedFileContentLabel.setEditable(false);

        // Ajouter des gestionnaires d'événements pour activer/désactiver les boutons en fonction de la sélection dans le TextArea
        editedFileContentLabel.selectedTextProperty().addListener((observable, oldValue, newValue) -> {
            boolean isTextSelected = !newValue.isEmpty();
            //editButton.setDisable(!isTextSelected);
            commentButton.setDisable(!isTextSelected);
            deleteButton.setDisable(!isTextSelected);
        });

        // Ajouter un gestionnaire d'événements pour le bouton d'édition
       /* editButton.setOnAction(event -> {
            // Activer l'édition
            editedFileContentLabel.setEditable(true);
        });*/
        editButton.setOnAction(event -> handleEditButtonClicked());

        // Ajouter un gestionnaire d'événements pour le bouton de suppression
        // delete selected text

        /*deleteButton.setOnAction(event -> {
            // Supprimer le contenu sélectionné
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(editedFileContentLabel.getSelectedText());
            clipboard.setContent(content);
            editedFileContentLabel.setEditable(true);
            editedFileContentLabel.requestFocus();
            if (!editedFileContentLabel.isFocused()) {
                System.out.println("Please select text in the label before deleting.");
                return;
            }
            editedFileContentLabel.replaceSelection("");
            editedFileContentLabel.setEditable(false);
            //editedFileContentLabel.requestFocus();
            System.out.println("Suppppp");
            String str = model.getEditedFileContent();
            System.out.println(str);
        });*/

        /*deleteButton.setOnAction(event -> {
            // Check if label has focus before deleting
            editedFileContentLabel.requestFocus();
            if (!editedFileContentLabel.isFocused()) {
                System.out.println("Please select text in the label before deleting.");
                // Optionally, disable delete button or display an error message in the UI
                return;
            }

            // Set editable only if focus is confirmed
            //editedFileContentLabel.requestFocus();
            editedFileContentLabel.setEditable(true);

            String selectedText = editedFileContentLabel.getSelectedText();

            // Validate selected text (optional, but good practice)
            if (!selectedText.isEmpty()) {
                editedFileContentLabel.replaceSelection("");
                System.out.println("Supprimé : " + selectedText);
            } else {
                System.out.println("Aucun texte sélectionné pour supprimer.");
            }

            editedFileContentLabel.setEditable(false);

            String str = model.getEditedFileContent();
            System.out.println(str);
        });*/


        deleteButton.setOnAction(event -> handleDeleteButtonClicked());


    }

    private void handleDeleteButtonClicked() {
        editedFileContentLabel.requestFocus();
        if (!editedFileContentLabel.isFocused()) {
            System.out.println("Please select text in the label before deleting.");
            return;
        }
        editedFileContentLabel.setEditable(true);
        // Récupérer le texte sélectionné
        String selectedText = editedFileContentLabel.getSelectedText();

        // Si du texte est sélectionné, le supprimer
        if (!selectedText.isEmpty()) {
            // Supprimer le texte sélectionné de la zone de texte
            int startIndex = editedFileContentLabel.getSelection().getStart();
            int endIndex = editedFileContentLabel.getSelection().getEnd();

            String st = editedFileContentLabel.getText(startIndex, endIndex);
            System.out.println("Texte sélectionneee : " + st);

            if (startIndex >= 0 && endIndex >= startIndex && endIndex <= editedFileContentLabel.getText().length()) {
                editedFileContentLabel.deleteText(startIndex, endIndex);
                editedFileContentLabel.setEditable(false);
                // Mettre à jour le fichier avec le texte modifié
                String newText = editedFileContentLabel.getText();
                model.updateContent(newText);
            } else {
                // Gérer la condition d'erreur (par exemple, afficher un message d'erreur ou désactiver la fonction de suppression)
                System.out.println("Invalid index range for deletion.");
            }

            System.out.println("Supprimé : " + selectedText);
        } else {
            System.out.println("Aucun texte sélectionné pour supprimer.");
        }

        String str = model.getEditedContent();
        System.out.println("edited content"+str);
        String str2 = model.getEditedFileContent();
        System.out.println("edited file content"+str2);
    }
    private void handleEditButtonClicked() {
        // Activez la modification du texte dans la zone de texte
        editedFileContentLabel.setEditable(true);
        // Placez le curseur à la fin du texte pour commencer à écrire
        editedFileContentLabel.positionCaret(editedFileContentLabel.getLength());
        // Donnez le focus à la zone de texte pour commencer à écrire directement
        editedFileContentLabel.requestFocus();
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
                // ajout test
                    if (selectedFile.getName().toLowerCase().endsWith(".txt")) {
                        try {
                            model.addDownloadedFile(selectedFile, index);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        //String str = (index == 0) ? model.getOriginalFileContent() : model.getEditedFileContent();
                        String str = (index == 0) ? model.getOriginalContent() : model.getEditedContent();

                        System.out.println(str);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../secondview.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        //originalFileContentLabel.setText(model.getOriginalFileContent());
        //editedFileContentLabel.setText(model.getEditedFileContent());
        originalFileContentLabel.setText(model.getOriginalFileContent());
        editedFileContentLabel.setText(model.getEditedFileContent());

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

