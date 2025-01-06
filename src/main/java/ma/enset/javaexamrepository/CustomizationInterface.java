package ma.enset.javaexamrepository;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.enset.javaexamrepository.dao.IngredientDAO;
import ma.enset.javaexamrepository.dao.impl.IngredientDAOImpl;
import ma.enset.javaexamrepository.dao.SupplementDAO;
import ma.enset.javaexamrepository.dao.impl.SupplementDAOImpl;
import ma.enset.javaexamrepository.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomizationInterface extends Application {
    private Client client;
    private PlatPrincipal platPrincipal;
    private List<Ingredient> selectedIngredients = new ArrayList<>();
    private List<Supplement> selectedSupplements = new ArrayList<>();
    private Label totalLabel = new Label("Total: 0.0 MAD");

    public CustomizationInterface(Client client, PlatPrincipal platPrincipal) {
        this.client = client;
        this.platPrincipal = platPrincipal;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Personnalisation du Repas");

        // Retrieve Ingredient and Supplement data from the database
        IngredientDAO ingredientDAO;
        try {
            ingredientDAO = new IngredientDAOImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SupplementDAO supplementDAO;
        try {
            supplementDAO = new SupplementDAOImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Ingredient> ingredients = ingredientDAO.getAll();
        List<Supplement> supplements = supplementDAO.getAll();

        VBox layout = new VBox(10);
        layout.getChildren().add(new Label("Plat Principal: " + platPrincipal.getNom()));

        // Add Ingredients
        layout.getChildren().add(new Label("Ingrédients:"));
        for (Ingredient ingredient : ingredients) {
            CheckBox checkBox = new CheckBox(ingredient.getNom() + " (" + ingredient.getPrixUnitaire() + ")");
            checkBox.setOnAction(e -> {
                if (checkBox.isSelected()) {
                    selectedIngredients.add(ingredient);
                } else {
                    selectedIngredients.remove(ingredient);
                }
                updateTotal();
            });
            layout.getChildren().add(checkBox);
        }

        // Add Supplements
        layout.getChildren().add(new Label("Suppléments:"));
        for (Supplement supplement : supplements) {
            CheckBox checkBox = new CheckBox(supplement.getNom() + " (" + supplement.getPrixUnitaire() + ")");
            checkBox.setOnAction(e -> {
                if (checkBox.isSelected()) {
                    selectedSupplements.add(supplement);
                } else {
                    selectedSupplements.remove(supplement);
                }
                updateTotal();
            });
            layout.getChildren().add(checkBox);
        }

        // Bouton pour enregistrer le choix
        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> {
            TicketInterface ticketInterface = new TicketInterface(client, platPrincipal, selectedIngredients, selectedSupplements);
            ticketInterface.start(new Stage());
            primaryStage.close();
        });

        layout.getChildren().addAll(totalLabel, saveButton);
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateTotal() {
        double total = platPrincipal.getPrixBase();
        for (Ingredient ingredient : selectedIngredients) {
            total += ingredient.getPrixUnitaire();
        }
        for (Supplement supplement : selectedSupplements) {
            total += supplement.getPrixUnitaire();
        }
        totalLabel.setText("Total: " + total + " MAD");
    }

    public static void main(String[] args) {
        launch(args);
    }
}