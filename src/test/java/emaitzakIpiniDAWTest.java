
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;


public class emaitzakIpiniDAWTest {
	private static DataAccess sut = new DataAccess();
	
	private Event eve1;
	private Event eve2;
	private Event eve3;
	private Question q1;
	private Question q2;
	private Question q3;
	private Quote quo1;
	private Quote quo2;
	private Quote quo3;
	private Apustua ap2;
	private Apustua ap3;
	private Apustua ap4;
	private Apustua ap5;


	
	@Before
	public void initialize() {
		
		eve1 = sut.getEventsAll().get(0);
		q1 = eve1.getQuestions().get(0);
		quo1 = q1.getQuotes().get(0);
		
		eve2 = sut.getEventsAll().get(0);
		q2 =  eve2.getQuestions().get(1);		
		quo2 = q2.getQuotes().get(0);
		ap2 = quo2.getApustuak().get(0);
		//ap3 = quo1.getApustuak().get(0);
		
		eve3 = sut.getEventsAll().get(21);
		q3 =  eve3.getQuestions().get(0);		
		quo3 = q3.getQuotes().get(0);
		ap4 = quo3.getApustuak().get(0);
	
		


	}
	
	@Test /*Camino salta excepcion por fecha del evento todavia no finalizada **/
	
	public void test1() {
		Date fecha = eve1.getEventDate();
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

	@Test
	/* Camino para comprobar el resultado de la apuesta ganada y el resultado de la pregunta se ponen correctamente 
	 * (en el if(b) no va a entrar nunca debido a que llama al metodo galdutaMarcatu con el parametro quo) **/
	
	public void test2() {
		
		String s1 = ap2.getEgoera();
		//String s2 = ap3.getApustuAnitza().getEgoera();

		ap2.setEgoera("jokoan");
		//ap3.setEgoera("jokoan");
		
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

		
		//String expected4 = "galduta";
		//String obtained4 = ap3.getApustuAnitza().getEgoera();
		
		assertEquals(expected, obtained);
		assertEquals(expected2, obtained2);
		
		//assertEquals(expected4, obtained4);

		try {
			
			eve2.setEventDate(fecha);
			quo2.getQuestion().setResult("");
			ap2.setEgoera(s1);
			
			//ap3.getApustuAnitza().setEgoera(s2);

		} catch (Exception e) {
			fail("No es posible");
		}
	}
	
	@Test /* Camino para comprobar que la ApustuAnitza se gana **/
	
	public void test4() {
		System.out.println(ap4.getApustuAnitza().getApustuak().size());

		String s1 = ap4.getEgoera();
		String s2 = ap4.getApustuAnitza().getEgoera();
		
		ap4.setEgoera("jokoan");
		ap4.getApustuAnitza().setEgoera("jokoan");
	
		
		Date fecha = eve3.getEventDate();
		eve3.setEventDate(UtilDate.newDate(2021,8,7));


		try {
			sut.EmaitzakIpini(quo3);
		}catch(Exception EventNotFinished) {
			System.out.println("EventNotFinished");
			//assertTrue(true);
		}
		
		String expected = quo3.getForecast();
		String obtained = q3.getResult();
		
		String expected2 = "irabazita";
		String obtained2 = ap4.getEgoera();
		
		String expected3 = "irabazita";
		String obtained3 = ap4.getApustuAnitza().getEgoera();
		

		assertEquals(expected, obtained);
		assertEquals(expected2, obtained2);
		assertEquals(expected3, obtained3);


		try {
			
			eve3.setEventDate(fecha);
			quo3.getQuestion().setResult("");
			ap4.setEgoera(s1);
			ap4.getApustuAnitza().setEgoera(s2);

		} catch (Exception e) {
			fail("No es posible");
		}
	}

}