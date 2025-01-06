package ma.enset.javaexamrepository.entity;

public class PlatPrincipal {
    private int id;
    private String nom;
    private double prixBase;

    public PlatPrincipal() {
    }

    public PlatPrincipal(int id, String nom, double prixBase) {
        this.id = id;
        this.nom = nom;
        this.prixBase = prixBase;
    }

    public PlatPrincipal(String nom, double prixBase) {
        this.nom = nom;
        this.prixBase = prixBase;
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

    public double getPrixBase() {
        return prixBase;
    }

    public void setPrixBase(double prixBase) {
        this.prixBase = prixBase;
    }

    @Override
    public String toString() {
        return this.getNom();
    }
}
