package de.hdm.itprojekt.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/* Diese Klasse ist die Basisklasse aller Showcases. Jeder *Showcase ist ein VerticalPanel und l�sst sich somit unter *GWT entsprechend anordnen 
 */

public abstract class Showcase extends VerticalPanel {

  /*Jedes GWT Widget muss diese Methode implementieren. Sie        *gibt an, das geschehen soll, wenn eine Widget-Instanz zur *Anzeige gebracht wird.
  */

  public void onLoad() {
    
/*Bevor wir unsere eigene Formatierung veranslassen, *�berlassen wir es der Superklasse eine Initialisierung *vorzunehmen.
*/
    super.onLoad();

/*Als erstes geben wir stets die Headline des Showcase aus. Da
 *getHeadlineText() als abstrakte Methode bzw. als   *Einschubmethode realisiert wird, obliegt es den Subklassen, *f�r eine Ausgestaltung also Implementierung zu sorgen.
*/
    this.add(this.createHeadline(this.getHeadlineText()));

 /* Wenn alles vorbereitet ist, sto�en wir die run()-Methode  *an. Auch run()ist als abstrakte Methode bzw. als *Einschubmethode realisiert. Auch hier ist es Aufgabe der *Subklassen, f�r deren Implementierung zu sorgen.
*/
    this.run();
  }

  /*Mit Hilfe dieser Methode erstellen wir aus einem String *ein mittels CSS formatierbares HTML-Element. Unter CSS l�sst *sich das Ergebnis �ber <code>.bankproject-headline</code> *referenzieren bzw. formatieren.
 */

  protected HTML createHeadline(String text) {
    HTML content = new HTML(text);
    content.setStylePrimaryName("messagingproject-headline");
    return content;
  }

  /*Mit Hilfe dieser Methode erstellen wir aus einem String *ein mittels CSS formatierbares HTML-Element, das an das Ende *der bisherigen Ausgabe dieses Showcase angeh�ngt wird. Unter *CSS l�sst sich das Ergebnis �ber 
*<code>.bankproject-simpletext</code> referenzieren bzw. *formatieren.
*/

  protected void append(String text) {
    HTML content = new HTML(text);
    content.setStylePrimaryName("messagingproject-simpletext");
    this.add(content);
  }

  /* Abstrakte Einschubmethode, die in den Subklassen zu realisieren ist.
  */
  
  protected abstract String getHeadlineText();

  /*Abstrakte Einschubmethode, die in den Subklassen zu realisieren ist.
   */
  protected abstract void run();
}
