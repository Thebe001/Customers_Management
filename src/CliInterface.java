import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CliInterface extends Remote {
	String getListeCli() throws RemoteException;
	void addCli(String nom) throws RemoteException;
	public void setCli(CliInterface c) throws RemoteException;
	public void affiche(String s) throws RemoteException;
	void removeCli(String nom) throws RemoteException;
	int getNbCli() throws RemoteException;
	String getCli(int index) throws RemoteException;
	void clearCli() throws RemoteException;
}