package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Bestellung;

public class MysqlBestellungDAO implements IBestellungDAO
{

	private String getBestellstatusString( Bestellung order )
	{
		String status = "";

		if( order.isOrderStateOrdered() )
			status = "bestellt";

		if( order.isOrderStatePaid() )
			status.concat( ",bezahlt" );

		if( order.isOrderStateSending() )
			status.concat( ",liefernd" );

		if( order.isOrderStateSent() )
			status.concat( ",geliefert" );

		if( order.isOrderStateComplete() )
			status.concat( ",abgeschlossen" );

		return status;
	}

	@Override
	public void insertBestellung( Bestellung order )
	{
		Connection conn = null;
		PreparedStatement stmt = null;

		try
		{
			conn = DBConnection.getConnection( DBConnection.connectionTypes.CUSTOMER );

			String query = "INSERT INTO bestellung (bestellstatus, aid) VALUES (?, ?)";

			stmt = conn.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
			stmt.setString( 1, getBestellstatusString( order ) );
			stmt.setInt( 2, order.getCustomer().getId() );

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			int autoGenKey = -1;
			if( rs.next() )
				autoGenKey = rs.getInt( 1 );

			order.setId( autoGenKey );

		} catch( SQLException | ClassNotFoundException e )
		{
			System.err.println( e.getMessage() );
			e.printStackTrace();
		} finally
		{
			try
			{
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			} catch( SQLException e )
			{
				System.err.println( e.getMessage() );
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateBestellung( Bestellung order, boolean updateTimestamp )
	{
		Connection conn = null;
		PreparedStatement stmt = null;

		try
		{
			conn = DBConnection.getConnection( DBConnection.connectionTypes.CUSTOMER );

			String query;
			if( updateTimestamp )
				query = "UPDATE bestellung SET datum=current_timestamp(),bestellstatus=?,paypalTNr=?,aid=? WHERE oid=?";
			else
				query = "UPDATE bestellung SET bestellstatus=?,paypalTNr=?,aid=? WHERE oid=?";

			stmt = conn.prepareStatement( query );
			stmt.setString( 1, getBestellstatusString( order ) );
			stmt.setString( 2, order.getPaypalTNr() );
			stmt.setInt( 3, order.getCustomer().getId() );
			stmt.setInt( 4, order.getId() );

			stmt.executeUpdate();

		} catch( ClassNotFoundException | SQLException e )
		{
			System.err.println( e.getMessage() );
			e.printStackTrace();
		} finally
		{
			try
			{
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			} catch( SQLException e )
			{
				System.err.println( e.getMessage() );
				e.printStackTrace();
			}
		}
	}
}
