package storageLayer;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gestioneVendite.Annuncio;
import gestioneVendite.DettagliAnnuncio;

public class DatabaseGV {
	
	private static String queryAddAnnuncio;
	private static String queryDettagliAnnuncio;
	
	/**
	 * <b>Permette l'inserimento di un annuncio nel database</b>
	 * @param annuncio
	 * @param dett
	 * @return
	 * @throws SQLException
	 */
	public static boolean addAnnuncio(Annuncio annuncio, DettagliAnnuncio dett) throws SQLException {
		Connection connection = null;
		PreparedStatement psAddAnnuncio= null;
		PreparedStatement psAddDettagliAnnuncio = null;
		int lastID=0;
		try {
			connection = Database.getConnection();
			psAddAnnuncio = connection.prepareStatement(queryAddAnnuncio, Statement.RETURN_GENERATED_KEYS);
			
			psAddAnnuncio.setString(1, annuncio.getTitolo());
			psAddAnnuncio.setString(2, annuncio.getAutore());
			psAddAnnuncio.setString(3, annuncio.getCorso());
			psAddAnnuncio.setString(4, annuncio.getProprietario());
			psAddAnnuncio.setString(5, annuncio.getCondizioneLibro().name());
			psAddAnnuncio.setDouble(6, annuncio.getPrezzo());
			System.out.println(psAddAnnuncio.toString());
			psAddAnnuncio.executeUpdate();
			
			ResultSet rs =psAddAnnuncio.getGeneratedKeys();
			if(rs.next()){
				lastID = rs.getInt(1);
				System.out.println("Test: "+rs.getInt(1));
			}
			
			connection.commit();
			
			
			psAddDettagliAnnuncio = connection.prepareStatement(queryDettagliAnnuncio); 
			psAddDettagliAnnuncio.setInt(1, lastID);
			psAddDettagliAnnuncio.setString(2, dett.getEditore());
			psAddDettagliAnnuncio.setInt(3, dett.getAnno());
			psAddDettagliAnnuncio.setString(4, dett.getDescrizione());
			psAddDettagliAnnuncio.setDate(5, (Date) dett.getData());
			psAddDettagliAnnuncio.setString(6, dett.getFoto());
			System.out.println(psAddDettagliAnnuncio.toString());
			
			psAddDettagliAnnuncio.executeUpdate();
			
			connection.commit();
			
		} finally {
			try {
				if(psAddAnnuncio != null)
					psAddAnnuncio.close();
				if(psAddDettagliAnnuncio !=null)
					psAddDettagliAnnuncio.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			 finally {
				Database.releaseConnection(connection);
			}
		}
		return true;
	}
	
	
	static {
		queryAddAnnuncio = "INSERT INTO redteam.annuncio (Titolo, Autore, Corso, Proprietario, CondizioneLibro,Prezzo) VALUES (?,?,?,?,?,?)";
		queryDettagliAnnuncio ="INSERT INTO redteam.dettagliannuncio (id, Editore, Anno, Descrizione, Data, Foto) VALUES (?,?,?,?,?,?)";
	}
}
