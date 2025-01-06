package ma.enset.javaexamrepository.entity;

import java.util.List;

public class Client {
    private int id;
    private String nom;
    private String email;
    private String phoneNumber;
    private List<Commande> commandes;

    public Client() {
    }

    public Client(int id, String nom, String email, String phoneNumber) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Client(String nom, String email, String phoneNumber) {
        this.nom = nom;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    private void ajouterCommande(Commande commande) {
        this.commandes.add(commande);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
