package ma.enset.javaexamrepository.dao.impl;

import ma.enset.javaexamrepository.dao.PlatPrincipalDAO;
import ma.enset.javaexamrepository.dao.SignletonConnexionDB;
import ma.enset.javaexamrepository.entity.PlatPrincipal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatPrincipalDAOImpl implements PlatPrincipalDAO {
    private final Connection connection;

    public PlatPrincipalDAOImpl() throws SQLException {
        this.connection = SignletonConnexionDB.getInstance().getConnection();
    }

    @Override
    public PlatPrincipal ajouter(PlatPrincipal platPrincipal) {
        String query = "INSERT INTO PlatPrincipal (nom, prixBase) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, platPrincipal.getNom());
            statement.setDouble(2, platPrincipal.getPrixBase());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    platPrincipal.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platPrincipal;
    }

    @Override
    public PlatPrincipal modifier(PlatPrincipal platPrincipal) {
        String query = "UPDATE PlatPrincipal SET nom = ?, prixBase = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, platPrincipal.getNom());
            statement.setDouble(2, platPrincipal.getPrixBase());
            statement.setInt(3, platPrincipal.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platPrincipal;
    }

    @Override
    public void supprimer(int id) {
        String query = "DELETE FROM PlatPrincipal WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlatPrincipal findById(int id) {
        String query = "SELECT * FROM PlatPrincipal WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new PlatPrincipal(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getDouble("prixBase")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PlatPrincipal> getAll() {
        List<PlatPrincipal> platPrincipals = new ArrayList<>();
        String query = "SELECT * FROM PlatPrincipal";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                platPrincipals.add(new PlatPrincipal(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getDouble("prixBase")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platPrincipals;
    }
}