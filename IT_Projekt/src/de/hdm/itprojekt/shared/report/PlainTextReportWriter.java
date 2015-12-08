package de.hdm.itprojekt.shared.report;

import java.util.Vector;

/*
 * ein ReportWriter der Reports mittels Plain Text formatiert. 
 */
public class PlainTextReportWriter extends ReportWriter {

  private String reportText = "";

  
  public void resetReportText() {
    this.reportText = "";
  }


  public String getHeader() {

	  return "";
  }

  public String getTrailer() {

	  return "___________________________________________";
  }

  
 
public void process(AllAbonnementOfNutzerReport r) {

    this.resetReportText();

   
    StringBuffer result = new StringBuffer();

    
    result.append("*** " + r.getTitle() + " ***\n\n");
    result.append(r.getHeaderData() + "\n");
    result.append("Erstellt am: " + r.getCreated().toString() + "\n\n");
    Vector<Row> rows = r.getRows();

    for (Row row : rows) {
      for (int k = 0; k < row.getNumColumns(); k++) {
        result.append(row.getColumnAt(k) + "\t ; \t");
      }

      result.append("\n");
    }

    result.append("\n");
    result.append(r.getImprint() + "\n");

    this.reportText = result.toString();
  }

public void process(AllNachrichtOfNutzerReport r) {

    StringBuffer result = new StringBuffer();


    result.append("*** " + r.getTitle() + " ***\n\n");

    if (r.getHeaderData() != null)
      result.append(r.getHeaderData() + "\n");

    result.append("Erstellt am: " + r.getCreated().toString() + "\n\n");


    for (int i = 0; i < r.getNumSubReports(); i++) {
 
      AllAbonnementOfNutzerReport subReport = (AllAbonnementOfNutzerReport) r
          .getSubReportAt(i);

      this.process(subReport);

      
      result.append(this.reportText + "\n\n\n\n\n");

      this.resetReportText();
    }

    this.reportText = result.toString();
  }

  public String getReportText() {
    return this.getHeader() + this.reportText + this.getTrailer();
  }
}