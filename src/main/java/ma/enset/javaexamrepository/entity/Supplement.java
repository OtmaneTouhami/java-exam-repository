package ma.enset.javaexamrepository.entity;

public class Supplement {
    private int id;
    private String nom;
    private double prixUnitaire;

    public Supplement() {
    }

    public Supplement(int id, String nom, double prixUnitaire) {
        this.id = id;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
    }

    public Supplement(String nom, double prixUnitaire) {
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
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

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
