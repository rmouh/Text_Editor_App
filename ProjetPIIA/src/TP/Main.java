package TP;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main extends Application {

    private TextArea referenceTextArea;
    private TextArea modifiedTextArea;
    private TextArea commentTextArea;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Creating TextAreas for reference text, modified text, and comments
        referenceTextArea = new TextArea();
        referenceTextArea.setEditable(false);
        modifiedTextArea = new TextArea();
        commentTextArea = new TextArea();

        // Creating buttons for accepting, rejecting, and saving changes
        Button acceptButton = new Button("Accept Changes");
        Button rejectButton = new Button("Reject Changes");
        Button saveButton = new Button("Save Changes");

        // Creating a VBox to hold buttons
        VBox buttonBox = new VBox(5);
        buttonBox.getChildren().addAll(acceptButton, rejectButton, saveButton);

        // Creating labels for TextAreas
        Label referenceLabel = new Label("Reference Text:");
        Label modifiedLabel = new Label("Modified Text:");
        Label commentLabel = new Label("Comments:");

        // Creating a VBox to hold labels and TextAreas
        VBox textAreasBox = new VBox(10);
        textAreasBox.getChildren().addAll(
                referenceLabel, referenceTextArea,
                modifiedLabel, modifiedTextArea,
                commentLabel, commentTextArea
        );

        // Adding TextAreas and buttons to the root BorderPane
        root.setCenter(textAreasBox);
        root.setRight(buttonBox);
        BorderPane.setMargin(buttonBox, new Insets(10));

        // Setting action handlers for buttons
        acceptButton.setOnAction(event -> acceptChanges());
        rejectButton.setOnAction(event -> rejectChanges());
        saveButton.setOnAction(event -> saveChanges());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Text Comparison Application");
        primaryStage.show();
    }

    // Method to handle accepting changes
    private void acceptChanges() {
        // Implement accepting changes logic here
    }

    // Method to handle rejecting changes
    private void rejectChanges() {
        // Implement rejecting changes logic here
    }

    // Method to handle saving changes
    private void saveChanges() {
        // Implement saving changes logic here
    }

    public static void main(String[] args) {
        launch(args);
    }
}
