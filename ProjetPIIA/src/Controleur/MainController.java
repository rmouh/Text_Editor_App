package Controleur;
import Vue.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainController {
    private File referenceFile;
    private File modifiedFile;
    private MainWindow mainWindow;

    public MainController(File ref, File modF, MainWindow mainWindow) {
        // Initialisation des fichiers
    	this.mainWindow = mainWindow;
    	loadFiles(ref,modF);
    	// Ajout des écouteurs d'événements
        mainWindow.getModifyButton().setOnAction(event -> modifyText());
        mainWindow.getDeleteButton().setOnAction(event -> deleteText());
        mainWindow.getCommentButton().setOnAction(event -> commentText());
    }

    public void loadFiles(File referenceFile, File modifiedFile) {
        this.referenceFile = referenceFile;
        this.modifiedFile = modifiedFile;

        // Charger les fichiers dans les zones de texte
        try {
            BufferedReader referenceReader = new BufferedReader(new FileReader(referenceFile));
            BufferedReader modifiedReader = new BufferedReader(new FileReader(modifiedFile));

            StringBuilder referenceText = new StringBuilder();
            String line;
            while ((line = referenceReader.readLine()) != null) {
                referenceText.append(line).append("\n");
            }
            mainWindow.setReferenceText(referenceText.toString());

            StringBuilder modifiedText = new StringBuilder();
            while ((line = modifiedReader.readLine()) != null) {
                modifiedText.append(line).append("\n");
            }
            mainWindow.setModifiedText(modifiedText.toString());

            referenceReader.close();
            modifiedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthodes pour gérer les actions de l'utilisateur (modification, suppression, commentaire)
    private void modifyText() {
        // Implémenter la logique pour modifier le texte
    }

    private void deleteText() {
        // Implémenter la logique pour supprimer du texte
    }

    private void commentText() {
        // Implémenter la logique pour commenter le texte
    }
}
