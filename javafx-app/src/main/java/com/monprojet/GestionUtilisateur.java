package com.monprojet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestionUtilisateur {

    private Connection connexion;

    public GestionUtilisateur(Connection connexion) {
        this.connexion = connexion;
    }

    public List<Utilisateur> getUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            if (connexion != null) {
                Statement statement = connexion.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateurs"); // Remplace par ta requête SQL

                // Dans GestionUtilisateur.java
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String email = resultSet.getString("email");

                    Utilisateur utilisateur = new Utilisateur(id, nom, prenom, email);
                    utilisateurs.add(utilisateur);
                }
            } else {
                System.err.println("Erreur : La connexion est null.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        try {
            if (connexion != null) {
                String query = "INSERT INTO utilisateurs (nom, prenom, email) VALUES (?, ?, ?)"; // Remplace par ta requête SQL
                PreparedStatement preparedStatement = connexion.prepareStatement(query);
                preparedStatement.setString(1, utilisateur.getNom());
                preparedStatement.setString(2, utilisateur.getPrenom());
                preparedStatement.setString(3, utilisateur.getEmail());
                preparedStatement.executeUpdate();
            } else {
                System.err.println("Erreur : La connexion est null.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modifierUtilisateur(Utilisateur utilisateur) {
        try {
            if (connexion != null) {
                String query = "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ? WHERE id = ?"; // Remplace par ta requête SQL
                PreparedStatement preparedStatement = connexion.prepareStatement(query);
                preparedStatement.setString(1, utilisateur.getNom());
                preparedStatement.setString(2, utilisateur.getPrenom());
                preparedStatement.setString(3, utilisateur.getEmail());
                preparedStatement.setInt(4, utilisateur.getId());
                preparedStatement.executeUpdate();
            } else {
                System.err.println("Erreur : La connexion est null.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {
        try {
            if (connexion != null) {
                // Requête de suppression
                String query = "DELETE FROM utilisateurs WHERE id = ?";
                PreparedStatement preparedStatement = connexion.prepareStatement(query);
                preparedStatement.setInt(1, utilisateur.getId());
                preparedStatement.executeUpdate();
                
                // Réinitialiser le compteur
                Statement stmt = connexion.createStatement();
                stmt.execute("ALTER TABLE utilisateurs AUTO_INCREMENT = 1");
            }
        } catch (SQLException e) {
            // Gestion des erreurs
        }
    }
    
}
