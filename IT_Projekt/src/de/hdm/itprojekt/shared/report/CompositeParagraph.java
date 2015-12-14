package de.hdm.itprojekt.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Klasse mit einer Menge einzelner Absätze <code>SimpleParagraph</code>-Objekte. 
 * Diese werden als Unterabschnitte in einem <code>Vector</code>
 * abgelegt verwaltet.
 * 
 * @author Teuta
 */
public class CompositeParagraph extends Paragraph implements Serializable {
	
	private static final long serialVersionUID = 1L;

/**
 * Speicherort der Unterabschnitte.
 */
	
	private Vector<SimpleParagraph> subParagraphs = new Vector<SimpleParagraph>();

/** 
 * Einen Unterabschnitt hinzufügen.
 * 
 * @param p der hinzufügende Unterabschnitt.
 */
	public void addSubParagraph(SimpleParagraph p) {
		this.subParagraphs.addElement(p);	
	}
	
/**
 * Einen Unterabschnitt entfernen.
 * 
 * @param p der zu entfernende Unterabschnitt.
 */
	
	
	public void removeParagraph(SimpleParagraph p){
		
		this.subParagraphs.removeElement(p);
		}
	
/** 
 * Auslesen aller Unterabschnitte.
 * 
 * @return <code>Vector</code>, der sämtliche Unterabschnitte enthält.
 */
	public Vector<SimpleParagraph> getSubParagraphs(){
		return this.subParagraphs;
	}
	
/** 
 * Auslesen der Anzahl der Unterabschnitte.
 * 
 * @return Anzahl der Unterabschnitte.
 */
	public int getNumParagraphs(){
		return this.subParagraphs.size();	
	}
	
/** 
 * Auslesen eines einzelnen Unterabschnitts.
 * 
 * @param i der Index des gewünschten Unterabschnitts ( 0 < = i < n ), mit n = Anzahl der Unterabschnitte.
 * 
 * @return der gewünschte Unterabschnitt.
 */
	
	public SimpleParagraph getParagraphAt (int i){
		return this.subParagraphs.elementAt(i);
	}


/**
 * Umwandeln eines <code>CompositeParagraph</code> in einen <code>String</code>.
 */

	public String toString(){
		
/**
 * Ein leerer Buffer wird angelegt. Hier werden sämtlicher String Repräsentationen der Unterabschnitte´eingetragen.
 */
		
		StringBuffer result = new StringBuffer();
		
/**
 * Schleife über alle Unterabschnitte.
 */
		
		for (int i = 0; i < this.subParagraphs.size(); i++){
			
			SimpleParagraph p = this.subParagraphs.elementAt(i);
			
/**
 * den jeweiligen Unterabschnitt in einen String wandeln und an einen Buffer hängen.
 *
 */
			
			result.append(p.toString() + "/n");
			
		}
		
/**
 * Buffer wird in einen String umgewandelt und zurückgegeben.
 */
		return result.toString();
		}
	}


