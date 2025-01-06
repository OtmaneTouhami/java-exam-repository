package ma.enset.javaexamrepository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
    private int id;
    private double prixTotal;
    private Client client;
    private List<Repas> repas;
    private LocalDate dateCommande;

    public void ajouterRepas(Repas repas) {
        this.repas.add(repas);
    }

    public void calculerTotalCommande() {
        prixTotal = 0;
        for (Repas r : repas) {
            prixTotal += r.calculerTotal();
        }}
}
