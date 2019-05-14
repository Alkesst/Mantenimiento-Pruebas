package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
	private Vista vista;
	private Agenda agenda;

	public Controlador(Vista v, Agenda a) {
		vista = v;
		agenda = a;
		v.show(toString(agenda));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command == Vista.ERASE) {
			try {
				Evento c = vista.getEvent();
				if (agenda.borraEvent(c))
					vista.message("Evento borrado");
				else
					vista.message("Evento no existe");
				vista.clearEvent();
				vista.show(toString(agenda));
			} catch (RuntimeException exc) {
				vista.message("Evento no válido");
			}
		} else if (command == Vista.ADD) {
			try {
				Evento c = vista.getEvent();
				if (agenda.insertaEvent(c))
					vista.message("Evento insertado");
				else
					vista.message("Evento ya existe");
				vista.show(toString(agenda));
			} catch (RuntimeException exc) {
				vista.message("Evento no válido");
			}
		} else if (command == Vista.SEARCH) {
			try {
				Evento c = agenda.buscaEvent(vista.getFields());
				if (c == null) {
					vista.message("Evento no encontrado");
					vista.show("");
				} else
					vista.show(c.toString());
			} catch (RuntimeException exc) {
				vista.message("Evento no válido");
			}
		} else {// if (command == Vista.RESET){
			vista.show("");
			vista.clearEvent();
			agenda.reset();
			vista.message("Calendario reseteado");
		}
	}

	private static String toString(Agenda a) {
		StringBuilder texto = new StringBuilder();
		for (Evento cn : a.getEvents())
			texto.append(cn + "\n");
		return texto.toString();
	}
}
