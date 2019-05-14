package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import calendar.Date;

public class Agenda {
	private SortedSet<Evento> events;

	public Agenda() {
		events = new TreeSet<Evento>();
	}

	public Agenda(String file) throws FileNotFoundException {
		this();
		leeAgenda(new Scanner(new File(file)));
	}

	protected void leeAgenda(Scanner sc) {
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			Scanner scLn = new Scanner(line);
			scLn.useDelimiter("[#]+");
			while (scLn.hasNext()) {
				String na = scLn.next();
				Scanner scNa = new Scanner(na);
				scNa.useDelimiter("[ ,]+");
				try {
					insertaEvent(new Evento(scNa.next(), scNa.next(), scLn.next(), scLn.next()));
				} catch (NoSuchElementException e) {
					// descartamos la linea sin hacer nada
				}
				scNa.close();
			}
			scLn.close();
		}
	}

	public boolean insertaEvent(Evento c) {
		return events.add(c);
	}

	public void escribeAgenda(String fichero) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fichero);
		escribeAgenda(pw);
		pw.close();
	}

	public void escribeAgenda(PrintWriter pw) {
		for (Evento c : events)
			pw.printf("%s, %s#%s#%s\n", c.getValor(Campo.NAME), c.getValor(Campo.BEGINING), c.getValor(Campo.END),
					c.getValor(Campo.DESCRIPTION));
	}

	public Evento buscaEvent(Map<Campo, String> m) {
		for (Evento c : events)
			if ((m.get(Campo.NAME).isEmpty() || (((String) c.getValor(Campo.NAME)).indexOf(m.get(Campo.NAME)) >= 0))
					&& (m.get(Campo.BEGINING).isEmpty() || ((((Date) c.getValor(Campo.BEGINING))
							.compareTo(new Date(m.get(Campo.BEGINING))) <= 0)
							&& (((Date) c.getValor(Campo.END)).compareTo(new Date(m.get(Campo.BEGINING))) >= 0)))
					&& (m.get(Campo.END).isEmpty()
							|| ((((Date) c.getValor(Campo.BEGINING)).compareTo(new Date(m.get(Campo.END))) <= 0)
									&& ((Date) c.getValor(Campo.END)).compareTo(new Date(m.get(Campo.END))) >= 0))
					&& ((m.get(Campo.DESCRIPTION).isEmpty()
							|| ((String) c.getValor(Campo.DESCRIPTION)).indexOf(m.get(Campo.DESCRIPTION)) >= 0)))
				return c;
		return null;
	}

	public boolean borraEvent(Evento event) {
		return events.remove(event);
	}

	public void reset() {
		events.clear();
	}

	public SortedSet<Evento> getEvents() {
		return events;
	}

	public String toString() {
		String r = "Agenda(";
		if (events.isEmpty())
			return r + ")";
		else {
			for (Evento cn : events)
				r += cn + ", ";
			return r.substring(0, r.length() - 2) + ")";
		}
	}
}
