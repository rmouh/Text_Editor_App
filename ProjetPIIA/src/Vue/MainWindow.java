package Vue;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainWindow {
    private TextArea referenceTextArea;
    private TextArea modifiedTextArea;
    private Button modifyButton;
    private Button deleteButton;
    private Button commentButton;

    public MainWindow() {
        referenceTextArea = new TextArea();
        modifiedTextArea = new TextArea();
        modifyButton = new Button("Modifier");
        deleteButton = new Button("Supprimer");
        commentButton = new Button("Commenter");

        // Mise en page des composants graphiques
        VBox root = new VBox();
        HBox buttonsBox = new HBox(modifyButton, deleteButton, commentButton);
        root.getChildren().addAll(referenceTextArea, modifiedTextArea, buttonsBox);

        // Initialisation des composants graphiques
        // Ajout des écouteurs d'événements
    }

    public TextArea getReferenceTextArea() {
        return referenceTextArea;
    }

    public TextArea getModifiedTextArea() {
        return modifiedTextArea;
    }

    public Button getModifyButton() {
        return modifyButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getCommentButton() {
        return commentButton;
    }
}
