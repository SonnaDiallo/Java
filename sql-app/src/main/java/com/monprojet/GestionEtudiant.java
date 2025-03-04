package com.monprojet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionEtudiant {
    public static void main(String[] args) {
        // Informations de connexion
        String url = "jdbc:mysql://localhost:3306/mabase"; // Remplace "mabase" par le nom de ta base
        String utilisateur = "root";
        String motDePasse = "";
        Connection connexion = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Établir la connexion
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie !");

            // Boucle principale du menu
            while (true) {
                afficherMenu();
                int choix = scanner.nextInt();
                scanner.nextLine(); // Consomme le saut de ligne après l'entier

                switch (choix) {
                    case 1:
                        ajouterUtilisateur(connexion, scanner);
                        break;
                    case 2:
                        supprimerUtilisateur(connexion, scanner);
                        break;
                    case 3:
                        afficherUtilisateurs(connexion);
                        break;
                    case 4:
                        System.out.println("Fermeture du programme...");
                        return; // Quitter le programme
                    default:
                        System.out.println("Choix invalide !");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
            System.err.println("Code d'état SQL : " + e.getSQLState());
            System.err.println("Cause de l'erreur : " + e.getCause());
        } finally {
            // Fermer la connexion et le scanner
            fermerConnexion(connexion);
            scanner.close();
        }
    }

    // Afficher le menu
    public static void afficherMenu() {
        System.out.println("\nQue voulez-vous faire ?");
        System.out.println("1 - Ajouter un utilisateur");
        System.out.println("2 - Supprimer un utilisateur");
        System.out.println("3 - Lister les utilisateurs");
        System.out.println("4 - Quitter");
        System.out.print("Choix : ");
    }

    // Ajouter un utilisateur
    public static void ajouterUtilisateur(Connection connexion, Scanner scanner) {
        System.out.print("Entrez le nom : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Entrez l'email : ");
        String email = scanner.nextLine();

        String sqlInsert = "INSERT INTO utilisateurs (nom, prenom, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sqlInsert)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Utilisateur ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    // Supprimer un utilisateur
    public static void supprimerUtilisateur(Connection connexion, Scanner scanner) {
        System.out.print("Entrez l'ID de l'utilisateur à supprimer : ");
        int idSuppr = scanner.nextInt();

        String sqlDelete = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sqlDelete)) {
            pstmt.setInt(1, idSuppr);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }

    // Lister les utilisateurs
    public static void afficherUtilisateurs(Connection connexion) {
        String sqlSelect = "SELECT id, nom, prenom, email FROM utilisateurs";
        try (Statement stmt = connexion.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {

            List<String> utilisateurs = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                utilisateurs.add("ID : " + id + ", Nom : " + nom + ", Prénom : " + prenom + ", Email : " + email);
            }

            if (utilisateurs.isEmpty()) {
                System.out.println("Aucun utilisateur trouvé.");
            } else {
                System.out.println("\nListe des utilisateurs :");
                for (String utilisateur : utilisateurs) {
                    System.out.println(utilisateur);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'affichage des utilisateurs : " + e.getMessage());
        }
    }

    // Fermer la connexion
    public static void fermerConnexion(Connection connexion) {
        if (connexion != null) {
            try {
                connexion.close();
                System.out.println("Connexion fermée avec succès.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}