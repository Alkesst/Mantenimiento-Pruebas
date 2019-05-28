package gui;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.Agenda;
import gui.Controlador;
import gui.Panel;
import gui.Vista;

public class Principal {
	public static void main(String[] args) {
		Agenda date = new Agenda();
		Vista vista = new Panel();
		ActionListener ctrl = new Controlador(vista, date);
		vista.addController(ctrl);

		JFrame gui = new JFrame("MiniAgenda");
		gui.setContentPane((JPanel) vista);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.pack();
		gui.setVisible(true);
	}
}
