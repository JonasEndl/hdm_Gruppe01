package de.hdm.itprojekt.shared.report;

/**
* Report, der alle Nachrichten eines Nutzers darstellt.
* Diese Klasse enthält keine weiteren Attribute- und Methodenimplementierungen. Alles 
* was benötigt wird, liegt in den Superklassen vor.
* Dennoch wird diese Klasse benötigt um bestimmte Typen von Reports zu deklarieren um  mit
* ihnen objektorientiert umgehen zu können.
* 
* @author Teuta
*/

import java.io.Serializable;

public class AllNachrichtOfNutzerReport extends CompositeReport implements Serializable {

  private static final long serialVersionUID = 1L;

}