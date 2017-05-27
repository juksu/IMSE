package Model;

import java.util.Calendar;

public class Kunde extends User 
{
	private String nachname;
	private String vorname;
	private String land;
	private int plz;
	private String ort;
	private String strasse;
	private int hausnummer;

	public Kunde(int id, String email, String passwort, String nachname, String vorname, String land, int plz, String ort, String strasse, 
			int hausnummer, Calendar createdate) 
	{
		super(id, email, passwort, createdate);
		setNachname(nachname);
		setVorname(vorname);
		setLand(land);
		setPlz(plz);
		setOrt(ort);
		setStrasse(strasse);
		setHausnummer(hausnummer);
	}

	public String getNachname() 
	{
		return nachname;
	}
	
	public void setNachname(String nachname) 
	{
		this.nachname = nachname;
	}
	
	public String getVorname()
	{
		return vorname;
	}
	
	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}
	
	public String getStrasse() 
	{
		return strasse;
	}
	
	public void setStrasse(String strasse)
	{
		this.strasse = strasse;
	}
	
	public int getPlz()
	{
		return plz;
	}
	
	public void setPlz(int plz) 
	{
		this.plz = plz;
	}
	
	public String getOrt() {
		return ort;
	}
	
	public void setOrt(String ort) 
	{
		this.ort = ort;
	}
	
	public int getHausnummer()
	{
		return hausnummer;
	}
	
	public void setHausnummer(int hausnummer) 
	{
		this.hausnummer = hausnummer;
	}
	
	public String getLand()
	{
		return land;
	}
	
	public void setLand(String land)
	{
		this.land = land;
	}
}