package Model;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    public boolean verifyDownload(){
        return (downloadedFiles[0]!=null && downloadedFiles[1]!=null);
    }
    /*public void addDownloadedFile(File file) {
        downloadedFiles.add(file);
    }
    public List<File> getDownloadedFiles() {
        return downloadedFiles;
    }*/

    // Ajoutez d'autres méthodes nécessaires pour manipuler les fichiers téléchargés selon vos besoins
}
