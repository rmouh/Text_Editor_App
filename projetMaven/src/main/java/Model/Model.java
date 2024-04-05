package Model;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<File> downloadedFiles;

    public Model() {
        downloadedFiles = new ArrayList<>();
    }

    public void addDownloadedFile(File file) {
        downloadedFiles.add(file);
    }
    public List<File> getDownloadedFiles() {
        return downloadedFiles;
    }

    // Ajoutez d'autres méthodes nécessaires pour manipuler les fichiers téléchargés selon vos besoins
}
