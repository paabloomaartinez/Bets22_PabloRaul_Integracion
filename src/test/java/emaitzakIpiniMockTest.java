
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Team;
import exceptions.EventNotFinished;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


public class emaitzakIpiniMockTest {
	
	DataAccess mock = Mockito.mock(DataAccess.class);
	BLFacadeImplementation sut = new BLFacadeImplementation(mock);
	
	Team team1= new Team("Atletico");
	Team team2= new Team("Athletic");
	Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(2022,1,17), team1, team2);
	Question q1 = new Question("¿Quién ganará el partido?",1, ev1);
	Quote quote1 = q1.addQuote(1.3, "1", q1); 

	@Test
	public void test1() {
		
		Registered reg1 =new Registered("registered", "123", 1234);
		ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
		Apustua ap1 = new Apustua(apA1, quote1);
		apA1.addApustua(ap1);
	
		ArgumentCaptor<Quote> cuota1 = ArgumentCaptor.forClass(Quote.class);
		
		try {
			Mockito.verify(mock, Mockito.times(1)).EmaitzakIpini(cuota1.capture());
		} catch (EventNotFinished e) {

			e.printStackTrace();
		}
		assertEquals(quote1, cuota1.getValue());
		
	}
	
	
	@Test
    public void test2() {

            try {

                Mockito.doReturn(new EventNotFinished()).when(mock).EmaitzakIpini(quote1);
                sut.EmaitzakIpini(quote1);

            } catch (EventNotFinished e1) {
                assertTrue(true);
                System.out.println("EventNotFinished");

            }

            ArgumentCaptor<Quote> cuota2 = ArgumentCaptor.forClass(Quote.class);
            try {
                Mockito.verify(mock, Mockito.times(1)).EmaitzakIpini(cuota2.capture());
            } catch (EventNotFinished e) {
                fail("No es posible");
            }
            assertEquals(quote1, cuota2.getValue());
    }
	
}