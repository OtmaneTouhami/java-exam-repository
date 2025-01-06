package ma.enset.javaexamrepository.dao;

import ma.enset.javaexamrepository.dao.impl.IngredientDAOImpl;
import ma.enset.javaexamrepository.dao.impl.PlatPrincipalDAOImpl;
import ma.enset.javaexamrepository.dao.impl.SupplementDAOImpl;
import ma.enset.javaexamrepository.entity.*;

public class DatabasePopulator {
    public static void main(String[] args) {
        try {
            // Initialize DAOs
            PlatPrincipalDAO platPrincipalDAO = new PlatPrincipalDAOImpl();
            IngredientDAO ingredientDAO = new IngredientDAOImpl();
            SupplementDAO supplementDAO = new SupplementDAOImpl();

            // Add PlatPrincipal
            PlatPrincipal tajineViande = new PlatPrincipal("Tajine de viande & Pruneaux", 150.0);
            PlatPrincipal tajinePoulet = new PlatPrincipal("Tajine de poulet & légumes", 130.0);
            platPrincipalDAO.ajouter(tajineViande);
            platPrincipalDAO.ajouter(tajinePoulet);

            // Add Ingredients
            Ingredient viande = new Ingredient("Viande", 70);
            Ingredient pruneaux = new Ingredient("Pruneaux", 10);
            Ingredient poulet = new Ingredient("Poulet", 40);
            Ingredient carotte = new Ingredient("Carotte", 6);
            ingredientDAO.ajouter(viande);
            ingredientDAO.ajouter(pruneaux);
            ingredientDAO.ajouter(poulet);
            ingredientDAO.ajouter(carotte);

            // Add Supplements
            Supplement frites = new Supplement("Frites", 11.0);
            Supplement boisson = new Supplement("Boisson", 12.0);
            Supplement jusOrange = new Supplement("Jus d'orange", 13.0);
            Supplement saladeMarocaine = new Supplement("Salade marocaine", 14.0);
            supplementDAO.ajouter(frites);
            supplementDAO.ajouter(boisson);
            supplementDAO.ajouter(jusOrange);
            supplementDAO.ajouter(saladeMarocaine);

            System.out.println("Database populated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}