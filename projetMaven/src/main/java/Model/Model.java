package Model;
import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Model {
    //private List<File> downloadedFiles;
    private File downloadedFiles[];


    public Model() {
        //downloadedFiles = new ArrayList<>();
        downloadedFiles = new File[2];
    }
    public void addDownloadedFile(File file, int index) {
        downloadedFiles[index] = file;
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
    public void getDiff() throws IOException {
       /* //build simple lists of the lines of the two testfiles
        List<String> original = Files.readAllLines(downloadedFiles[0].toPath());
        List<String> revised = Files.readAllLines(downloadedFiles[1].toPath());

//compute the patch: this is the diffutils part
        Patch<String> patch = DiffUtils.diff(original, revised);

//simple output the computed patch to console
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            System.out.println(delta);
        }
        */
        List<String> original = Files.readAllLines(downloadedFiles[0].toPath());
        List<String> revised = Files.readAllLines(downloadedFiles[1].toPath());

        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")
                .newTag(f -> "")
                .build();
        List<DiffRow> rows = generator.generateDiffRows(original,revised);

        System.out.println("|original|new|");
        System.out.println("|--------|---|");
        for (DiffRow row : rows) {
            System.out.println("|" + row.getOldLine() + "|" + row.getNewLine()+"|");
        }
    }

    /*public void addDownloadedFile(File file) {
        downloadedFiles.add(file);
    }
    public List<File> getDownloadedFiles() {
        return downloadedFiles;
    }*/

    // Ajoutez d'autres méthodes nécessaires pour manipuler les fichiers téléchargés selon vos besoins
}
