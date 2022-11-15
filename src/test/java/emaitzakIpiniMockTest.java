import businessLogic.BLFacade;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Team;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;



public class emaitzakIpiniMockTest {
	
	@Mock
	DataAccess mock;
	@InjectMocks
	BLFacade sut;

	 /* Salta excepcion por fecha del evento todavia no finalizada **/
	@Test
	public void test1() {
		
		try {
			
			Team team1= new Team("Atletico");
			Team team2= new Team("Athletic");
			Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(2023,1,17), team1, team2);
			Question q1 = new Question("¿Quién ganará el partido?",1, ev1);
			Quote quote1 = q1.addQuote(1.3, "1", q1); 	
			Registered reg1 =new Registered("registered", "123", 1234);
			ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
			Apustua ap1 = new Apustua(apA1, quote1);
			apA1.addApustua(ap1);
			
			try {
				
				sut.EmaitzakIpini(quote1);
			}catch(Exception EventNotFinished) {
				System.out.println("EventNotFinished");
				assertTrue(true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test2() {
		ArgumentCaptor<Quote> cuota1 = ArgumentCaptor.forClass(Quote.class);
		
		try {
					
			Team team1= new Team("Atletico");
			Team team2= new Team("Athletic");
			Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(2021,1,17), team1, team2);
			Question q1 = ev1.addQuestion("¿Quién ganará el partido?",1);
			Quote quote1 = q1.addQuote(1.3, "1", q1);
			
			try {
				
				sut.EmaitzakIpini(quote1);
				Mockito.verify(mock, Mockito.times(1)).EmaitzakIpini(cuota1.capture());
				assertEquals(quote1, cuota1.getValue());

			}catch(Exception EventNotFinished) {
				System.out.println("EventNotFinished");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}