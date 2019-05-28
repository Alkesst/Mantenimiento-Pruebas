package gui;

import java.util.HashMap;
import java.util.Map;

import calendar.Date;

public class Evento implements Comparable<Evento> {
	private Map<Campo, Object> datos = new HashMap<Campo, Object>();

	public Evento(String n, String a, String e, String t) {
		if (n == null || a == null || e == null || t == null)
			throw new RuntimeException("Evento no válido");
		Date beg = new Date(a);
		Date end = new Date(e);
		if (beg.compareTo(end) > 0)
			throw new RuntimeException("Evento no válido");
		datos.put(Campo.NAME, n);
		datos.put(Campo.BEGINING, beg);
		datos.put(Campo.END, end);
		datos.put(Campo.DESCRIPTION, t);
	}

	public Object getValor(Campo f) {
		return datos.get(f);
	}

	public void setValor(Campo campo, String valor) {
		datos.put(campo, valor);
	}

	public boolean equals(Object o) {
		return o instanceof Evento && getValor(Campo.NAME).equals(((Evento) o).getValor(Campo.NAME))
				&& getValor(Campo.BEGINING).equals(((Evento) o).getValor(Campo.BEGINING))
				&& getValor(Campo.DESCRIPTION).equals(((Evento) o).getValor(Campo.DESCRIPTION))
				&& getValor(Campo.END).equals(((Evento) o).getValor(Campo.END));
	}

	public int hashCode() {
		return getValor(Campo.NAME).hashCode() + getValor(Campo.BEGINING).hashCode()
				+ getValor(Campo.DESCRIPTION).hashCode() + getValor(Campo.END).hashCode();
	}

	@Override
	public int compareTo(Evento c) {
		int r = ((Date) getValor(Campo.BEGINING)).compareTo((Date) c.getValor(Campo.BEGINING));
		if (r == 0) {
			r = ((String) getValor(Campo.NAME)).compareTo((String) c.getValor(Campo.NAME));
			if (r == 0) {
				r = ((Date) getValor(Campo.END)).compareTo((Date) c.getValor(Campo.END));
				if (r == 0)
					r = ((String) getValor(Campo.DESCRIPTION)).compareTo((String) c.getValor(Campo.DESCRIPTION));
			}
		}
		return r;
	}

	public String toString() {
		String r = "Evento: [";
		for (Campo cm : Campo.values())
			r += getValor(cm) + ", ";
		return r.substring(0, r.length() - 2) + "]";
	}
}
