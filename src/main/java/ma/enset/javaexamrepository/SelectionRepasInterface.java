package ma.enset.javaexamrepository;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.enset.javaexamrepository.dao.PlatPrincipalDAO;
import ma.enset.javaexamrepository.dao.impl.PlatPrincipalDAOImpl;
import ma.enset.javaexamrepository.entity.Client;
import ma.enset.javaexamrepository.entity.PlatPrincipal;

import java.sql.SQLException;

public class SelectionRepasInterface extends Application {
    private Client client;
    private ComboBox<PlatPrincipal> platPrincipalComboBox = new ComboBox<>();

    public SelectionRepasInterface(Client client) {
        this.client = client;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sélection de Repas");

        // Retrieve PlatPrincipal data from the database
        PlatPrincipalDAO platPrincipalDAO;
        try {
            platPrincipalDAO = new PlatPrincipalDAOImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        platPrincipalComboBox.getItems().addAll(platPrincipalDAO.getAll());

        Button nextButton = new Button("Suivant");
        nextButton.setOnAction(e -> {
            PlatPrincipal selectedPlat = platPrincipalComboBox.getValue();
            if (selectedPlat != null) {
                CustomizationInterface customizationInterface = new CustomizationInterface(client, selectedPlat);
                customizationInterface.start(new Stage());
                primaryStage.close();
            }
        });

        // Add a button to open the voice assistant interface
        Button voiceAssistantButton = new Button("Assistant Vocal");
        voiceAssistantButton.setOnAction(e -> {
            AssistantVocalInterface assistantVocalInterface = new AssistantVocalInterface();
            assistantVocalInterface.start(new Stage());
        });

        VBox layout = new VBox(10, platPrincipalComboBox, nextButton, voiceAssistantButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}