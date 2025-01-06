package ma.enset.javaexamrepository;

import java.util.ArrayList;
import java.util.List;

public class AssistantVocal {
    private List<String> commandes = new ArrayList<>();

    // Simulate capturing audio
    public void capturerCommandeAudio() {
        System.out.println("Enregistrement audio en cours...");
    }

    // Simulate transcribing audio to text
    public String transcrireAudioTexte() {
        // Simulate a voice command
        return "Ajouter 2 plats de pâtes";
    }

    // Process the command and add it to the list
    public void traiterCommande(String commande) {
        commandes.add(commande);
        System.out.println("Commande traitée: " + commande);
    }

    // Get the list of commands
    public List<String> getCommandes() {
        return commandes;
    }
}