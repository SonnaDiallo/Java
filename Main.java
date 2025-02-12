
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionEtudiant gestion = new GestionEtudiant();
        Scanner scanner = new Scanner(System.in);
        int choix;

        //boucle de choix
        do {
            System.out.println("\n=== Menu de Gestion des Étudiants ===");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Supprimer un étudiant");
            System.out.println("3. Afficher tous les étudiants");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la ligne restante

            switch (choix) {
                case 1:
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Classe : ");
                    String classe = scanner.nextLine();

                    Etudiante etu = new Etudiante(nom, prenom, classe);
                    gestion.ajouterEtudiant(etu);
                    break;

                case 2:
                    System.out.print("Nom de l'étudiant à supprimer : ");
                    String nomSuppr = scanner.nextLine();
                    System.out.print("Prénom de l'étudiant à supprimer : ");
                    String prenomSuppr = scanner.nextLine();

                    gestion.supprimerEtudiant(nomSuppr, prenomSuppr);
                    break;

                case 3:
                    gestion.afficherEtudiants();
                    break;

                case 4:
                    System.out.println("Fermeture du programme...");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 4);

        scanner.close();
    }
}
