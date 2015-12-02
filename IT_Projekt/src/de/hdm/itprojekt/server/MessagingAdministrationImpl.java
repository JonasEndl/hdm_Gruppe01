package de.hdm.itprojekt.server;


import java.util.Vector;



//!!MessagingAdministrationImpl noch anpassen! siehe Klassendiagramm


public class MessagingAdministrationImpl extends RemoteServiceServlet
    implements MessagingAdministration {

  
  private Nutzer nutzer = null;
  
  private NutzerMapper nMapper = null;

  private AbonnementMapper aMapper = null;

  private HashtagMapper hMapper = null;

  private UnterhaltungMapper uMapper = null;
  
  private NachrichtMapper naMapper = null;


  public MessagingAdministrationImpl() throws IllegalArgumentException {
   
  }

  public void init() throws IllegalArgumentException {
    
    this.nMapper = NutzerMapper.nutzerMapper();
    this.aMapper = AbonnementMapper.abonnementMapper();
    this.hMapper = HashtagMapper.hashtagMapper();
    this.uMapper = UnterhaltungMapper.unterhaltungMapper();
    this.naMapper = NachrichtMapper.nachrichtMapper();
  }

 
  
  public Nutzer createNutzer(Nutzer n) throws IllegalArgumentException {
    Nutzer n = new Nutzer();

     n.setId(1);

    return this.nMapper.insert(n);
  }

 
  public Nutzer getNutzerById(int id) throws IllegalArgumentException {
    return this.nMapper.findByKey(id);
  }

  //* bei save und delete als Rückgabewert nicht besser void ???siehe Klassendiagramm

  public Nutzer save(Nutzer n) throws IllegalArgumentException {
    nMapper.update(n);
  }

  public Nutzer delete(Nutzer n) throws IllegalArgumentException {
    
//* sämtliche Daten von Nutzer vorher löschen???

    Vector<Nachricht> nachricht= this.getNachrichtByNutzer(n);

    if (nachrichten != null) {
      for (Nachricht na : nachricht) {
        this.delete(na);
      }
    }

   Vector<Abonnement> abonnement=this.getAbonnementByNutzer(n);

    if (abonnement != null) {
      for (Abonnement a : abonnement) {
        this.delete(a);
      }
    } 
Vector<Hashtag> hashtag=this.getHashtagByNutzer(n);

    if (hashtag!= null) {
      for (Hashtag h : hashtag) {
        this.delete(h);
      }
    }
 
    this.nMapper.delete(n);
  }


public Nutzer createHashtag(Hashtag h) throws IllegalArgumentException {
    Hashtag h = new Hashtag();

     h.setId(1);

    return this.hMapper.insert(h);
  }

 
  public Hashtag getHashtagById(int id) throws IllegalArgumentException {
    return this.hMapper.findByKey(id);
  }

  //* bei save und delete als Rückgabewert nicht besser void ???

  public Hashtag save( Hashtag h) throws IllegalArgumentException {
    hMapper.update(h);
  }

  public Hashtag delete( Hashtag h) throws IllegalArgumentException {
    
//* sämtliche Daten von Hashtag vorher löschen???

   this.hMapper.delete(h);
  }



public Abonnement createAbonnement ( Abonnement a) throws IllegalArgumentException {
    Abonnement a = new Abonnement();

     a.setId(1);

    return this.aMapper.insert(a);
  }

 
  public Abonnement getAbonnementById(int id) throws IllegalArgumentException {
    return this.aMapper.findByKey(id);
  }

  //* bei save und delete als Rückgabewert nicht besser void ???

  public Abonnement save( Abonnement a) throws IllegalArgumentException {
    aMapper.update(a);
  }

  public Abonnement delete( Abonnement a) throws IllegalArgumentException {
    
//* sämtliche Daten von Abonnement vorher löschen???

    this.aMapper.delete(a);
  }

 public Unterhaltung createUnterhaltung (Unterhaltung u) throws IllegalArgumentException {
    Unterhaltung u = new Unterhaltung ();

     u.setId(1);

    return this.uMapper.insert(u);
  }

 
  public Unterhaltung  getUnterhaltung ById(int id) throws IllegalArgumentException {
    return this.uMapper.findByKey(id);
  }

  //* bei save und delete als Rückgabewert nicht besser void ???

  public Unterhaltung save(Unterhaltung u) throws IllegalArgumentException {
    uMapper.update(u);
  }

  public Unterhaltung delete(Unterhaltung u) throws IllegalArgumentException {
    
//* sämtliche Daten von Unterhaltung vorher löschen???

    
    this.uMapper.delete(u);
  }


 public Nachricht createNachricht  (Nachricht na) throws IllegalArgumentException {
    Nachricht na = new Nachricht ();

     na.setId(1);

    return this.naMapper.insert(na);
  }

 
  public Nachricht getNachricht  ById(int id) throws IllegalArgumentException {
    return this.naMapper.findByKey(id);
  }

  //* bei save und delete als Rückgabewert nicht besser void ???

  public Unterhaltung save(Unterhaltung u) throws IllegalArgumentException {
    uMapper.update(u);
  }

  public Nachricht delete(Nachricht  na) throws IllegalArgumentException {
    
   
    this.naMapper.delete(na);
  }


public Vector<Hashtag> findAllHashtags() throws IllegalArgumentException {
    return this.hMapper.findAll();
  }

 
  public Vector<Nachricht> getNachrichtbyNutzer(Nutzer n)
      throws IllegalArgumentException {
    return this.naMapper.findByOwner(n);
  }

  public Vector<Abonnement> getAbonnementbyNutzer(Nutzer n)
      throws IllegalArgumentException {
    return this.aMapper.findByOwner(n);
  }

  public Vector<Abonnement> getAbonnementbyNutzer(Hashtag h)
      throws IllegalArgumentException {
    return this.aMapper.findByOwner(h);
  }

//* Einfügen??? wegen Report Generator

  public Vector<Hashtag> getHashtagbyNutzer(Nutzer n)
      throws IllegalArgumentException {
    return this.hMapper.findByOwner(n);
  }

 
  
}

