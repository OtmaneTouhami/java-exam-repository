package ma.enset.javaexamrepository.dao.impl;

import ma.enset.javaexamrepository.dao.SignletonConnexionDB;
import ma.enset.javaexamrepository.dao.SupplementDAO;
import ma.enset.javaexamrepository.entity.Supplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAOImpl implements SupplementDAO {
    private final Connection connection;

    public SupplementDAOImpl() throws SQLException {
        this.connection = SignletonConnexionDB.getInstance().getConnection();
    }

    @Override
    public Supplement ajouter(Supplement supplement) {
        String query = "INSERT INTO Supplement (nom, prixUnitaire) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, supplement.getNom());
            statement.setDouble(2, supplement.getPrixUnitaire());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    supplement.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplement;
    }

    @Override
    public Supplement modifier(Supplement supplement) {
        String query = "UPDATE Supplement SET nom = ?, prixUnitaire = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, supplement.getNom());
            statement.setDouble(2, supplement.getPrixUnitaire());
            statement.setInt(3, supplement.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplement;
    }

    @Override
    public void supprimer(int id) {
        String query = "DELETE FROM Supplement WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplement findById(int id) {
        String query = "SELECT * FROM Supplement WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Supplement(
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
    public List<Supplement> getAll() {
        List<Supplement> supplements = new ArrayList<>();
        String query = "SELECT * FROM Supplement";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                supplements.add(new Supplement(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getDouble("prixUnitaire")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplements;
    }
}