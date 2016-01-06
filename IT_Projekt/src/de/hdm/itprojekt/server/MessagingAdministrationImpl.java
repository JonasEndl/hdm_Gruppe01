package de.hdm.itprojekt.server;


import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.*;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.*;



//Implementierungsklasse des Interface von MessagingAdmin


/* Diese Klasse hat neben ReportGeneratorImpl sämtliche Applikationslogik.
 * Sie ist quasi die Schnittstelle für alle Daten und Abläufe.
 * In den Methoden wird die Applikationslogik ausgeführt.
*/
public class MessagingAdministrationImpl extends RemoteServiceServlet
    implements MessagingAdministration {


// Referenz auf das zugehörige Nutzer-Objekt
  private Nutzer nutzer = null;
  
  /* Referenz auf die MapperKlassen, der Nutzer-Objekte
  mit der Datenbank abgleicht
  */
  
  
  /* Anpassung der MapperKlassen
   
   */
  
  private NutzerMapper nMapper = null;
  
  private HashtagAboMapper haMapper = null;

  private NutzerAboMapper nabMapper= null;

  private HashtagMapper hMapper = null;

  private UnterhaltungMapper uMapper = null;
  
  private NachrichtMapper naMapper = null;

// Durch GWT ein No-argument Konstruktor nötig
  public MessagingAdministrationImpl() throws IllegalArgumentException {
   
  }
/*Initialisierungsmethode
 * Anpassung erfolgt
 */
  
  public void init() throws IllegalArgumentException {
    
    this.nMapper = NutzerMapper.nutzerMapper();
    this.haMapper= HashtagAboMapper.hashtagAboMapper();
    this.nabMapper = NutzerAboMapper.nutzerAboMapper();
    this.hMapper = HashtagMapper.hashtagMapper();
    this.uMapper = UnterhaltungMapper.unterhaltungMapper();
    this.naMapper = NachrichtMapper.nachrichtMapper();
  }

 
  //Nutzer erstellen
  public Nutzer createNutzer(Nutzer n) throws IllegalArgumentException {
    Nutzer nu = new Nutzer();

     n.setID(1);

    return this.nMapper.insert(n);
  }

 //Nutzer anhand der ID finden
  public Nutzer getNutzerById(int id) throws IllegalArgumentException {
    return this.nMapper.findById(id);
  }

  //Nutzer speichern
  public void saveNutzer(Nutzer n) throws IllegalArgumentException {
    nMapper.update(n);
  }
//Nutzer löschen
  public void deleteNutzer(Nutzer n) throws IllegalArgumentException {
    
    this.nMapper.delete(n);
  }

//Hashtag erstellen
public Hashtag createHashtag(Hashtag x) throws IllegalArgumentException {
    Hashtag h = new Hashtag();

     h.setID(1);

    return this.hMapper.insert(h);
  }

 //Hashtag finden anhand der ID
  public Hashtag getHashtagById(int id) throws IllegalArgumentException {
    return this.hMapper.findById(id);
  }

 
//Hashtag speichern
  public void saveHashtag( Hashtag h) throws IllegalArgumentException {
    hMapper.update(h);
  }
//Hashtag löschen
  public void deleteHashtag( Hashtag h) throws IllegalArgumentException {
    
   this.hMapper.delete(h);
  }

// Abo erstellen
  
public HashtagAbo createHashtagAbo ( HashtagAbo h) throws IllegalArgumentException {
    HashtagAbo ha = new HashtagAbo();

     ha.setID(1);

    return this.haMapper.insert(ha);
  }

public NutzerAbo createNutzerAbo ( NutzerAbo x) throws IllegalArgumentException {
    NutzerAbo nab = new NutzerAbo();

     nab.setID(1);

    return this.nabMapper.insert(nab);
  }

 //Abo finden anhand der ID
  public NutzerAbo getNutzerAboById(int id) throws IllegalArgumentException {
    return this.nabMapper.findById(id);
  }

 
  public HashtagAbo getHashtagAboById(int id) throws IllegalArgumentException {
	    return this.haMapper.findById(id);
	  }

	 
//Abo speichern
  public void saveNutzerAbo( NutzerAbo nab) throws IllegalArgumentException {
    nabMapper.update(nab);
  }
  
  public void saveHashtagAbo( HashtagAbo ha) throws IllegalArgumentException {
	    haMapper.update(ha);
	  }
//Abo löschen
  public void deleteNutzerAbo( NutzerAbo nab) throws IllegalArgumentException {
    
    this.nabMapper.delete(nab);
  }
  
  public void deleteHashtagAbo( HashtagAbo ha) throws IllegalArgumentException {
	    
	    this.haMapper.delete(ha);
	  }
//Unterhaltung erstellen
 public Unterhaltung createUnterhaltung (Unterhaltung x) throws IllegalArgumentException {
    Unterhaltung u = new Unterhaltung ();

     u.setID(1);

    return this.uMapper.insert(u);
  }

 //Unterhaltung finden anhand der ID
  public Unterhaltung  getUnterhaltungById(int id) throws IllegalArgumentException {
    return this.uMapper.findById(id);
  }
//Unterhaltung speichern
  public void saveUnterhaltung(Unterhaltung u) throws IllegalArgumentException {
    uMapper.update(u);
  }
//Unterhaltung löschen
  public void deleteUnterhaltung(Unterhaltung u) throws IllegalArgumentException {
        
    this.uMapper.delete(u);
  }

//Nachricht erstellen
 public Nachricht createNachricht  (Nachricht x) throws IllegalArgumentException {
    Nachricht na = new Nachricht ();

     na.setID(1);

    return this.naMapper.insert(na);
  }

 //Nachricht anhand der ID finden
  public Nachricht getNachrichtById(int id) throws IllegalArgumentException {
    return this.naMapper.findById(id);
  }
//Nachricht speichern
  public void saveNachricht(Nachricht  na) throws IllegalArgumentException {
    uMapper.update(na);
  }
//Nachricht löschen
  public void deleteNachricht(Nachricht  na) throws IllegalArgumentException {
    
    this.naMapper.delete(na);
  }

// Alle Hashtags finden
public Vector<Hashtag> findAllHashtags() throws IllegalArgumentException {
    return this.hMapper.findAll();
  }

 //Nachrichten vom Nutzer finden
  public Vector<Nachricht> getNachrichtbyNutzer(Nutzer n)
      throws IllegalArgumentException {
    return this.naMapper.findByOwner(n);
  }
  /*Nutzerabos vom Nutzer finden
   */
  
   public Vector<NutzerAbo> getAllNutzerAbo()
      throws IllegalArgumentException {
    return this.nabMapper.findAll();
  }
   
//Hashtagabos vom Nutzer finden
  public Vector<HashtagAbo> getAllHashtagAbo()
      throws IllegalArgumentException {
    return this.haMapper.findAll();
  }
// Hashtags vom Nutzer finden
  public Vector<Hashtag> getHashtagbyNutzer(Nutzer n)
      throws IllegalArgumentException {
    return this.hMapper.findByOwner(n);
  }
//Alle Nutzer
  
public Vector<Nutzer>getAllNutzer() 
		throws IllegalArgumentException {
	return this.nMapper.findAll();
}

/*Alle Nutzer, die man noch abonnieren kann,
	also noch nicht abonniert wurden.
*/


public Vector<Nutzer>getAllAbonnierbareNutzer()
		throws IllegalArgumentException {
			return null;
	// Fehlt noch!!
}
}


