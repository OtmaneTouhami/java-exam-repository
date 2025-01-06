package ma.enset.javaexamrepository;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AssistantVocalInterface extends Application {
    private AssistantVocal assistantVocal = new AssistantVocal();
    private TextArea commandesTextArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Assistant Vocal");

        // Create UI components
        Label titleLabel = new Label("Assistant Vocal - Serveur Plat");
        Button startButton = new Button("Démarrer l'enregistrement");
        Button processButton = new Button("Traiter la commande");
        commandesTextArea.setEditable(false);

        // Handle start button click
        startButton.setOnAction(e -> {
            assistantVocal.capturerCommandeAudio();
            String commande = assistantVocal.transcrireAudioTexte();
            commandesTextArea.appendText("Commande capturée: " + commande + "\n");
        });

        // Handle process button click
        processButton.setOnAction(e -> {
            String commande = assistantVocal.transcrireAudioTexte();
            assistantVocal.traiterCommande(commande);
            commandesTextArea.appendText("Commande traitée: " + commande + "\n");
        });

        // Layout
        VBox layout = new VBox(10, titleLabel, startButton, processButton, commandesTextArea);
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}