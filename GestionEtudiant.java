import java.util.ArrayList;
import java.util.Iterator;

public class GestionEtudiant {
    private ArrayList<Etudiante> listeEtudiants;

    // Constructeur
    public GestionEtudiant() {
        listeEtudiants = new ArrayList<>();
    }

    // Ajouter un étudiant
    public void ajouterEtudiant(Etudiante e) {
        listeEtudiants.add(e);
        System.out.println(e.getPrenom() + " " + e.getNom() + " a été ajouté(e).");
    }

    // Supprimer un étudiant par nom et prénom
    public void supprimerEtudiant(String nom, String prenom) {
        Iterator<Etudiante> it = listeEtudiants.iterator();
        while (it.hasNext()) {
            Etudiante e = it.next();
            if (e.getNom().equalsIgnoreCase(nom) && e.getPrenom().equalsIgnoreCase(prenom)) {
                it.remove();
                System.out.println(prenom + " " + nom + " a été supprimé(e).");
                return;
            }
        }
        System.out.println("Étudiant(e) non trouvé(e).");
    }

    // Afficher tous les étudiants
    public void afficherEtudiants() {
        if (listeEtudiants.isEmpty()) {
            System.out.println("Aucun étudiant dans la liste.");
        } else {
            System.out.println("Liste des étudiants :");
            for (Etudiante e : listeEtudiants) {
                System.out.println(e);
            }
        }
    }
}
