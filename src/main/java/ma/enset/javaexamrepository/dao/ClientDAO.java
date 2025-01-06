package ma.enset.javaexamrepository.dao;

import ma.enset.javaexamrepository.entity.Client;

import java.util.List;

public interface ClientDAO {
    Client ajouter(Client client);
    Client modifier(Client client);
    void supprimer(int id);
    Client findById(int id);
    List<Client> getAll();
}
