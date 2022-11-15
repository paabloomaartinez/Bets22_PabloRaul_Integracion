import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;

public class gertaerakSortuDABTest {

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
		boolean expected = true;
		
		descripcion = "Casper Ruud-Alexander Zverev";
		date = UtilDate.newDate(today.get(Calendar.YEAR) + 1, (today.get(Calendar.MONTH)+1), 17);
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
		
		System.out.println("El evento (" + descripcion + ", " + date.toString() + ", " + s + ") creado correctamente");
		
		if (deleted)
			System.out.println("Evento borrado (" + descripcion + ", " + date.toString() + ", " + s + ")");
		else
			System.out.println("Evento no borrado (" + descripcion + ", " + date + ", " + s + ")");
	}
	
	@Test
	public void test2() {
		
		descripcion = null;
		date = UtilDate.newDate(today.get(Calendar.YEAR), (today.get(Calendar.MONTH)+1), 17);
		s = "Tennis";
		
		try {
			dt.gertaerakSortu(descripcion, date, s);
		}catch(Exception e) {
			assertTrue(true);
			System.out.println("La descripción es null");
		}

		for (Event ev : dt.getEvents(date)) {
			if (ev.getDescription().equals(descripcion)) {
				dt.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		System.out.println("La descripción es null (" + descripcion + ", " + date.toString() + ", " + s + ")");
		
		if (deleted)
			System.out.println("Evento borrado (" + descripcion + ", " + date.toString() + ", " + s + ")");
		else
			System.out.println("Evento no borrado (" + descripcion + ", " + date + ", " + s + ")");
				
	}
	
	@Test
	public void test3() {
		
		descripcion = "Casper Ruud-Alexander Zverev";
		date = UtilDate.newDate(today.get(Calendar.YEAR), (today.get(Calendar.MONTH)+1), 17);
		s = null;
		
		try {
			dt.gertaerakSortu(descripcion, date, s);
		}catch(Exception e) {
			assertTrue(true);
			System.out.println("El deporte es null");
		}

		for (Event ev : dt.getEvents(date)) {
			if (ev.getDescription().equals(descripcion)) {
				dt.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		System.out.println("La descripción es null (" + descripcion + ", " + date.toString() + ", " + s + ")");
		
		if (deleted)
			System.out.println("Evento borrado (" + descripcion + ", " + date.toString() + ", " + s + ")");
		else
			System.out.println("Evento no borrado (" + descripcion + ", " + date + ", " + s + ")");
				
	}
	
	@Test
	public void test4() {
		
		boolean esperado = false;
		
		descripcion = "Casper Ruud-Alexander Zverev";
		date = UtilDate.newDate(today.get(Calendar.YEAR), (today.get(Calendar.MONTH)+1), 17);
		s = "Balonmano";
		
		boolean res = dt.gertaerakSortu(descripcion, date, s);
		
		assertEquals(esperado, res);

		for (Event ev : dt.getEvents(date)) {
			if (ev.getDescription().equals(descripcion)) {
				dt.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		System.out.println("La descripción es null (" + descripcion + ", " + date.toString() + ", " + s + ")");
		
		if (deleted)
			System.out.println("Evento borrado (" + descripcion + ", " + date.toString() + ", " + s + ")");
		else
			System.out.println("Evento no borrado (" + descripcion + ", " + date + ", " + s + ")");
				
	}
	
	@Test
	public void test5() {
		
		descripcion = "Casper Ruud/Alexander Zverev";
		date = UtilDate.newDate(today.get(Calendar.YEAR), (today.get(Calendar.MONTH)+1), 17);
		s = "Tennis";
		
		try {
			dt.gertaerakSortu(descripcion, date, s);
		}catch(Exception e) {
			assertTrue(true);
			System.out.println("La descripción no esta separada por un guión.");
		}

		for (Event ev : dt.getEvents(date)) {
			if (ev.getDescription().equals(descripcion)) {
				dt.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		System.out.println("La descripción es null (" + descripcion + ", " + date.toString() + ", " + s + ")");
		
		if (deleted)
			System.out.println("Evento borrado (" + descripcion + ", " + date.toString() + ", " + s + ")");
		else
			System.out.println("Evento no borrado (" + descripcion + ", " + date + ", " + s + ")");
				
	}
}
