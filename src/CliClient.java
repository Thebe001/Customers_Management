import java.rmi.*;
import java.util.*;

public class CliClient {
    public static void main(String[] args) {
        System.out.println("Voici la liste des commande : ");
        System.out.println("1) Afficher la liste des clients");
        System.out.println("2) Afficher le nombre de client");
        System.out.println("3) Ajouter un client");
        System.out.println("4) Supprimer un client");
        System.out.println("5) Acceder a un client par son index");
        System.out.println("6) vider la liste des clients");

        try {
            Cli c = new Cli();
            CliInterface cli = (CliInterface) Naming.lookup("CliService");
            cli.setCli(c);
            Scanner sc = new Scanner(System.in);
            String input;
            int commande;
            while (true) {
                System.out.println("Choisissez une commande a executer : ");
                input = sc.nextLine();
                if ((Character.isDigit(input.charAt(0))) && (Integer.parseInt(String.valueOf(input.charAt(0))) >= 1) && (Integer.parseInt(String.valueOf(input.charAt(0))) <= 6))
                {
                    commande = Integer.parseInt(String.valueOf(input.charAt(0)));
                    switch (commande) {
                        case 1:
                            System.out.println("La liste des client : ");
                            System.out.println(cli.getListeCli());
                            break;
                        case 2:
                            cli.getNbCli();
                            break;
                        case 3:
                            System.out.println("Saisissez le nom du nouveau client : ");
                            cli.addCli(sc.nextLine());
                            break;
                        case 4:
                            System.out.println("Saisissez le nom du client a suprimer: ");
                            cli.removeCli(sc.nextLine());
                            break;
                        case 5:
                            System.out.println("Saisissez L'index du client : ");
                            if (sc.hasNextInt()) {
                                cli.getCli(sc.nextInt() - 1);
                                sc.nextLine();
                            } else System.out.println("index invalide");
                            break;
                        case 6:
                            cli.clearCli();
                            break;
                    }
                }
                else
                System.out.println("commande inexistante");
            }
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
