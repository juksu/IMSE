package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Bestellposition;
import Model.Bestellung;

public class MysqlBestellpositionDAO implements IBestellpositionDAO
{

	@Override
	public void insertBestellposition( Bestellung order, Bestellposition item )
	{

		Connection conn = null;
		PreparedStatement stmt = null;

		try
		{
			conn = DBConnection.getConnection( DBConnection.connectionTypes.CUSTOMER );

			String query = "INSERT INTO bestellposition (oid, pid, menge, preisprostueck) VALUES (?, ?, ?, ?)";

			stmt = conn.prepareStatement( query );
			stmt.setInt( 1, order.getId() );
			stmt.setInt( 2, item.getProduct().getId() );
			stmt.setInt( 3, item.getQuantity() );
			stmt.setFloat( 4, item.getPricePerUnit() );

			stmt.executeUpdate();

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
	public void updateBestellposition( Bestellung order, Bestellposition item )
	{
		Connection conn = null;
		PreparedStatement stmt = null;

		try
		{
			conn = DBConnection.getConnection( DBConnection.connectionTypes.CUSTOMER );

			String query = "UPDATE bestellposition SET menge=?, preisprostueck=? WHERE oid=?,pid=?";

			stmt = conn.prepareStatement( query );
			stmt.setInt( 1, item.getQuantity() );
			stmt.setFloat( 2, item.getPricePerUnit() );
			stmt.setInt( 3, order.getId() );
			stmt.setInt( 4, item.getProduct().getId() );

			stmt.executeUpdate();

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
}
