package ma.enset.javaexamrepository;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.enset.javaexamrepository.dao.ClientDAO;
import ma.enset.javaexamrepository.dao.impl.ClientDAOImpl;
import ma.enset.javaexamrepository.entity.Client;

import java.sql.SQLException;

public class ClientCredentialsInterface extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Entrez vos informations");

        // Create form fields
        Label nomLabel = new Label("Nom:");
        TextField nomField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label phoneLabel = new Label("Numéro de téléphone:");
        TextField phoneField = new TextField();

        Button submitButton = new Button("Soumettre");
        submitButton.setOnAction(e -> {
            String nom = nomField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneField.getText();

            if (!nom.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty()) {
                Client client = new Client(nom, email, phoneNumber);
                ClientDAO clientDAO;
                try {
                    clientDAO = new ClientDAOImpl();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                clientDAO.ajouter(client);
                System.out.println("Client enregistré avec succès!");

                // Open the next interface (SelectionRepasInterface)
                SelectionRepasInterface selectionRepasInterface = new SelectionRepasInterface(client);
                selectionRepasInterface.start(new Stage());
                primaryStage.close();
            } else {
                System.out.println("Veuillez remplir tous les champs!");
            }
        });

        VBox layout = new VBox(10, nomLabel, nomField, emailLabel, emailField, phoneLabel, phoneField, submitButton);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}