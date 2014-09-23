package datenbanken;

import java.util.ArrayList;


public class MySQLYahooData {

	
	private String[] dbconfig;
	
	
	/**
	 * Onjekt zum einpflegen und auslesen von Yahoo Datensaetze in eine MySql Tabelle.
	 * 
	 * @param dbconfig
	 */
	public MySQLYahooData(String[] dbconfig)
	{
		this.dbconfig = dbconfig;
	}
	
	
	/**
	 * Schreibt eine neue Zeile in die Datentabelle.
	 * 
	 * @param row
	 */
	public void setData(String[] row)
	{
		DBMySQLConnection c = getConnection();
		
		String newRow = row[0];
		for(int i=1; i<row.length; i++)
			newRow += ";" + row[i];
		
		c.setData(dbconfig[0], newRow, "Date;Open;Close;High;Low");
		
		c.close();
	}
	
	
	/**
	 * Liesst alle Zeilen der Tabelle aus, welche zwischen den Zeitpunkten startDate und endDate liegen.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ArrayList<String> getData(String startDate, String endDate)
	{
		ArrayList<String> list = new ArrayList<String>();
		DBMySQLConnection c = getConnection();
		
		list = c.getData(table, key_column, key, "Date;Open;Close;High;Low");
		
		c.close();
		return list;
	}
	
	/**
	 * Erstellt eine Verknuepfung zur Datenbank.
	 * 
	 * @return
	 */
	private DBMySQLConnection getConnection()
	{
		return new DBMySQLConnection(dbconfig[0], dbconfig[1], dbconfig[2], dbconfig[3], dbconfig[4]);
	}
	
}
