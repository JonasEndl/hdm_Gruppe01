package de.hdm.itprojekt.shared;

import java.util.Vector;

//Kommentare werden noch eingefügt!

public interface MessagingAdministration extends RemoteService {

public void init() throws IllegalArgumentException;

public Nutzer createNutzer(Nutzer n) throws IllegalArgumentException;
public Abonnement createAbonnement ( Abonnement a) throws IllegalArgumentException;
public Hashtag createHashtag(Hashtag h) throws IllegalArgumentException;
public Unterhaltung createUnterhaltung (Unterhaltung u) throws IllegalArgumentException;
public Nachricht createNachricht( Nachricht na) throws IllegalArgumentException;

public void save(Nutzer n) throws IllegalArgumentException;
public void save( Hashtag h) throws IllegalArgumentException;
public void save( Abonnement a) throws IllegalArgumentException;
public void save(Unterhaltung u) throws IllegalArgumentException;
public void save(Nachricht na) throws IllegalArgumentException;


public void delete(Nutzer n) throws IllegalArgumentException;
public void delete( Hashtag h) throws IllegalArgumentException;
public void delete( Abonnement a) throws IllegalArgumentException;
public void delete(Unterhaltung u) throws IllegalArgumentException;
public void delete(Nachricht  na) throws IllegalArgumentException;

public Nutzer getNutzerById(int id) throws IllegalArgumentException;
public Hashtag getHashtagById(int id) throws IllegalArgumentException;
public Abonnement getAbonnementById(int id) throws IllegalArgumentException;
public Unterhaltung getUnterhaltungById(int id) throws IllegalArgumentException;
public Nachricht getNachrichtById(int id) throws IllegalArgumentException;


public Vector<Hashtag> findAllHashtags() throws IllegalArgumentException;

public Vector<Nachricht> getNachrichtbyNutzer(Nutzer n)
    throws IllegalArgumentException;

public Vector<Abonnement> getAbonnementbyNutzer(Nutzer n)
    throws IllegalArgumentException;

public Vector<Abonnement> getAbonnementbyNutzer(Hashtag h)
    throws IllegalArgumentException;

public Vector<Hashtag> getHashtagbyNutzer(Nutzer n)
    throws IllegalArgumentException;

}

