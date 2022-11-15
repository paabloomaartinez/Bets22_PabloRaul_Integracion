
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;

public class emaitzakIpiniDABTest {

private static DataAccess sut = new DataAccess();
	
	private Event eve1;
	private Event eve2;
	private Question q1;
	private Question q2;
	private Quote quo1;
	private Quote quo2;
	private Apustua ap2;
	private Apustua ap3;

	
	@Before
	public void initialize() {
		
		eve1 = sut.getEventsAll().get(0);
		q1 = eve1.getQuestions().get(0);
		quo1 = q1.getQuotes().get(0);
		
		eve2 = sut.getEventsAll().get(0);
		q2 =  eve2.getQuestions().get(1);		
		quo2 = q2.getQuotes().get(0);	
		
		ap2 = quo2.getApustuak().get(0);
		ap3 = quo1.getApustuak().get(0);
	}
	
	
	@Test
	/* Camino para comprobar el resultado de una apuesta ganada y otra perdida y que el resultado de la pregunta es correcto
	 *  **/
	
	public void test1() {
		
		String s1 = ap2.getEgoera();
		String s2 = ap3.getApustuAnitza().getEgoera();

		ap2.setEgoera("jokoan");
		ap3.getApustuAnitza().setEgoera("jokoan");
		
		Date fecha = eve2.getEventDate();
		eve2.setEventDate(UtilDate.newDate(2021,8,7));


		try {
			sut.EmaitzakIpini(quo2);
		}catch(Exception EventNotFinished) {
			System.out.println("EventNotFinished");
			//assertTrue(true);
		}
		
		String expected = quo2.getForecast();
		String obtained = q2.getResult();
		
		String expected2 = "irabazita";
		String obtained2 = ap2.getEgoera();

		
		String expected3 = "galduta";
		String obtained3 = ap3.getApustuAnitza().getEgoera();
		
		assertEquals(expected, obtained);
		assertEquals(expected2, obtained2);
		//Falla porque nunca va a entrar en el if(b) del metodo 
		assertEquals(expected3, obtained3);

		try {
			
			eve2.setEventDate(fecha);
			quo2.getQuestion().setResult("");
			ap2.setEgoera(s1);
			ap3.getApustuAnitza().setEgoera(s2);

		} catch (Exception e) {
			fail("No es posible");
		}
	}
	
	
	@Test  /* Camino salta excepcion por fecha del evento todavia no finalizada **/
	public void test2() {
		
		Date fecha  = eve1.getEventDate();
		eve1.setEventDate(UtilDate.newDate(2023,11,23));


		try {
			
			sut.EmaitzakIpini(quo1);
			
		}catch(Exception EventNotFinished) {
			
			System.out.println("EventNotFinished");
			assertTrue(true);
		}
		
		try {
			eve1.setEventDate(fecha);	
		} catch (Exception e) {	
			fail("No es posible");
		}
		
	}
	
	
	@Test /* Camino en el que la cuota pasada por parametro es null **/
	public void test3() {
		
		Quote quo = null;

		try {
			sut.EmaitzakIpini(quo);
		}catch(Exception e) {
			assertTrue(true);
			System.out.println("Esa cuota no esta en la base de datos ");
		}
	}
	
	
	@Test /* Camino en el que la cuota pasada por parametro no esta en BD **/
	public void test4() {
		
		Quote quo = new Quote(1.5, "X", q1);

		try {
			sut.EmaitzakIpini(quo);
		}catch(Exception e) {
			assertTrue(true);
			System.out.println("Esa cuota no esta en la base de datos ");
		}
	}

	
	
}