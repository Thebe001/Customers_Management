import java.rmi.*;

public class CliServer {
    public static void main(String[] args) {
        try {
            String[] nomsClients = {"Salma", "Ahmed", "Walid"};
            CliInterface cli = new Cli(nomsClients);
            Naming.rebind("CliService", cli);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
