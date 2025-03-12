package com.monprojet;

import com.monprojet.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AppController {

    @FXML
    private TableView<Utilisateur> tableUtilisateurs;
    @FXML
    private TableColumn<Utilisateur, Integer> colId;
    @FXML
    private TableColumn<Utilisateur, String> colNom;
    @FXML
    private TableColumn<Utilisateur, String> colPrenom;
    @FXML
    private TableColumn<Utilisateur, String> colEmail;

    private Connection connexion;

    @FXML
    public void initialize() {
        // Connexion à la BDD
        try {
            // Remplace les valeurs par tes informations de connexion
            String url = "jdbc:mysql://localhost:3306/mabase";
            String utilisateur = "root";
            String motDePasse = ""; // Remplace par ton mot de passe

            // Établir la connexion
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

            // Vérifier si la connexion est établie
            if (connexion != null) {
                System.out.println("Connexion à la base de données réussie !");
            } else {
                System.err.println("Erreur : La connexion est null.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }

        // Lier les colonnes aux propriétés de `Utilisateur`
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Charger les utilisateurs
        chargerUtilisateurs();
    }

    private void chargerUtilisateurs() {
        if (connexion != null) {
            try {
                GestionUtilisateur gestionUtilisateur = new GestionUtilisateur(connexion);
                List<Utilisateur> utilisateurs = gestionUtilisateur.getUtilisateurs();
                tableUtilisateurs.getItems().setAll(utilisateurs);
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("La connexion est null, impossible de charger les utilisateurs.");
        }
    }

    @FXML
    private void ajouterUtilisateur() {
        // 1. Créer une boîte de dialogue pour saisir les informations de l'utilisateur
        TextInputDialog dialogNom = new TextInputDialog();
        dialogNom.setTitle("Ajouter un utilisateur");
        dialogNom.setHeaderText("Saisir le nom du nouvel utilisateur :");
        dialogNom.setContentText("Nom:");
        Optional<String> resultNom = dialogNom.showAndWait();

        TextInputDialog dialogPrenom = new TextInputDialog();
        dialogPrenom.setTitle("Ajouter un utilisateur");
        dialogPrenom.setHeaderText("Saisir le prénom du nouvel utilisateur :");
        dialogPrenom.setContentText("Prénom:");
        Optional<String> resultPrenom = dialogPrenom.showAndWait();

        TextInputDialog dialogEmail = new TextInputDialog();
        dialogEmail.setTitle("Ajouter un utilisateur");
        dialogEmail.setHeaderText("Saisir l'email du nouvel utilisateur :");
        dialogEmail.setContentText("Email:");
        Optional<String> resultEmail = dialogEmail.showAndWait();


        // 4. Créer l'utilisateur et l'ajouter à la base de données
        if (resultNom.isPresent() && resultPrenom.isPresent() && resultEmail.isPresent()) {
            String nom = resultNom.get();
            String prenom = resultPrenom.get();
            String email = resultEmail.get();

            Utilisateur nouvelUtilisateur = new Utilisateur(0, nom, prenom, email);
            if (connexion != null) {
                try {
                    GestionUtilisateur gestionUtilisateur = new GestionUtilisateur(connexion);
                    gestionUtilisateur.ajouterUtilisateur(nouvelUtilisateur);
                    chargerUtilisateurs();
                } catch (Exception e) {
                    System.err.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.err.println("La connexion est null, impossible d'ajouter l'utilisateur.");
            }
        }
    }

    @FXML
    private void modifierUtilisateur() {
        // 1. Récupérer l'utilisateur sélectionné
        Utilisateur utilisateurSelectionne = tableUtilisateurs.getSelectionModel().getSelectedItem();

        if (utilisateurSelectionne != null) {

            TextInputDialog dialogNom = new TextInputDialog(utilisateurSelectionne.getNom());
            dialogNom.setTitle("Modifier un utilisateur");
            dialogNom.setHeaderText("Saisir le nouveau nom de l'utilisateur :");
            dialogNom.setContentText("Nom:");
            Optional<String> resultNom = dialogNom.showAndWait();

            TextInputDialog dialogPrenom = new TextInputDialog(utilisateurSelectionne.getPrenom());
            dialogPrenom.setTitle("Modifier un utilisateur");
            dialogPrenom.setHeaderText("Saisir le nouveau prenom de l'utilisateur :");
            dialogPrenom.setContentText("Prenom:");
            Optional<String> resultPrenom = dialogPrenom.showAndWait();

            TextInputDialog dialogEmail = new TextInputDialog(utilisateurSelectionne.getEmail());
            dialogEmail.setTitle("Modifier un utilisateur");
            dialogEmail.setHeaderText("Saisir le nouveau email de l'utilisateur :");
            dialogEmail.setContentText("Email:");
            Optional<String> resultEmail = dialogEmail.showAndWait();
            // 3. Mettre à jour l'utilisateur dans la base de données
            if (resultNom.isPresent() && resultPrenom.isPresent() && resultEmail.isPresent()) {
                utilisateurSelectionne.setNom(resultNom.get());
                utilisateurSelectionne.setPrenom(resultPrenom.get());
                utilisateurSelectionne.setEmail(resultEmail.get());


                if (connexion != null) {
                    try {
                        GestionUtilisateur gestionUtilisateur = new GestionUtilisateur(connexion);
                        gestionUtilisateur.modifierUtilisateur(utilisateurSelectionne);
                        chargerUtilisateurs();
                    } catch (Exception e) {
                        System.err.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("La connexion est null, impossible de modifier l'utilisateur.");
                }
            }
        } else {
            System.out.println("Aucun utilisateur sélectionné.");
        }
    }

    @FXML
    private void supprimerUtilisateur() {
        // 1. Récupérer l'utilisateur sélectionné
        Utilisateur utilisateurSelectionne = tableUtilisateurs.getSelectionModel().getSelectedItem();

        if (utilisateurSelectionne != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Confirmation de suppression");
            alert.setContentText("Etes vous sur de vouloir supprimer cet utilisateur ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if (connexion != null) {
                    try {
                        GestionUtilisateur gestionUtilisateur = new GestionUtilisateur(connexion);
                        gestionUtilisateur.supprimerUtilisateur(utilisateurSelectionne);
                        chargerUtilisateurs();
                    } catch (Exception e) {
                        System.err.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("La connexion est null, impossible de supprimer l'utilisateur.");
                }
            } else {
                System.out.println("Suppression annulee !");
            }
        } else {
            System.out.println("Aucun utilisateur selectionne !");
        }
    }
}
