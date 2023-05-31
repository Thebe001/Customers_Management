import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Cli extends UnicastRemoteObject implements CliInterface {
    private String[] Nom;
    private int N;
    CliInterface c;

    public void setCli(CliInterface c) throws RemoteException {
        this.c = c;
    }

    public Cli() throws RemoteException{
        N=0;
        Nom=null;
        c=null;
    }

    public Cli(String[] Nom) throws RemoteException {
        super();
        this.Nom = Nom;
        this.N = Nom.length;
        this.c = null;
    }

    @Override
    public String getListeCli() throws RemoteException {
        String liste = "";
        if(N!=0){
            for (int i = 0; i < N-1; i++) {
                liste += Nom[i] + " , ";
            }
            liste+= Nom[N-1];
        }
        else
            liste = "La liste est vide";
        return liste;
    }

    public void affiche(String s) throws RemoteException {
        System.out.println(s);
    }

    @Override
    public void addCli(String nomClient) throws RemoteException {
        String[] nouveauNom = new String[N + 1];
        for (int i = 0; i < N; i++) {
            nouveauNom[i] = Nom[i];
        }
        nouveauNom[N] = nomClient;
        Nom = nouveauNom;
        N++;
        c.affiche("Nouveau client ajoute : "+nomClient);
        c.affiche("Liste des Clients : \n"+getListeCli());
    }


    public void removeCli(String nom) throws RemoteException {
        boolean exist = false;
        for(String s : Nom){
            if((s.toUpperCase()).equals(nom.toUpperCase()))
                exist=true;
        }
        if (exist){
            String[] newNom = new String[N - 1];
            int j = 0;
            for (int i = 0; i < N; i++) {
                if (!(Nom[i].toUpperCase()).equals(nom.toUpperCase())) {
                    newNom[j] = Nom[i];
                    j++;
                }
            }
            N--;
            Nom = newNom;
            c.affiche("Client suprime : "+nom);
            c.affiche("Liste des Clients : "+getListeCli());
        }
        else {
            c.affiche("Ce nom n'existe pas dans la liste des clients");
            c.affiche("Liste des Clients : "+getListeCli());
        }
    }

	public int getNbCli() throws RemoteException {
        c.affiche("Le nombre total de client est : "+this.N);
        return N;
    }

	public String getCli(int index) throws RemoteException {
        if (index < 0 || index > N) {
            c.affiche("Index out of bounds");
            return "";
        }
        else {
            c.affiche("Le Client #" + (index + 1) + " est : " + Nom[index]);
            return Nom[index];
        }

    }

	public void clearCli() throws RemoteException {
        Nom = new String[0];
        N = 0;
        c.affiche("La liste des clients a ete suprime ");
        c.affiche("Liste des Clients : "+getListeCli());
    }
}












