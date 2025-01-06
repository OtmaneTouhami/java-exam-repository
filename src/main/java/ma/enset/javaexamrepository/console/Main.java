package ma.enset.javaexamrepository.console;


import ma.enset.javaexamrepository.dao.*;
import ma.enset.javaexamrepository.dao.impl.ClientDAOImpl;
import ma.enset.javaexamrepository.dao.impl.IngredientDAOImpl;
import ma.enset.javaexamrepository.dao.impl.PlatPrincipalDAOImpl;
import ma.enset.javaexamrepository.dao.impl.SupplementDAOImpl;
import ma.enset.javaexamrepository.entity.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ClientDAO clientDAO;
    private static IngredientDAO ingredientDAO;
    private static PlatPrincipalDAO platPrincipalDAO;
    private static SupplementDAO supplementDAO;

    public static void main(String[] args) {
        try {
            // Initialize DAOs
            clientDAO = new ClientDAOImpl();
            ingredientDAO = new IngredientDAOImpl();
            platPrincipalDAO = new PlatPrincipalDAOImpl();
            supplementDAO = new SupplementDAOImpl();

            // Create a client and save it to the database
            Client client = createClient();
            client = clientDAO.ajouter(client);
            System.out.println("Client saved to database with ID: " + client.getId());

            // Create a commande (in memory, not saved to the database)
            Commande commande = createCommande(client);

            // Add repas to the commande (in memory, not saved to the database)
            boolean addMoreRepas = true;
            while (addMoreRepas) {
                Repas repas = createRepas();
                commande.ajouterRepas(repas);

                System.out.print("Do you want to add another repas? (yes/no): ");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    addMoreRepas = false;
                }
            }

            // Calculate the total price of the commande
            commande.calculerTotalCommande();

            // Generate and display the ticket
            generateTicket(commande);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Client createClient() {
        System.out.println("Enter client details:");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        return new Client(nom, email, phoneNumber);
    }

    private static Commande createCommande(Client client) {
        System.out.println("Creating a new commande...");
        return new Commande(0, 0, client, new ArrayList<>(), LocalDate.now());
    }

    private static Repas createRepas() {
        System.out.println("Enter repas details:");

        // Create PlatPrincipal and save it to the database
        System.out.print("Plat Principal Name: ");
        String platPrincipalName = scanner.nextLine();
        System.out.print("Plat Principal Price: ");
        double platPrincipalPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        PlatPrincipal platPrincipal = new PlatPrincipal(platPrincipalName, platPrincipalPrice);
        platPrincipal = platPrincipalDAO.ajouter(platPrincipal);
        System.out.println("PlatPrincipal saved to database with ID: " + platPrincipal.getId());

        // Create Ingredients and save them to the database
        Map<Ingredient, Double> ingredients = new HashMap<>();
        boolean addMoreIngredients = true;
        while (addMoreIngredients) {
            System.out.print("Ingredient Name: ");
            String ingredientName = scanner.nextLine();
            System.out.print("Ingredient Price per Unit: ");
            double ingredientPrice = scanner.nextDouble();
            System.out.print("Ingredient Quantity (in grams): ");
            double ingredientQuantity = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Ingredient ingredient = new Ingredient(ingredientName, ingredientPrice);
            ingredient = ingredientDAO.ajouter(ingredient);
            ingredients.put(ingredient, ingredientQuantity);
            System.out.println("Ingredient saved to database with ID: " + ingredient.getId());

            System.out.print("Do you want to add another ingredient? (yes/no): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                addMoreIngredients = false;
            }
        }

        // Create Supplements and save them to the database
        List<Supplement> supplements = new ArrayList<>();
        boolean addMoreSupplements = true;
        while (addMoreSupplements) {
            System.out.print("Supplement Name: ");
            String supplementName = scanner.nextLine();
            System.out.print("Supplement Price: ");
            double supplementPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Supplement supplement = new Supplement(supplementName, supplementPrice);
            supplement = supplementDAO.ajouter(supplement);
            supplements.add(supplement);
            System.out.println("Supplement saved to database with ID: " + supplement.getId());

            System.out.print("Do you want to add another supplement? (yes/no): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                addMoreSupplements = false;
            }
        }

        // Create and return the Repas (in memory, not saved to the database)
        return new Repas(0, platPrincipal, ingredients, supplements);
    }

    private static void generateTicket(Commande commande) {
        System.out.println("\n---TICKET---");
        System.out.println("Nom: " + commande.getClient().getNom());
        System.out.println("Nombre de repas: " + commande.getRepas().size());

        int repasNumber = 1;
        for (Repas repas : commande.getRepas()) {
            System.out.println("Repas N°" + repasNumber + ": " + repas.getPlatPrincipal().getNom());
            System.out.println("Ingrédients:");
            for (Map.Entry<Ingredient, Double> entry : repas.getIngredients().entrySet()) {
                System.out.println(entry.getKey().getNom() + ": " + entry.getValue() + " gramme");
            }
            System.out.println("Suppléments:");
            for (Supplement supplement : repas.getSupplements()) {
                System.out.println(supplement.getNom() + " " + supplement.getPrixUnitaire());
            }
            System.out.println("*******");
            repasNumber++;
        }

        System.out.println("---Total: " + commande.getPrixTotal() + "---");
    }
}