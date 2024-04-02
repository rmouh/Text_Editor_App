package Vue;
import Controler.Controler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;


public class Vue {
    private AnchorPane root;

    public Vue() {
        root = new AnchorPane();
        //initializeComponents();
    }

   /* private void initializeComponents() {
        Button uploadOriginalFileButton = createUploadButton("Upload Original File", 179.0, 1);
        Button uploadEditedFileButton = createUploadButton("Upload Edited File", 179.0, 2);

        // Ajouter les boutons à la vue
        addToView(uploadOriginalFileButton);
        addToView(uploadEditedFileButton);

        // Chargement de l'image depuis les ressources du projet
        Image image = new Image(getClass().getResourceAsStream("/icon_txt.png"));
        ImageView imageView1 = createImageView(image, 150.0, 200.0, 1);
        ImageView imageView2 = createImageView(image, 150.0, 200.0, 2);

        // Ajouter les images à la vue
        root.getChildren().addAll(imageView1, imageView2);
    }*/

    public Button createUploadButton(String buttonText, double width, int n) {
        Button uploadButton = new Button(buttonText);
        uploadButton.setPrefWidth(width);
        uploadButton.setFont(Font.font("Cooper Black", 15.0));
        AnchorPane.setTopAnchor(uploadButton, 94.0);
        if (n == 1)
            AnchorPane.setRightAnchor(uploadButton, 66.0);
        else
            AnchorPane.setLeftAnchor(uploadButton, 66.0);
        return uploadButton;
    }

    public void addToView(Button button1, Button button2, ImageView imageView1 , ImageView imageView2) {
        root.getChildren().addAll(button1, button2, imageView1, imageView2);
    }

    public AnchorPane getLayout() {
        return root;
    }
    public ImageView createImageView (Image image, double height, double width, int n) {
        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(height);
        imageView2.setFitWidth(width);
        AnchorPane.setTopAnchor(imageView2, 200.0);
        if (n==1)
            AnchorPane.setRightAnchor(imageView2, 100.0);
        else
            AnchorPane.setLeftAnchor(imageView2, 100.0);
        return imageView2;
    }
}


    /*
    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();

        // Créer les boutons et les images
        Button uploadOriginalFileButton = createUploadButton("Upload Original File", 179.0, 1);
        Button uploadEditedFileButton = createUploadButton("Upload Edited File", 179.0, 2);

        Image image = new Image(getClass().getResourceAsStream("/icon_txt.png"));
        ImageView imageView1 = createImageView(image, 150.0, 200.0, 1);
        ImageView imageView2 = createImageView(image, 150.0, 200.0, 2);

        // Ajouter les boutons et les images à la vue
        addToView(root, uploadOriginalFileButton, imageView1);
        addToView(root, uploadEditedFileButton, imageView2);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
*/
/*
public class Vue extends Application {



    public Button createUploadButton(String buttonText, double width, int n) {
        Button uploadOriginalFileButton = new Button(buttonText);
        uploadOriginalFileButton.setPrefWidth(width);
        uploadOriginalFileButton.setFont(Font.font("Cooper Black", 15.0));
        AnchorPane.setTopAnchor(uploadOriginalFileButton, 94.0);
        if (n==1)
            AnchorPane.setRightAnchor(uploadOriginalFileButton, 66.0);
        else
            AnchorPane.setLeftAnchor(uploadOriginalFileButton, 66.0);
        return uploadOriginalFileButton;
    }

    public ImageView createImageView (Image image, double height, double width, int n) {
        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(height);
        imageView2.setFitWidth(width);
        AnchorPane.setTopAnchor(imageView2, 200.0);
        if (n==1)
            AnchorPane.setRightAnchor(imageView2, 100.0);
        else
            AnchorPane.setLeftAnchor(imageView2, 100.0);
        return imageView2;
    }

    // Méthode pour ajouter des composants à la vue
    public void addToView(AnchorPane root, Button button, ImageView imageView) {
        // Ajoutez les composants à la racine (root) de la vue
        // Exemple : root.getChildren().addAll(button, imageView);

    }

        public static void main(String[] args) {
        launch(args);
    }
}
*/