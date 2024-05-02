package Model;
import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Model {
    //private List<File> downloadedFiles;
    private File downloadedFiles[];
    private List<String> original;
    private List<String> edited;


    public Model() {
        //downloadedFiles = new ArrayList<>();
        downloadedFiles = new File[2];
        //aniaaaa
    }
    public void addDownloadedFile(File file, int index) throws IOException {
        downloadedFiles[index] = file;
        if(index==0)
            original = Files.readAllLines(downloadedFiles[0].toPath());
        else
            edited = Files.readAllLines(downloadedFiles[1].toPath());
    }
    public File getDownFile(int index) {
        return downloadedFiles[index];
    }
    public boolean verifyDownload(){
        return (downloadedFiles[0]!=null && downloadedFiles[1]!=null);
    }

    // Dans la méthode handleUploadButtonClick après avoir ajouté le fichier au modèle

    // Méthode pour lire le contenu du fichier
    public  String readFileContent(File file) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
                content.append("\n"); // Ajouter un saut de ligne après chaque ligne lue
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    /*public void getDiff() throws IOException {
        //build simple lists of the lines of the two testfiles
        List<String> original = Files.readAllLines(downloadedFiles[0].toPath());
        List<String> revised = Files.readAllLines(downloadedFiles[1].toPath());

//compute the patch: this is the diffutils part
        Patch<String> patch = DiffUtils.diff(original, revised);

//simple output the computed patch to console
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            System.out.println(delta);
        }

        //List<String> original = Files.readAllLines(downloadedFiles[0].toPath());
        //List<String> revised = Files.readAllLines(downloadedFiles[1].toPath());

        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")
                .newTag(f -> "")
                .build();
        List<DiffRow> rows = generator.generateDiffRows(original,edited);

        System.out.println("|original|new|");
        System.out.println("|--------|---|");
        for (DiffRow row : rows) {
            System.out.println("|" + row.getOldLine() + "|" + row.getNewLine()+"|");
        }
    }*/
    public String getEditedContentDiff() throws IOException {
        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")
                .newTag(f -> "**")
                .build();
        List<DiffRow> rows = generator.generateDiffRows(original,edited);
        StringBuilder content = new StringBuilder();
        for (DiffRow row : rows) {
            content.append(row.getOldLine() + "|" + row.getNewLine()+"|\n");
        }
        //this.updateContent(newText);
        System.out.println(content.toString());
        return content.toString();
    }

    /*public void addDownloadedFile(File file) {
        downloadedFiles.add(file);
    }
    public List<File> getDownloadedFiles() {
        return downloadedFiles;
    }*/
    public String getOriginalFileContent() {
        return readFileContent(downloadedFiles[0]);
    }
    public String getEditedFileContent() {
        return readFileContent(downloadedFiles[1]);
    }
    public String getOriginalContent() {
        StringBuilder content = new StringBuilder();
        for (String line : original) {
            content.append(line);
            content.append("\n");
        }
        return content.toString();
    }
    public String getEditedContent() {
        StringBuilder content = new StringBuilder();
        for (String line : edited) {
            content.append(line);
            content.append("\n");
        }
        return content.toString();
    }
    public void updateFile(String newText) {
        try {
            FileWriter writer = new FileWriter(downloadedFiles[1]);
            writer.write(newText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateContent(String newText) {
        edited = Arrays.asList(newText.split("\n"));
    }

    public void updateFileFromEditedList(int index) {
        // Vérifiez d'abord si le fichier correspondant existe
        if (downloadedFiles != null && index >= 0 && index < downloadedFiles.length) {
            File fileToUpdate = downloadedFiles[index];

            // Vérifiez si la liste éditée est valide
            if (edited != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToUpdate))) {
                    // Écrivez chaque ligne de la liste éditée dans le fichier
                    for (String line : edited) {
                        writer.write(line);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    // Gérez l'exception selon votre logique d'application
                }
            } else {
                System.out.println("La liste éditée est vide ou non initialisée.");
                // Gérez cette condition selon votre logique d'application
            }
        } else {
            System.out.println("Index de fichier invalide ou fichier non téléchargé.");
            // Gérez cette condition selon votre logique d'application
        }
    }
}
