package storageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import gestioneUtente.Utente;



public class DatabaseGU {
	/**
	 * <b>Registra un utente nel database</b>
	 * @param utente
	 * @return {@code true} se la registrazione e' ok, {@code false}  altrimenti.
	 * @throws SQLException
	  * @author Domenico Tropeano
	 */
	public synchronized static boolean AddUser(Utente utente) throws SQLException {

		Connection connection = null;
		PreparedStatement psAddUtente = null;

		try {
			connection = Database.getConnection();
			psAddUtente = connection.prepareStatement(queryAddUtente);

			psAddUtente.setString(1, utente.getNome());
			psAddUtente.setString(2, utente.getCognome());
			psAddUtente.setString(3, utente.getEmail());
			psAddUtente.setString(4, utente.getPassword());
			psAddUtente.setBoolean(5, utente.isStatus());
			psAddUtente.setBoolean(6, utente.isPrivilegioAdmin());
			System.out.println(psAddUtente.toString());
			psAddUtente.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (psAddUtente != null)
					psAddUtente.close();
			} finally {
				Database.releaseConnection(connection);
			}
		}

		return true;
	}
	/**
	 * <b>Elimina un utente dal database</b>
	 * @param email
	 * @return {@code true}  se l'eliminazione � ok, {@code false}  altrimenti.
	 * @throws SQLException
	  * @author Domenico Tropeano
	 */
	public synchronized static boolean DeleteUser(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryEliminaAccount);
			preparedStatement.setString(1, email);

			result = preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				Database.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	/**
	 * <b>Cambia lo stato di un utente</b>
	 * @param email indica l' email dell' utente a cui cambiare stato
	 * @param newStatus indica il nuovo stato assegnato all' utente
	 * @return {@code true}  se l'eliminazione � ok, {@code false}  altrimenti.
	 * @throws SQLException
	  * @author Antonio Corsuto
	 */
	public synchronized static boolean ChangeStatus(String email, boolean newStatus) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryCambiaStatus);
			preparedStatement.setBoolean(1,newStatus );
			preparedStatement.setString(2, email);

			result = preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				Database.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	/**
	 * <b>Cambia i privilegi di amministratore di un utente</b>
	 * @param email indica l' email dell' utente a cui cambiare stato
	 * @param newStatus indica il nuovo stato assegnato all' utente
	 * @return {@code true}  se l'eliminazione � ok, {@code false}  altrimenti.
	 * @throws SQLException
	  * @author Antonio Corsuto
	 */
	public synchronized static boolean ChangePrivilegiAdmin(String email, boolean newPrivilegi) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
 
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryCambiaPrivilegiAdmin);
			preparedStatement.setBoolean(1,newPrivilegi);
			preparedStatement.setString(2, email);

			result = preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				Database.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	/**
	 * Restituisce ,se esiste, un oggetto Utente data una email
	 * @param email
	 * @return {@code null}  se l'utene non esiste, {@code Oggetto Utente }  altrimenti.
	 * @throws SQLException
	  * @author Antonio Corsuto
	 */


	public synchronized static Utente GetUtenteByID(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Utente utente=new Utente();


		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetUtente);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();			
			connection.commit();

			while (rs.next()) {
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setEmail(rs.getString("email"));
				utente.setPassword(rs.getString("password"));
				utente.setStatus(rs.getBoolean("status"));
				utente.setPrivilegioAdmin(rs.getBoolean("privilegioAdmin"));				
			}
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				Database.releaseConnection(connection);
			}
		}
		if(utente.getEmail()==null)
			return null;
		else  		
			return utente ;
	}


	/**
	 * Restituisce tutti gli Utenti del database
	 * @return {@code null}  se non esistono utenti, {@code ArrayList Utenti }  altrimenti.
	 * @throws SQLException
	 * @author Antonio Corsuto
	 */
	public synchronized static ArrayList<Utente> DoRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Utente> utenti = new ArrayList<Utente>();

		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetAllUtenti);

			ResultSet rs = preparedStatement.executeQuery();
			connection.commit();

			while (rs.next()) {
				Utente utente = new Utente();	

				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setEmail(rs.getString("email"));
				utente.setPassword(rs.getString("password"));
				utente.setStatus(rs.getBoolean("status"));
				utente.setPrivilegioAdmin(rs.getBoolean("privilegioAdmin"));	

				utenti.add(utente);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				Database.releaseConnection(connection);
			}
		}

		if(utenti.size()<1)
			return null;
		else  		
			return utenti ;
	}



	private static String queryAddUtente;
	private static String queryEliminaAccount;
	private static String queryCambiaStatus;
	private static String queryGetUtente;
	private static String queryCambiaPrivilegiAdmin;
	private static String queryGetAllUtenti;


	static {
		queryAddUtente = "INSERT INTO `redteam`.`utente` (`Nome`, `Cognome`, `Email`, `Password`, `Status`, `PrivilegioAdmin`) VALUES (?,?,?,?,?,?);";
		queryEliminaAccount = "DELETE FROM `redteam`.`utente` WHERE `email`=?;";
		queryCambiaStatus = "UPDATE `redteam`.`utente` SET `Status`=? WHERE `Email`=?;";
		queryGetUtente = "SELECT * From redteam.utente WHERE utente.email=?;";
		queryCambiaPrivilegiAdmin = "UPDATE `redteam`.`utente` SET `privilegioAdmin`=? WHERE `Email`=?;";
		queryGetAllUtenti = "SELECT * From redteam.utente";
	}
}
