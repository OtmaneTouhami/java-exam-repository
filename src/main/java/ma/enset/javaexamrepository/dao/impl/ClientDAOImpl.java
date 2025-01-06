package ma.enset.javaexamrepository.dao.impl;

import ma.enset.javaexamrepository.dao.ClientDAO;
import ma.enset.javaexamrepository.dao.SignletonConnexionDB;
import ma.enset.javaexamrepository.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private final Connection connection;

    public ClientDAOImpl() throws SQLException {
        this.connection = SignletonConnexionDB.getInstance().getConnection();
    }

    @Override
    public Client ajouter(Client client) {
        String query = "INSERT INTO Client (nom, email, phoneNumber) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPhoneNumber());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client modifier(Client client) {
        String query = "UPDATE Client SET nom = ?, email = ?, phoneNumber = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPhoneNumber());
            statement.setInt(4, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void supprimer(int id) {
        String query = "DELETE FROM Client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client findById(int id) {
        String query = "SELECT * FROM Client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("email"),
                            resultSet.getString("phoneNumber")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Client";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                clients.add(new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
