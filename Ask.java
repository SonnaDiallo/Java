import java.util.Scanner;

public class Ask {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();


        System.out.print("Entrez votre age : ");
        int age =scanner.nextInt();

        System.out.println("Bonjour, " + nom + "! vous avez " + age + "ans");

        scanner.close();
    }
    
}
