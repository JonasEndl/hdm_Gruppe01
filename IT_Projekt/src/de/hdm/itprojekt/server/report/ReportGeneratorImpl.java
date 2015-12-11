package de.hdm.itprojekt.server.report;

import java.util.Date;
import java.util.Vector;

// Implementierung des ReportGenerator- Interface
public class ReportGeneratorImpl extends RemoteServiceServlet
    implements ReportGenerator {

// Zugriff am Administration aufgrund von Methoden notwendig
  private MessagingAdministration administration = null;

//No-Argument Konstruktor anzulegen aufgrund von GWT
  public ReportGeneratorImpl() throws IllegalArgumentException {
  }

 //Initialisierungsmethode
  public void init() throws IllegalArgumentException {
    

    MessagingAdministrationImpl a = new MessagingAdministrationImpl();
    a.init();
    this.administration = a;
  }

  //protected aufgrund internen gebrauch
  protected MessagingAdministration getMessagingAdministration() {
    return this.administration;
  }

 //Setzen des dazugehörigen Nutzers
  public void setNutzer(Nutzer n) {
    this.administration.setNutzer(n);
  }

//Impressum 
  protected void addImprint(Report r) {
   
//getNutzer() einfügen!

    Nutzer n= this.administration.getNutzer();

    CompositeParagraph imprint = new CompositeParagraph();

//ID nötig?

    imprint.addSubParagraph(new SimpleParagraph(n.getVorname()));
    imprint.addSubParagraph(new SimpleParagraph(n.getNachname()));
    imprint.addSubParagraph(new SimpleParagraph(n.getEmailAdresse()));

    //Impressum hinzufügen
    r.setImprint(imprint);

  }

 // Erster Report
  public AllNachrichtOfNutzerReport createAllNachrichtOfNutzerReport(
      Nutzer n) throws IllegalArgumentException {

    if (this.getMessagingAdministration() == null)
      return null;

   //leerer Report erstellen
    AllNachrichtOfNutzerReport result = new AllNachrichtOfNutzerReport();

// Überschrift
    result.setTitle("Alle Nachrichten des Nutzers");

//Impressum hinzufügen
    this.addImprint(result);

//Erstellungsdatum
    result.setCreated(new Date());

    CompositeParagraph header = new CompositeParagraph();


    header.addSubParagraph(new SimpleParagraph(n.getNachname() + ", "
        + n.getVorname()));

    
    header.addSubParagraph(new SimpleParagraph("Kd.-Nr.: " + n.getId()));

    result.setHeaderData(header);

//Kopfzeile
    Row headline = new Row();

  
    headline.addColumn(new Column("Nachrichten"));
    headline.addColumn(new Column("Hashtags"));

//Hinzufügen der Kopfzeile
    result.addRow(headline);

    
    Vector<Nachricht> nachricht= this.administration.getNachrichtOfNutzer(n);

    for (Nachricht na: nachricht) {
     
//leere Zeile anlegen
      Row nachrichtRow = new Row();

//Erste Spalte die Nachrichten des Nutzers ausgeben
      nachrichtRow.addColumn(new Column(String.valueOf(n.getNachrichtbyNutzer())));

//Zweite Spalte die Hashtags des jeweiligen Nutzers
      nachrichtRow.addColumn(new Column(String.valueOf(n.getHashtagbyNutzer())));
      
//Hinzufügen 
      result.addRow(nachrichtRow);
    }

   return result;
  }

//Zweiter Report
 public AllAbonnementOfNutzerReport createAllAbonnementOfNutzerReport(
      Nutzer n) throws IllegalArgumentException {

    if (this.getMessagingAdministration() == null)
      return null;

// Leerer Report 
    AllAbonnementOfNutzerReport result = new AllAbonnementOfNutzerReport();

//Überschrift
    result.setTitle("Alle Abonnements des Nutzers");

//Impressum hinzufügen
    this.addImprint(result);

//Erstellungsdatum
    result.setCreated(new Date());

    CompositeParagraph header = new CompositeParagraph();

    header.addSubParagraph(new SimpleParagraph(n.getNachname() + ", "
        + n.getVorname()));

    
    header.addSubParagraph(new SimpleParagraph("KD-Nr.: " + n.getId()));

    result.setHeaderData(header);

//Kopfzeile
    Row headline = new Row();

  
    headline.addColumn(new Column("Nutzer"));
    headline.addColumn(new Column("Hashtags"));

    result.addRow(headline);

    
    Vector<Abonnement> abonnement=this.administration.getAbonnementByNutzer(n);

    for (Abonnement a: abonnement) {
     
      Row abonnementRow = new Row();

//Erste Spalte Alle Nutzer, die man abonniert hat
      abonnementRow.addColumn(new Column(String.valueOf(n.getAbonnementbyNutzer())));

//Zweite Spalte: Alle Hashtags, die man abonniert hat
      abonnementRow.addColumn(new Column(String.valueOf(n.getHashtagbyNutzer())));

      
      result.addRow(abonnementRow);
    }

   return result;
  }

}
