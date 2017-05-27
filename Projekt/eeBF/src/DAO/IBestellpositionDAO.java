package DAO;

import Model.Bestellposition;
import Model.Bestellung;

public interface IBestellpositionDAO
{
	public void insertBestellposition( Bestellung order, Bestellposition item );

	public void updateBestellposition( Bestellung order, Bestellposition item );
}
