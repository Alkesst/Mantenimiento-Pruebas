package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel extends JPanel implements Vista {
	private static final long serialVersionUID = -2213230814123671209L;
	private Map<Campo, JTextField> jtfs;
	private JButton bBorra, bBusca, bAnade, bResetea;
	private JTextArea aTexto;
	private JLabel mensaje;

	public Panel() {
		jtfs = new HashMap<Campo, JTextField>();
		jtfs.put(Campo.NAME, new JTextField());
		jtfs.put(Campo.BEGINING, new JTextField());
		jtfs.put(Campo.END, new JTextField());
		jtfs.put(Campo.DESCRIPTION, new JTextField());
		bBorra = new JButton("REMOVE");
		bBusca = new JButton("SEARCH");
		bAnade = new JButton("ADD");
		bResetea = new JButton("RESET");
		aTexto = new JTextArea(8, 20);
		aTexto.setEditable(false);

		JPanel botonera = new JPanel();
		botonera.setLayout(new GridLayout(4, 3, 10, 10));
		JLabel lApellido = new JLabel("Nombre");
		botonera.add(lApellido);
		botonera.add(jtfs.get(Campo.NAME));
		botonera.add(bBorra);
		JLabel lNombre = new JLabel("Fecha comienzo");
		botonera.add(lNombre);
		botonera.add(jtfs.get(Campo.BEGINING));
		botonera.add(bBusca);
		JLabel lTelefono = new JLabel("Fecha final");
		botonera.add(lTelefono);
		botonera.add(jtfs.get(Campo.END));
		botonera.add(bAnade);
		JLabel lEmail = new JLabel("Descripción");
		botonera.add(lEmail);
		botonera.add(jtfs.get(Campo.DESCRIPTION));
		botonera.add(bResetea);

		mensaje = new JLabel("Calendario creado");
		mensaje.setBorder(BorderFactory.createEtchedBorder());

		JPanel sur = new JPanel();
		sur.setLayout(new BorderLayout());
		sur.add(BorderLayout.CENTER, botonera);
		sur.add(BorderLayout.SOUTH, mensaje);

		setLayout(new GridLayout(2, 1));
		add(aTexto);
		add(sur);
	}

	@Override
	public void addController(ActionListener ctrl) {
		bBorra.addActionListener(ctrl);
		bBorra.setActionCommand(Vista.ERASE);
		bBusca.addActionListener(ctrl);
		bBusca.setActionCommand(Vista.SEARCH);
		bAnade.addActionListener(ctrl);
		bAnade.setActionCommand(Vista.ADD);
		bResetea.addActionListener(ctrl);
		bResetea.setActionCommand(Vista.RESET);
	}

	@Override
	public void clearEvent() {
		for (Campo c : jtfs.keySet())
			jtfs.get(c).setText("");
	}

	@Override
	public void show(Evento cn) {
		for (Campo cm : jtfs.keySet())
			jtfs.get(cm).setText(cn.getValor(cm).toString());
	}

	@Override
	public void show(String t) {
		aTexto.setText(t);
	}

	public String get(Campo f) {
		return jtfs.get(f).getText();
	}

	@Override
	public Evento getEvent() {
		return new Evento(get(Campo.NAME), get(Campo.BEGINING), get(Campo.END), get(Campo.DESCRIPTION));
	}

	public Map<Campo,String> getFields() {
		Map<Campo,String> m = new HashMap<Campo,String>();
		for (Campo f : Campo.values()) 
			m.put(f, get(f));
		return m;
	}

	@Override
	public void message(String texto) {
		mensaje.setText(texto);
	}
}
