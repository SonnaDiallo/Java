public class Etudiante {
    // Attributs de l'étudiante
    private String nom;
    private String prenom;
    private String classe;

    // Constructeur
    public Etudiante(String nom, String prenom, String classe) {
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    // Méthode pour afficher les infos de l'étudiante
    public void afficherInfos() {
        System.out.println(toString()); // Utilise toString() pour afficher
    }

    // Méthode toString pour un affichage propre
    @Override
    public String toString() {
        return "Etudiante { " +
               "Nom='" + nom + '\'' +
               ", Prénom='" + prenom + '\'' +
               ", Classe='" + classe + '\'' +
               " }";
    }
}
