package ma.enset.javaexamrepository.dao;

import ma.enset.javaexamrepository.entity.Ingredient;

import java.util.List;

public interface IngredientDAO {
    Ingredient ajouter(Ingredient ingredient);
    Ingredient modifier(Ingredient ingredient);
    void supprimer(int id);
    Ingredient findById(int id);
    List<Ingredient> getAll();
}
