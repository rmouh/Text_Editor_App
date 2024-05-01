package Controler;

import Model.Model;
import Vue.Vue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controler {
   private Model model;
    private Vue vue;
    Stage primaryst;
    //List<Comment> comments = new ArrayList<>();
    List<Comment> comments = new ArrayList<Comment>();
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
        this.primaryst=primaryStage;

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
            System.out.println("new value : " + newValue);
            System.out.println("newvalue.empty : " + newValue.isEmpty());
            System.out.println("oldValue : " + oldValue);
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


        deleteButton.setOnAction(event -> handleDeleteButtonClicked());
        /*commentButton.setOnAction(event -> {
            // Ouvrir une boîte de dialogue pour saisir le commentaire
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Ajouter un commentaire");
            dialog.setHeaderText("Saisissez votre commentaire :");
            dialog.setContentText("Commentaire :");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String commentaire = result.get();

                // Créer une étiquette avec l'icône de commentaire
                Label commentaireLabel = new Label();
                commentaireLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../chat.png"))));
                commentaireLabel.setTooltip(new Tooltip("Commentaire : " + commentaire));


                int startIndex = editedFileContentLabel.getSelection().getStart();
                int endIndex = editedFileContentLabel.getSelection().getEnd();

                String selectedText = editedFileContentLabel.getText(startIndex, endIndex);

                // Positionner l'étiquette à côté du texte sélectionné
                Bounds bounds = editedFileContentLabel.localToScreen(editedFileContentLabel.getLayoutBounds());
                commentaireLabel.setLayoutX(bounds.getMinX());
                commentaireLabel.setLayoutY(bounds.getMinY() - commentaireLabel.getHeight());

                // Ajouter l'étiquette à la scène
                // Assurez-vous d'avoir accès à la scène ou à un conteneur approprié ici
                // Par exemple, vous pouvez ajouter l'étiquette à un Pane ou directement à la scène racine
                ((Pane) this.primaryst.getScene().getRoot()).getChildren().add(commentaireLabel);

            }
        });*/
        commentButton.setOnAction(event -> {
            // Check if text is selected
            if (!editedFileContentLabel.getSelectedText().isEmpty()) {

                // Open a dialog box for comment input
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Ajouter un commentaire");
                dialog.setHeaderText("Saisissez votre commentaire :");
                dialog.setContentText("Commentaire :");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String commentaire = result.get();



                    Label commentaireLabel = new Label();
                    commentaireLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../chat.png"))));

                    // Create a comment object and add it to the list
                    Comment comment = new Comment(commentaire, commentaireLabel);
                    comments.add(comment);

                    commentaireLabel.setOnMouseClicked(e -> {
                        Comment comment2 = comments.stream()
                                .filter(c -> c.getLabel() == commentaireLabel)
                                .findFirst()
                                .orElse(null);

                        if (comment2 != null) {
                            // Display comment text using a Tooltip
                            Tooltip tooltip = new Tooltip("Commentaire : " + comment2.getText());
                            int X = (int) editedFileContentLabel.getLayoutX() + editedFileContentLabel.getSelection().getEnd();
                            int Y = (int) ((int) editedFileContentLabel.getLayoutY() + e.getY());
                            tooltip.show(this.primaryst, X, Y + 10);

                        }
                    });

                    if (editedFileContentLabel.getParent() instanceof StackPane) {
                        StackPane stackPane = (StackPane) editedFileContentLabel.getParent();
                        stackPane.getChildren().add(commentaireLabel);
                    } else {
                        System.out.println("Parent of editedFileContentLabel is not a StackPane.");
                    }

                    // Assuming editedFileContentLabel is the first TextArea in the first StackPane:
                    /*editedFileContentLabel.getParent().getChildrenUnmodifiable().stream()
                            .filter(node -> node instanceof StackPane)
                            .map(node -> (StackPane) node)
                            .findFirst()
                            .ifPresent(stackPane -> {
                                stackPane.getChildren().add(commentaireLabel);
                            });*/

                }
            }
        });
        /*commentButton.setOnAction(event -> {
            // Check if text is selected
            if (!editedFileContentLabel.getSelectedText().isEmpty()) {

                // Open a dialog box for comment input
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Ajouter un commentaire");
                dialog.setHeaderText("Saisissez votre commentaire :");
                dialog.setContentText("Commentaire :");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String commentaire = result.get();

                    int startIndex = editedFileContentLabel.getSelection().getStart();
                    int endIndex = editedFileContentLabel.getSelection().getEnd();
                    // Create a label with the comment icon
                    Label commentaireLabel = new Label();
                    commentaireLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../chat.png"))));
                    Tooltip tooltip = new Tooltip("Commentaire : " + commentaire);
                    commentaireLabel.setTooltip(tooltip);
                    tooltip.show(this.primaryst, 100, 100);

                    // Create IndexedComment object and add it to the list
                    comments.add(new Comment(commentaire,commentaireLabel));
                }
            }
        });*/




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
                        //String str = (index == 0) ? model.getOriginalContent() : model.getEditedContent();
                        String str = null;
                        try {
                            str = (index == 0) ? model.getOriginalContent() : model.getEditedContentDiff();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
        //editedFileContentLabel.setText(model.getEditedFileContent());
        editedFileContentLabel.setText(model.getEditedContentDiff());

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



    class Comment {
        private String text;
        private Label label;

        public Comment(String text, Label label) {
            this.text = text;
            this.label = label;
        }

        public String getText() {
            return text;
        }

        public Label getLabel() {
            return label;
        }
    }




}

