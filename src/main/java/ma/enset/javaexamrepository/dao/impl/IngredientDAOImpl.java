package ma.enset.javaexamrepository.dao.impl;

import ma.enset.javaexamrepository.dao.IngredientDAO;
import ma.enset.javaexamrepository.dao.SignletonConnexionDB;
import ma.enset.javaexamrepository.entity.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAOImpl implements IngredientDAO {
    private final Connection connection;

    public IngredientDAOImpl() throws SQLException {
        this.connection = SignletonConnexionDB.getInstance().getConnection();
    }

    @Override
    public Ingredient ajouter(Ingredient ingredient) {
        String query = "INSERT INTO Ingredient (nom, prixUnitaire) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, ingredient.getNom());
            statement.setDouble(2, ingredient.getPrixUnitaire());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ingredient.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

    @Override
    public Ingredient modifier(Ingredient ingredient) {
        String query = "UPDATE Ingredient SET nom = ?, prixUnitaire = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ingredient.getNom());
            statement.setDouble(2, ingredient.getPrixUnitaire());
            statement.setInt(3, ingredient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

    @Override
    public void supprimer(int id) {
        String query = "DELETE FROM Ingredient WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ingredient findById(int id) {
        String query = "SELECT * FROM Ingredient WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Ingredient(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getDouble("prixUnitaire")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ingredient> getAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        String query = "SELECT * FROM Ingredient";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ingredients.add(new Ingredient(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getDouble("prixUnitaire")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }
}