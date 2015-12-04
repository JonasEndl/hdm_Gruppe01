package de.hdm.itprojekt.shared.report;

import java.io.Serializable;
import java.util.Vector;

/*
 * Klasse mit Menge einzelner Absätze. Diese werden als Unterabschnitte in einem <code>Vector</code>
 *  abgelegt verwaltet.
 * 
 */
public class CompositeParagraph extends Paragraph implements Serializable {
	
	private static final long serialVersionUID = 1L;

/*
 * Speicherort der Unterabschnitte.
 */
	
	private Vector<SimpleParagraph> subParagraphs = new Vector<SimpleParagraph>();

/* 
 * einen Unterabschnitt hinzufügen.
 * 
 */
	public void addSubParagraph(SimpleParagraph p) {
		this.subParagraphs.addElement(p);	
	}
	
/*
 * einen Unterabschnitt entfernen.
 */
	
	public void removeParagraph(SimpleParagraph p){
		
		this.subParagraphs.removeElement(p);
		}
	
/* 
 * Auslesen aller Unterabschnitte.
 */
	public Vector<SimpleParagraph> getSubParagraphs(){
		return this.subParagraphs;
	}
	
/* 
 * Auslesen der Anzahl der Unterabschnitte
 */
	public int getNumParagraphs(){
		return this.subParagraphs.size();	
	}
	
/* 
 * Auslesen eines einzelnen Unterabschnitts.
 */
	
	public SimpleParagraph getParagraphAt (int i){
		return this.subParagraphs.elementAt(i);
	}


/*
 * Umwandeln in eine String.
 * @see java.lang.Object#toString()
 */

	public String toString(){
		
/*
 * leerer Buffer. Eintragung sämtlicher String Repräsentationen der Unterabschnitte.
 */
		
		StringBuffer result = new StringBuffer();
		
/*
 * Schleife über alle Unterabschnitte.
 */
		
		for (int i = 0; i < this.subParagraphs.size(); i++){
			
			SimpleParagraph p = this.subParagraphs.elementAt(i);
			
/*
 * jeweiligen Unterabschnitt in einen String wandeln und an Buffer hängen.
 *
 */
			
			result.append(p.toString() + "/n");
			
		}
		
/*
 * Buffer wird in einen String umgewandelt und zurückgegeben.
 */
		return result.toString();
		}
	}


