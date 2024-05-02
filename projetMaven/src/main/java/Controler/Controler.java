package Controler;

import Model.Model;
import Vue.Vue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    private Button exitBtn;
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
    double posSelectionX;
    double posSelectionY;

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
        exitBtn.setDisable(false);
        exitBtn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de sortie");
            alert.setHeaderText("Êtes-vous sûr de vouloir quitter ?");
            alert.setContentText("En quittant sans enregistrer, vous perdrez les modifications apportées au fichier.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                this.vue = new Vue();
                vue.Firstvue(primaryst,this);
            }

        });
        // Ajouter des gestionnaires d'événements pour activer/désactiver les boutons en fonction de la sélection dans le TextArea
        editedFileContentLabel.selectedTextProperty().addListener((observable, oldValue, newValue) -> {

            boolean isTextSelected = !newValue.isEmpty();
            if(isTextSelected)
            {

                //System.out.println("Debut selectionement ");
                int lineNumber = 1;
                int caretPosition = editedFileContentLabel.getCaretPosition();
                int selectionStart = editedFileContentLabel.getSelection().getStart();
                int charPositionInLine = 0;

                int i = selectionStart - 1;
                while (i >= 0 && editedFileContentLabel.getText().charAt(i) != '\n') {
                    i--;
                    charPositionInLine++;
                }
                lineNumber = 1;
                if (i >= 0) {
                    charPositionInLine++; // Pour compenser le saut de ligne
                }
                for (int j = 0; j < caretPosition; j++) {
                    if (editedFileContentLabel.getText().charAt(j) == '\n') {
                        lineNumber++;
                    }
                }
                System.out.println("les positions ");
                System.out.println("Ligne: " + lineNumber);
                System.out.println("Position dans la ligne: " + charPositionInLine);
                posSelectionX = charPositionInLine*11-20;
                posSelectionY = lineNumber*17.5 -20;
                //System.out.println("X : "+posSelectionX);
                //System.out.println("Y : "+posSelectionY);
            }
            else
                System.out.println("text not selected");
            //editButton.setDisable(!isTextSelected);
            commentButton.setDisable(!isTextSelected);
            deleteButton.setDisable(!isTextSelected);
        });

        editButton.setOnAction(event -> handleEditButtonClicked());
        deleteButton.setOnAction(event -> handleDeleteButtonClicked());
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

                    // Create a custom CommentNode for image and tooltip
                    Image commentImage = new Image(getClass().getResourceAsStream("../chat.png"));
                    CommentNode commentNode = new CommentNode(commentaire, commentImage);
                    CommentNode commentNode2 = new CommentNode(commentaire, commentImage);


                    commentNode.setPosition(posSelectionX, posSelectionY);//3.5  15
                    System.out.println("************************************Les vrai corrd");
                    System.out.println("X : "+posSelectionX*3.5);
                    System.out.println("Y : "+posSelectionY*15);

                    if (editedFileContentLabel.getParent().getParent() instanceof Pane) {
                        Pane parent = (Pane) editedFileContentLabel.getParent().getParent();
                        parent.getChildren().add(commentNode);
                        //parent.getChildren().add(commentNode2);
                    } else {
                        System.out.println("Parent of editedFileContentLabel is not a Pane.");
                    }
                }
            }
        });


    }


    public class CommentNode extends StackPane {
        private ImageView imageView;
        private Tooltip tooltip;
        private Boolean isTooltipVisible = false;

        public CommentNode(String commentText, Image image) {
            imageView = new ImageView(image);
            imageView.setFitWidth(20); // Adjust width as needed
            imageView.setFitHeight(20); // Adjust height as needed

            tooltip = new Tooltip(commentText);
            // attach the tooltip to the image view
            Tooltip.install(imageView, tooltip);
            imageView.setOnMouseClicked(e -> {
                if (isTooltipVisible) {
                    tooltip.hide();
                    isTooltipVisible = false;
                } else {
                    tooltip.show(this, e.getScreenX()+10, e.getScreenY()+10);
                    isTooltipVisible = true;
                }
            });
            getChildren().add(imageView);
        }

        public void setPosition(double X, double Y) {
            setLayoutX(65+X);//65
            setLayoutY(65+Y);
        }
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
            // Obtenez les indices de début et de fin de la sélection
            int startIndex = editedFileContentLabel.getSelection().getStart();
            int endIndex = editedFileContentLabel.getSelection().getEnd();
            int length = editedFileContentLabel.getLength();
            String text = editedFileContentLabel.getText();

            // Inverser les indices si la sélection est de droite à gauche
            if (startIndex > endIndex) {
                int temp = startIndex;
                startIndex = endIndex;
                endIndex = temp;
            }
            // Supprimer le texte sélectionné de la zone de texte
            if (startIndex >= 0 && endIndex > startIndex && endIndex <= length) {
                model.updateContent(editedFileContentLabel.getText());
                editedFileContentLabel.setText(text);
                editedFileContentLabel.deleteText(startIndex, endIndex);
                // Mettre à jour le fichier avec le texte modifié
                String newText = editedFileContentLabel.getText();
                editedFileContentLabel.setEditable(false);
                model.updateContent(newText);

                //System.out.println("Contenu du modèle mis à jour DELETE : " + model.getEditedContent());

            } else {
                // Gérer la condition d'erreur (par exemple, afficher un message d'erreur ou désactiver la fonction de suppression)
                System.out.println("Invalid index range for deletion.");
            }
            //System.out.println("-------------------->le texte Supprimé : " + selectedText);
        } else {
           // System.out.println("Aucun texte sélectionné pour supprimer.");
        }

    }
    private void handleEditButtonClicked() {
        // Activez la modification du texte dans la zone de texte
        editedFileContentLabel.setEditable(true);
        // Placez le curseur à la fin du texte pour commencer à écrire
        editedFileContentLabel.positionCaret(editedFileContentLabel.getLength());
        // Donnez le focus à la zone de texte pour commencer à écrire directement
        editedFileContentLabel.requestFocus();

        // Écoutez les changements dans le contenu de la zone de texte
        editedFileContentLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            // Mettez à jour le modèle avec le nouveau texte
            model.updateContent(newValue);
            // Imprimez le nouveau contenu du modèle (incluant les modifications)
            System.out.println("Contenu du modèle mis à jour ADD : " + model.getEditedContent());
        });
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
                        String str = (index == 0) ? model.getOriginalContent() : model.getEditedContent();
                        // Changer l'icône après la vérification du fichier texte
                        Image newImage = new Image(getClass().getResourceAsStream("/icon_txt_green.png"));
                        vue.replaceImageView(uploadButton, newImage);


                        if(model.verifyDownload()){
                            System.out.println("if reussi la diff ");
                            try {
                                model.updateContent(model.getEditedContentDiff());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                redirectToSecondPage();
                            } catch (Exception e) {
                                //throw new RuntimeException(e);
                                e.printStackTrace();
                            }
                        }

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Le fichier sélectionné n'est pas un fichier texte (.txt).");

                        alert.showAndWait();
                        System.out.println("Le fichier sélectionné n'est pas un fichier texte (.txt).");
                        // Afficher un message d'erreur ou une notification à l'utilisateur si le fichier n'est pas un fichier texte
                    }

            }
        });
    }

    private void redirectToSecondPage() throws Exception {
        // Créer la deuxième page avec les boutons et les éléments nécessaires
        System.out.println("redirect to 2nd pg");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../secondview.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        String css = "-fx-font-weight: bold;";
        originalFileContentLabel.setStyle(css);
        originalFileContentLabel.setText(model.getOriginalFileContent());
        editedFileContentLabel.setStyle(css);
        editedFileContentLabel.setText(model.getEditedContent());
        primaryst.setTitle("Interface Utilisateur");
        primaryst.setScene(new Scene(root, 800, 600));
        primaryst.show();
    }
    public void setdisplayText(String text)
    {
        //editedFileContentLabel.setText();
        String selectedText = editedFileContentLabel.getText();
        String formattedText = "<b>" + selectedText + "</b>"; // Bold formatting example
        editedFileContentLabel.replaceSelection(formattedText);
    }

    @FXML
    void handleSaveButtonClic(ActionEvent event) {
        // Cast de l'événement en tant que bouton cliqué
        model.updateFile(model.getEditedContent());
        // Récupérer l'image associée au bouton cliqué
        ImageView imageView = (ImageView) saveButton.getGraphic();

        // Modifier l'image associée
        Image newImage = new Image(getClass().getResourceAsStream("../bookmark.png"));
        imageView.setImage(newImage);

        // Afficher un autre bouton
        downloadButton.setVisible(true);// Assurez-vous que "autreBouton" est bien un autre bouton dans votre scène
        downloadButton.setDisable(false);

        downloadButton.setOnAction(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Sélectionner un emplacement pour enregistrer le fichier");
            fileChooser.setCurrentDirectory(new File("."));

            // Filtre pour n'afficher que les fichiers .txt
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier texte", "txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showSaveDialog(null);

            if(response == JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();

                // Ajouter l'extension .txt si elle n'est pas déjà présente dans le nom du fichier
                if (!file.getName().toLowerCase().endsWith(".txt")) {
                    file = new File(file.getAbsolutePath() + ".txt");
                }
                try (PrintWriter fileOut = new PrintWriter(file)) {
                    fileOut.println(editedFileContentLabel.getText());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
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

