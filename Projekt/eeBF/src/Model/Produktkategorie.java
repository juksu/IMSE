package Model;

public class Produktkategorie 
{
	int id;
	private String titel;
	private String beschreibung;

	public Produktkategorie (int id, String titel, String beschreibung)
	{
		setId(id);
		setTitel(titel);
		setBeschreibung(beschreibung);
	}

	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getTitel() 
	{
		return titel;
	}
	
	public void setTitel(String titel) 
	{
		this.titel = titel;
	}
	
	public String getBeschreibung()
	{
		return beschreibung;
	}
	
	public void setBeschreibung(String beschreibung)
	{
		this.beschreibung = beschreibung;
	}
}