package de.hdm.itprojekt.server;


import java.util.Vector;


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
  
  
  /* Anpassung der MapperKlassen fehlt noch!!!!
   * NutzerAbo + HashtagAbo  
   */
  
  private NutzerMapper nMapper = null;

  private AbonnementMapper aMapper = null;

  private HashtagMapper hMapper = null;

  private UnterhaltungMapper uMapper = null;
  
  private NachrichtMapper naMapper = null;

// Durch GWT ein No-argument Konstruktor nötig
  public MessagingAdministrationImpl() throws IllegalArgumentException {
   
  }
/*Initialisierungsmethode
 * Anpassung fehlt noch!!
 * NutzerAbo + HashtagAbo
 */
  
  public void init() throws IllegalArgumentException {
    
    this.nMapper = NutzerMapper.nutzerMapper();
    this.aMapper = AbonnementMapper.abonnementMapper();
    this.hMapper = HashtagMapper.hashtagMapper();
    this.uMapper = UnterhaltungMapper.unterhaltungMapper();
    this.naMapper = NachrichtMapper.nachrichtMapper();
  }

 
  //Nutzer erstellen
  public Nutzer createNutzer(Nutzer n) throws IllegalArgumentException {
    Nutzer n = new Nutzer();

     n.setId(1);

    return this.nMapper.insert(n);
  }

 //Nutzer anhand der ID finden
  public Nutzer getNutzerById(int id) throws IllegalArgumentException {
    return this.nMapper.findByKey(id);
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
public Hashtag createHashtag(Hashtag h) throws IllegalArgumentException {
    Hashtag h = new Hashtag();

     h.setId(1);

    return this.hMapper.insert(h);
  }

 //Hashtag finden anhand der ID
  public Hashtag getHashtagById(int id) throws IllegalArgumentException {
    return this.hMapper.findByKey(id);
  }

 
//Hashtag speichern
  public void saveHashtag( Hashtag h) throws IllegalArgumentException {
    hMapper.update(h);
  }
//Hashtag löschen
  public void delete( Hashtag h) throws IllegalArgumentException {
    
   this.hMapper.delete(h);
  }

// Abo anpassen!!
//Abo erstellen
public Abonnement createAbonnement ( Abonnement a) throws IllegalArgumentException {
    Abonnement a = new Abonnement();

     a.setId(1);

    return this.aMapper.insert(a);
  }

 //Abo finden anhand der ID
  public Abonnement getAbonnementById(int id) throws IllegalArgumentException {
    return this.aMapper.findByKey(id);
  }

 
//Abo speichern
  public void saveAbonnement( Abonnement a) throws IllegalArgumentException {
    aMapper.update(a);
  }
//Abo löschen
  public void deleteAbonnement( Abonnement a) throws IllegalArgumentException {
    
    this.aMapper.delete(a);
  }
//Unterhaltung erstellen
 public Unterhaltung createUnterhaltung (Unterhaltung u) throws IllegalArgumentException {
    Unterhaltung u = new Unterhaltung ();

     u.setId(1);

    return this.uMapper.insert(u);
  }

 //Unterhaltung finden anhand der ID
  public Unterhaltung  getUnterhaltungById(int id) throws IllegalArgumentException {
    return this.uMapper.findByKey(id);
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
 public Nachricht createNachricht  (Nachricht na) throws IllegalArgumentException {
    Nachricht na = new Nachricht ();

     na.setId(1);

    return this.naMapper.insert(na);
  }

 //Nachricht anhand der ID finden
  public Nachricht getNachrichtById(int id) throws IllegalArgumentException {
    return this.naMapper.findByKey(id);
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
  /*Anpassung an Nutzerabo+ Hashtagabo
   */Nutzerabos vom Nutzer finden
  
   public Vector<Abonnement> getAbonnementbyNutzer(Nutzer n)
      throws IllegalArgumentException {
    return this.aMapper.findByOwner(n);
  }
//Hashtagabos vom Nutzer finden
  public Vector<Abonnement> getAbonnementbyNutzer(Hashtag h)
      throws IllegalArgumentException {
    return this.aMapper.findByOwner(h);
  }
// Hashtags vom Nutzer finden
  public Vector<Hashtag> getHashtagbyNutzer(Nutzer n)
      throws IllegalArgumentException {
    return this.hMapper.findByOwner(n);
  }

}

