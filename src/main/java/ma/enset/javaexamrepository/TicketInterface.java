package ma.enset.javaexamrepository;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.enset.javaexamrepository.entity.*;

import java.util.List;

public class TicketInterface extends Application {
    private Client client;
    private PlatPrincipal platPrincipal;
    private List<Ingredient> ingredients;
    private List<Supplement> supplements;

    public TicketInterface(Client client, PlatPrincipal platPrincipal, List<Ingredient> ingredients, List<Supplement> supplements) {
        this.client = client;
        this.platPrincipal = platPrincipal;
        this.ingredients = ingredients;
        this.supplements = supplements;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ticket");

        VBox layout = new VBox(10);
        layout.getChildren().add(new Label("--- TICKET ---"));
        layout.getChildren().add(new Label("Client: " + client.getNom()));
        layout.getChildren().add(new Label("Plat Principal: " + platPrincipal.getNom()));

        layout.getChildren().add(new Label("Ingrédients:"));
        for (Ingredient ingredient : ingredients) {
            layout.getChildren().add(new Label("- " + ingredient.getNom() + ": " + ingredient.getPrixUnitaire() + " MAD"));
        }

        layout.getChildren().add(new Label("Suppléments:"));
        for (Supplement supplement : supplements) {
            layout.getChildren().add(new Label("- " + supplement.getNom() + ": " + supplement.getPrixUnitaire() + " MAD"));
        }

        double total = platPrincipal.getPrixBase();
        for (Ingredient ingredient : ingredients) {
            total += ingredient.getPrixUnitaire();
        }
        for (Supplement supplement : supplements) {
            total += supplement.getPrixUnitaire();
        }
        layout.getChildren().add(new Label("--- Total: " + total + " MAD ---"));

        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}