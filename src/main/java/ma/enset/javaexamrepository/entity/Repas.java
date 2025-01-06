package ma.enset.javaexamrepository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Repas {
    private int id;
    private PlatPrincipal platPrincipal;
    private Map<Ingredient, Double> ingredients;
    private List<Supplement> supplements;

    public void ajouterIngredient(Ingredient ingredient, double quantity) {
        ingredients.put(ingredient, quantity);
    }
    public void ajouterSupplement(Supplement supplement) {
        supplements.add(supplement);
    }
    public double calculerTotal() {
        double total = platPrincipal.getPrixBase();
        for (Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
            total += entry.getKey().getPrixUnitaire() * entry.getValue();
        }
        for (Supplement supplement : supplements) {
            total += supplement.getPrixUnitaire();
        }
        return total;
    }
}
