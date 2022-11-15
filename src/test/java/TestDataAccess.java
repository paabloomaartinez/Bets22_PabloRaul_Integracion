

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.Transaction;

public class TestDataAccess {
	protected EntityManager db;
	protected EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public TestDataAccess() {

		System.out.println("Creating TestDataAccess instance");

		open();

	}

	public void open() {

		System.out.println("Opening TestDataAccess instance ");

		String fileName = c.getDbFilename();

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else
			return false;
	}
	
	public boolean removeSport(Sport sport) {
		System.out.println(">> DataAccessTest: removeEvent");
		Sport e = db.find(Sport.class, sport.getIzena());
		if (e != null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else
			return false;
	}

	public boolean removeRegistered(Registered r) {
		System.out.println(">> DataAccessTest: removeRegistered");
		Registered e = db.find(Registered.class, r.getUsername() );
		if (e != null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else
			return false;
	}

	public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev = null;
		db.getTransaction().begin();
		try {
			// ev=new Event(desc,d);
			ev.addQuestion(question, qty);
			db.persist(ev);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}

	public boolean existQuestion(Event ev, Question q) {
		System.out.println(">> DataAccessTest: existQuestion");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			return e.DoesQuestionExists(q.getQuestion());
		} else
			return false;

	}
	
	public void addSport1(String sport) {
		Sport kirola = new Sport(sport);
		db.getTransaction().begin();
		db.persist(kirola);
		db.getTransaction().commit();
	}

	public Sport addSport(String sport, Event ev) {
		Sport kirola = new Sport(sport);
		db.getTransaction().begin();
		db.persist(kirola);
		Event e = db.find(Event.class, ev.getEventNumber());
		e.setSport(kirola);
		db.getTransaction().commit();
		return kirola;

	}

	public Registered addRegistered(Registered r) {
		db.getTransaction().begin();
		db.persist(r);
		db.getTransaction().commit();
		return db.find(Registered.class, r.getUsername());
	}

	public void addTeam(String team) {
		Team taldea = new Team(team);
		db.getTransaction().begin();
		db.persist(taldea);
		db.getTransaction().commit();
	}
	public void addQuote(Quote quote) {
		db.getTransaction().begin();
		db.persist(quote);
		db.getTransaction().commit();
	}
	public void addQuestion(Question question) {
		db.getTransaction().begin();
		db.persist(question);
		db.getTransaction().commit();
	}
	public void addEvent(Event event) {
		db.getTransaction().begin();
		db.persist(event);
		db.getTransaction().commit();
	}
	public void addApustuAnitza(ApustuAnitza aa) {
		db.getTransaction().begin();
		db.persist(aa);
		db.getTransaction().commit();
	}
	public void addApustua(Apustua a) {
		db.getTransaction().begin();
		db.persist(a);
		db.getTransaction().commit();
	}
	public void DiruaSartu(Registered u, Double dirua, Date data, String mota) {
		Registered user = (Registered) db.find(Registered.class, u.getUsername()); 
		db.getTransaction().begin();
		Transaction t = new Transaction(user, dirua, data, mota); 
		System.out.println(t.getMota());
		user.addTransaction(t);
		user.updateDiruKontua(dirua);
		db.persist(t);
		db.getTransaction().commit();
	}
	
	public void removedSport1(String sport) {
		db.getTransaction().begin();
		db.remove(sport);
		db.getTransaction().commit();
	}

	public void removeTeam1(String team) {
		db.getTransaction().begin();
		db.persist(team);
		db.getTransaction().commit();
	}
}

