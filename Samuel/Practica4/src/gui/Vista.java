package gui;

import java.awt.event.ActionListener;
import java.util.Map;

public interface Vista {
	String ERASE = "Borra", SEARCH = "Busca", RESET = "Resetea", ADD = "anade";

	// Borra el evento mostrado en los campos de texto
	void clearEvent();

	// Muestra el evento c en los campos de texto correspondientes
	void show(Evento c);

	// Pone el string recibido en el area de texto de la GUI
	void show(String t);

	// Establece el objeto ctrl como oyente de las acciones de la GUI
	void addController(ActionListener ctrl);

	// Devuelve los datos introducidos en los campos de texto. El map devuelto
	// asocia a cada campo la entrada disponible.
	String get(Campo name);

	// Devuelve un evento con los valores en los campos de texto.
	Evento getEvent();

	// Devuelve un map con los valores en los campos de texto.
	Map<Campo, String> getFields();

	// Muestra un mensaje informativo
	void message(String texto);
}
