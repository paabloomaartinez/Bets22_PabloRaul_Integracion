

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;

public class gertaerakSortuDAWTest {

	private DataAccess dt = new DataAccess();
	Calendar today = Calendar.getInstance();
	private String descripcion;
	private Date date;
	private String s;
	private boolean deleted;
	
	@Before
	public void initialize() {
		descripcion = "";
		date = null;
		s = "";
		deleted = false;
	}
	
	@Test
	public void test1() {
		
		boolean expected = false;
		
		descripcion = "Real Madrid-Barcelona";
		date = UtilDate.newDate(today.get(Calendar.YEAR), (today.get(Calendar.MONTH)+1), 17);
		s = "Balonmano";
		
		boolean result = dt.gertaerakSortu(descripcion, date, s);
		
		assertEquals(expected, result);
				
		for (Event ev : dt.getEvents(date)) {
			if (ev.getDescription().equals(descripcion)) {
				dt.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		System.out.println("sport " + s +" no es un Sport");
		
		if (deleted)
			System.out.println("Evento borrado (" + descripcion + ", " + date + ", " + s + ")");
		else
			System.out.println("Evento no borrado (" + descripcion + ", " + date + ", " + s + ")");
				
	}
	
	@Test
	public void test2() {
		
		boolean expected = true;
		
		descripcion = "Casper Ruud-Alexander Zverev";
		date = UtilDate.newDate(today.get(Calendar.YEAR) + 1, (today.get(Calendar.MONTH)+1), 2);
		s = "Tennis";
		
		boolean result = dt.gertaerakSortu(descripcion, date, s);
		
		assertEquals(expected, result);
				
		for (Event ev : dt.getEvents(date)) {
			if (ev.getDescription().equals(descripcion)) {
				dt.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		if (deleted)
			System.out.println("Evento borrado (" + descripcion + ", " + date + ", " + s + ")");
		else
			System.out.println("Evento no borrado (" + descripcion + ", " + date + ", " + s + ")");
			
	}
	
	@Test
	public void test3() {
		
		boolean expected = true;
		
		descripcion = "Casper Ruud-Alexander Zverev";
		date = UtilDate.newDate(today.get(Calendar.YEAR), (today.get(Calendar.MONTH)+1), 17);
		s = "Tennis";

		boolean result = dt.gertaerakSortu(descripcion, date, s);
		
		assertEquals(expected, result);
		
		for (Event ev : dt.getEvents(date)) {
			if (ev.getDescription().equals(descripcion)) {
				dt.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		if (deleted)
			System.out.println("Evento borrado (" + descripcion + ", " + date + ", " + s + ")");
		else
			System.out.println("Evento no borrado (" + descripcion + ", " + date + ", " + s + ")");
			
	}
	@Test
	public void test4() {
		
		boolean expected = false;
		
		descripcion = "Djokovic-Federer";
		date = UtilDate.newDate(today.get(Calendar.YEAR), (today.get(Calendar.MONTH)+1), 17);
		s = "Tennis";
		
		dt.gertaerakSortu(descripcion, date, s);
		boolean result = dt.gertaerakSortu(descripcion, date, s);
		
		assertEquals(expected, result);
				
		for (Event ev : dt.getEvents(date)) {
			if (ev.getDescription().equals(descripcion)) {
				dt.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		if (deleted)
			System.out.println("Evento borrado (" + descripcion + ", " + date + ", " + s + ")");
		else
			System.out.println("Evento no borrado (" + descripcion + ", " + date + ", " + s + ")");
			
	}

}