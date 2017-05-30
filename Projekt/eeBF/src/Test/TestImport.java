package Test;

// v1.0.1

import java.sql.*;
import java.util.Random;

public class TestImport {

  public static void main(String args[]) {

    try {
      Class.forName("com.mysql.jdbc.Driver");
      String database = "jdbc:mysql://www.eeBF.at:3306/eebf?useSSL=false";
      String user = "eeBF_Admin";
      String pass = "Tombstone";

      // establish connection to database 
      Connection con = DriverManager.getConnection(database, user, pass);
      Statement stmt = con.createStatement();
      
			System.out.println("Datenschaufel: " + user);
			// random test data 
			String nachnamen[] = new String[21];
			nachnamen[0] = "Hinterberger";
			nachnamen[1] = "Apsberger";
			nachnamen[2] = "Oberberger";
			nachnamen[3] = "Unterberger";
			nachnamen[4] = "Berger";
			nachnamen[5] = "Schoendorfer";
			nachnamen[6] = "Schirchdorfer";
			nachnamen[7] = "Dorfer";
			nachnamen[8] = "Bitter";
			nachnamen[9] = "Suess";
			nachnamen[10] = "Herzlich";
			nachnamen[11] = "Hammer";
			nachnamen[12] = "Ueberhammer";
			nachnamen[13] = "Dau";
			nachnamen[14] = "Berger";
			nachnamen[15] = "Froehlich";
			nachnamen[16] = "Schwarz";
			nachnamen[17] = "Schattinger";
			nachnamen[18] = "Schartner";
			nachnamen[19] = "Engel";
			nachnamen[20] = "Stimmung";
			System.out.println("Kontrolle NN: " + nachnamen[20]);
		
			String vornamen[] = new String[21];
			vornamen[0] = "Peter";
			vornamen[1] = "Anna";
			vornamen[2] = "Maria";
			vornamen[3] = "Karl";
			vornamen[4] = "Gustav";
			vornamen[5] = "Erika";
			vornamen[6] = "Andrea";
			vornamen[7] = "Erich";
			vornamen[8] = "Andreas";
			vornamen[9] = "Gabriel";
			vornamen[10] = "Florian";
			vornamen[11] = "Harald";
			vornamen[12] = "Maria";
			vornamen[13] = "Joachim";
			vornamen[14] = "Isabella";
			vornamen[15] = "Denis";
			vornamen[16] = "Birgit";
			vornamen[17] = "Christian";
			vornamen[18] = "Robert";
			vornamen[19] = "Roman";
			vornamen[20] = "Lisa";
			System.out.println("Kontrolle VN: " + vornamen[20]);

			String postleitzahlen[] = new String[11];
			postleitzahlen[0] = "2753";
			postleitzahlen[1] = "1010";
			postleitzahlen[2] = "3040";
			postleitzahlen[3] = "3011";
			postleitzahlen[4] = "2353";
			postleitzahlen[5] = "3021";
			postleitzahlen[6] = "3013";
			postleitzahlen[7] = "3034";
			postleitzahlen[8] = "2754";
			postleitzahlen[9] = "2752";
			postleitzahlen[10] = "2751";
			System.out.println("Kontrolle PLZ: " + postleitzahlen[10]);

			String ortschaften[] = new String[11];
			ortschaften[0] = "Piesting";
			ortschaften[1] = "Wien";
			ortschaften[2] = "Hofstatt";
			ortschaften[3] = "Purkersdorf";
			ortschaften[4] = "Guntramsdorf";
			ortschaften[5] = "Pressbaum";
			ortschaften[6] = "Tullnerbach";
			ortschaften[7] = "Maria-Anzbach";
			ortschaften[8] = "Dreistetten";
			ortschaften[9] = "Woellersdorf";
			ortschaften[10] = "Steinabrueckl";
			System.out.println("Kontrolle Ort: " + ortschaften[10]);

			String strassen[] = new String[11];
			strassen[0] = "Siegfried-Ludwig-Str.";
			strassen[1] = "Alserstr.";
			strassen[2] = "Feldgasse";
			strassen[3] = "Hauptstr.";
			strassen[4] = "Bahnhofszeile";
			strassen[5] = "Burgundergasse";
			strassen[6] = "Traminereck";
			strassen[7] = "Josefsplatz";
			strassen[8] = "Schottergasse";
			strassen[9] = "Waldgasse";
			strassen[10] = "Muehlgasse";
			System.out.println("Kontrolle Str.: " + strassen[10]);

			String pw[] = new String[11];
			pw[0] = "j.kwak";
			pw[1] = "12345678";
			pw[2] = "super";
			pw[3] = "kauf";
			pw[4] = "rausch";
			pw[5] = "dau";
			pw[6] = "0815";
			pw[7] = "4711";
			pw[8] = "GehBitte";
			pw[9] = "password";
			pw[10] = "felix";

			String daten[] = new String[11];
			daten[0] = "2011-01-02";
			daten[1] = "2011-02-27";
			daten[2] = "2011-03-16";
			daten[3] = "2011-04-01";
			daten[4] = "2011-05-30";
			daten[5] = "2011-08-05";
			daten[6] = "2011-09-11";
			daten[7] = "2011-10-27";
			daten[8] = "2011-11-11";
			daten[9] = "2011-12-08";
			daten[10] = "2012-01-06";
			System.out.println("Kontrolle Datum: " + daten[10]);

			String stati[] = new String[7];
			stati[0] = "offen";
			stati[1] = "bezahlt";
			stati[2] = "in Zustellung";
			stati[3] = "geliefert";
			stati[4] = "abgeschlossen";
			System.out.println("Kontrolle Status: " + stati[6]);

			Random r = new Random();
		
			String table1 = "benutzerkonto";
			String table2 = "kunde";
			String table3 = "bestellung";
			int id = 0;

			for(int i = 0; i < 10; i++){
				//Datum						
				int indexDaten = r.nextInt(10)+ 1;
				String datum = daten[indexDaten];

				//Status
				int indexStatus = r.nextInt(4) + 1;
				String status = stati[indexStatus];
								
				//Nachname						
				int indexNachname = r.nextInt(20)+ 1;
				String nachname = nachnamen[indexNachname];

				//Vorname
				int indexVorname = r.nextInt(20) + 1;
				String vorname = vornamen[indexVorname];

				//Land
				String land = "Austria";
		
				//PLZ
				int indexPLZ = r.nextInt(10) + 1;
				String plz = postleitzahlen[indexPLZ];	
					
				//Ort
				int indexOrt = indexPLZ;
				String ort = ortschaften[indexOrt];
				
				//Strasse
				int indexStrasse = r.nextInt(10) + 1;
				String strasse = strassen[indexStrasse];

				//HausNr		  		  
				int hausnr = r.nextInt(50) + 1;
				
				//Passwort
				int indexPasswort = r.nextInt(10) + 1;
				String passwort = pw[indexPasswort];	
				
				//email
				String email = vorname + "." + nachname + "@" + "eeBF.at";

				// DB insert
				try {
					String insertSql1 = "INSERT INTO "+table1+" SET Passwort='"+passwort+"', email='"+email+"'";
					System.out.println("Datensatz " + i + ": " + insertSql1);
					stmt.executeUpdate(insertSql1);
				} catch (Exception e) {
					System.err.println("Fehler: " + table1 + e.getMessage());
				}

				try {
					ResultSet rs1 = stmt.executeQuery("SELECT aid FROM "+table1+" WHERE email='"+email+"'");
					System.out.println("Query " + i + ": " + rs1);
				    if (rs1.next()) {
				   	  id = rs1.getInt(1);
				   	  System.out.println("aid: " + id);
				   	  rs1.close();
				    }	
					String insertSql2 = "INSERT INTO "+table2+" SET Nachname='"+nachname+"', Vorname='"+vorname+"', Land='"+land+"', PLZ='"+plz+"', Ort='"+ort+"', Strasse='"+strasse+"', HausNr='"+hausnr+"', aid='"+id+"'";
					System.out.println("Datensatz " + i + ": " + insertSql2);
					stmt.executeUpdate(insertSql2);
				
				} catch (Exception e) {
					System.err.println("Fehler: " + table2 + e.getMessage());
				}
				try {
					String insertSql3 = "INSERT INTO "+table3+" SET Datum='"+datum+"', Status='"+status+"', aid='"+id+"'";
					System.out.println("Datensatz " + i + ": " + insertSql3);
					stmt.executeUpdate(insertSql3);
				
				} catch (Exception e) {
					System.err.println("Fehler: " + table3 + e.getMessage());
				}
			}

		    // check number of DataSets
		    ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM " + table1);
		    if (rs1.next()) {
		    	int count1 = rs1.getInt(1);
		    	System.out.println("Number of datasets: " + count1);
		    	rs1.close();
		    }
		    ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM " + table2);
		    	if (rs2.next()) {
		    	int count2 = rs2.getInt(1);
		    	System.out.println("Number of datasets: " + count2);
		    	rs2.close();
		    }
		    ResultSet rs3 = stmt.executeQuery("SELECT COUNT(*) FROM " + table3);
		    	if (rs3.next()) {
		    	int count = rs3.getInt(1);
		    	System.out.println("Number of datasets: " + count);
		    	rs3.close(); 
		    }
		    	
		// clean up connections
		stmt.close();
		con.close();
    	} catch (Exception e) {
    		System.err.println(e.getMessage());
   		}
  	}
}	  

