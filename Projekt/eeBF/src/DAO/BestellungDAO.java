package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Bestellung;

public class BestellungDAO {
	
	private String getBestellstatusString( Bestellung order )
	{
		String status = "";
		
		if( order.getCurrentState().isOrdered() )
			status = "bestellt";
		
		if( order.getCurrentState().isPaid() )
			status.concat(",bezahlt");
		
		if( order.getCurrentState().isSending() )
			status.concat(",liefernd");

		if( order.getCurrentState().isSent() )
			status.concat(",geliefert");
		
		if( order.getCurrentState().isComplete() )
			status.concat(",abgeschlossen");
		
		return status;
	}
	
	public void insertBestellung( Bestellung order ) throws ClassNotFoundException, SQLException
	{
		Connection conn = DBConnection.getConnection( DBConnection.connectionTypes.CUSTOMER );
		
		
		String query = "INSERT INTO bestellung (bestellstatus, aid) VALUES (?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, getBestellstatusString(order) );
		stmt.setInt(2, order.getCustomer().getId() );
		
		stmt.executeUpdate();
		
		ResultSet rs = stmt.getGeneratedKeys();
		
		int autoGenKey = -1;
		if( rs.next() )
			autoGenKey = rs.getInt(1);
		
		order.setId( autoGenKey );
		
		rs.close();
		
		// TODO Bestellpositionen
		
		
		stmt.close();
	}
	
	public void updateBestellung( Bestellung order, boolean updateTimestamp ) throws ClassNotFoundException, SQLException
	{
		Connection conn = DBConnection.getConnection( DBConnection.connectionTypes.CUSTOMER );
		
		String query;
		if( updateTimestamp )
			query = "UPDATE bestellung SET datum=current_timestamp(),bestellstatus=?,paypalTNr=?,aid=? WHERE oid=?";
		else
			query = "UPDATE bestellung SET bestellstatus=?,paypalTNr=?,aid=? WHERE oid=?";
		
		PreparedStatement stmt = conn.prepareStatement(query);		
		stmt.setString(1, getBestellstatusString(order) );
		stmt.setString(2, order.getPaypalTNr() );
		stmt.setInt(3, order.getCustomer().getId() );
		stmt.setInt(4, order.getId());
		
		stmt.executeUpdate();

		// TODO Bestellpositionen
		
		stmt.close();
	}
}
