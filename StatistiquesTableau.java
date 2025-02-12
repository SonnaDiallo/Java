public class StatistiquesTableau {

    // Calcul de la moyenne
    public static double calculerMoyenne(int[] tableau) {
        int somme = 0;
        for (int valeur : tableau) {
            somme += valeur;
        }
        return (double) somme / tableau.length;
    }

    // Calcul de la médiane sans Arrays.sort()
    public static double calculerMediane(int[] tableau) {
        trierTableau(tableau); // On trie le tableau nous-même
        int n = tableau.length;
        if (n % 2 == 0) {
            return (tableau[n / 2 - 1] + tableau[n / 2]) / 2.0;
        } else {
            return tableau[n / 2];
        }
    }

    // Trier un tableau sans Arrays.sort() (tri à bulles)
    public static void trierTableau(int[] tableau) {
        int n = tableau.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tableau[j] > tableau[j + 1]) { 
                    int temp = tableau[j];
                    tableau[j] = tableau[j + 1];
                    tableau[j + 1] = temp;
                }
            }
        }
    }

    // Approximation de la racine carrée sans Math.sqrt()
    public static double racineCarree(double nombre) {
        double x = nombre;
        double y = 1;
        double precision = 0.00001;

        while (x - y > precision) {
            x = (x + y) / 2;
            y = nombre / x;
        }
        return x;
    }

    // Calcul de l'écart-type sans Math.pow() et Math.sqrt()
    public static double calculerEcartType(int[] tableau) {
        double moyenne = calculerMoyenne(tableau);
        double sommeCarres = 0;

        for (int valeur : tableau) {
            double diff = valeur - moyenne;
            sommeCarres += diff * diff;
        }

        return racineCarree(sommeCarres / tableau.length);
    }

    // Trouver la valeur minimale
    public static int trouverMin(int[] tableau) {
        int min = tableau[0];
        for (int valeur : tableau) {
            if (valeur < min) {
                min = valeur;
            }
        }
        return min;
    }

    // Trouver la valeur maximale
    public static int trouverMax(int[] tableau) {
        int max = tableau[0];
        for (int valeur : tableau) {
            if (valeur > max) {
                max = valeur;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] tableau = {5, 8, 3, 10, 2, 7, 6, 9, 4, 1};

        System.out.println("Tableau original : " + java.util.Arrays.toString(tableau));

        // Calcul des statistiques
        double moyenne = calculerMoyenne(tableau);
        double mediane = calculerMediane(tableau);
        double ecartType = calculerEcartType(tableau);

        // Trier le tableau manuellement
        trierTableau(tableau);

        // Trouver les valeurs min et max
        int min = trouverMin(tableau);
        int max = trouverMax(tableau);

        System.out.println("Tableau trié : " + java.util.Arrays.toString(tableau));
        System.out.println("Moyenne : " + moyenne);
        System.out.println("Médiane : " + mediane);
        System.out.println("Écart-type : " + ecartType);
        System.out.println("Valeur minimale : " + min);
        System.out.println("Valeur maximale : " + max);
    }
}
