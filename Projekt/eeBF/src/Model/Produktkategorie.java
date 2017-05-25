package Model;

public class Produktkategorie
{
	int id;
	private String titel;
	private String beschreibung;
	private Produktkategorie oberkategorie;		// TODO if null then root category

	public Produktkategorie (int id, String titel, String beschreibung, Produktkategorie oberkategorie)
	{
		setId(id);
		setTitel(titel);
		setBeschreibung(beschreibung);
		this.oberkategorie = oberkategorie;
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

	public Produktkategorie getOberkategorie() {
		return oberkategorie;
	}

	public void setOberkategorie(Produktkategorie oberkategorie) {
		this.oberkategorie = oberkategorie;
	}
}