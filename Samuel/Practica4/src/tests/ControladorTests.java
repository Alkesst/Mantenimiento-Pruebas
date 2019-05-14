package tests;

import gui.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class ControladorTests {
    private ActionListener c;
    private Vista v = Mockito.mock(Vista.class);
    private Agenda a = Mockito.mock(Agenda.class);
    private ActionEvent ev = Mockito.mock(ActionEvent.class);

    @BeforeEach
    public void pretest() {
        v = Mockito.mock(Vista.class);
        a = Mockito.mock(Agenda.class);
        ev = Mockito.mock(ActionEvent.class);
        c = new Controlador(v, a);
    }

    // Add Tests
    @Test
    public void addEventoValidoNoExiste() {
        when(ev.getActionCommand()).thenReturn(Vista.ADD);
        when(v.getEvent()).thenReturn(new Evento("prueba", "05/11/2015", "05/11/2016", "Prueba 1"));
        when(a.insertaEvent(any())).thenReturn(true);
        c.actionPerformed(ev);
        verify(v).message("Evento insertado");
        verify(a).insertaEvent(any());
        verify(v, times(2)).show(anyString());
    }

    @Test
    public void addEventoValidoExiste() {
        when(ev.getActionCommand()).thenReturn(Vista.ADD);
        when(v.getEvent()).thenReturn(new Evento("prueba", "05/11/2015", "05/11/2016", "Prueba 1"));
        when(a.insertaEvent(any())).thenReturn(false);
        c.actionPerformed(ev);
        verify(v).message("Evento ya existe");
        verify(a).insertaEvent(any());
        verify(v, times(2)).show(anyString());
    }

    @Test
    public void addEventoNoValido() {
        when(ev.getActionCommand()).thenReturn(Vista.ADD);
        when(v.getEvent()).thenThrow(new RuntimeException());
        c.actionPerformed(ev);
        verify(v).message("Evento no válido");
    }

    // Remove tests
    @Test
    public void removeEventoValidoNoExiste() {
        when(ev.getActionCommand()).thenReturn(Vista.ERASE);
        when(v.getEvent()).thenReturn(new Evento("prueba", "05/11/2015", "05/11/2016", "Borrado 1"));
        when(a.borraEvent(any())).thenReturn(false);
        c.actionPerformed(ev);
        verify(v).getEvent();
        verify(a).borraEvent(any());
        verify(v).message("Evento no existe");
        verify(v).clearEvent();
        verify(v, times(2)).show(anyString());
    }

    @Test
    public void removeEventoValidoExiste() {
        when(ev.getActionCommand()).thenReturn(Vista.ERASE);
        when(v.getEvent()).thenReturn(new Evento("prueba", "05/11/2015", "05/11/2016", "Borrado 2"));
        when(a.borraEvent(any())).thenReturn(true);
        c.actionPerformed(ev);
        verify(v).getEvent();
        verify(a).borraEvent(any());
        verify(v).message("Evento borrado");
        verify(v).clearEvent();
        verify(v, times(2)).show(anyString());
    }

    @Test
    public void removeEventoNoValido() {
        when(ev.getActionCommand()).thenReturn(Vista.ERASE);
        when(v.getEvent()).thenThrow(RuntimeException.class);
        c.actionPerformed(ev);
        verify(v).message("Evento no válido");
    }

    //Search test
    @Test
    public void searchEventoEncontrado() {
        when(ev.getActionCommand()).thenReturn(Vista.SEARCH);
        when(a.buscaEvent(any())).thenReturn(new Evento("prueba", "05/11/2200", "05/02/2201", "ah, no sabia"));
        when(v.getFields()).thenReturn(new HashMap<>());
        c.actionPerformed(ev);
        verify(v).getFields();
        verify(a).buscaEvent(any());
        verify(v, times(2)).show(anyString());
    }

    @Test
    public void searchEventoNoEncontrado() {
        when(ev.getActionCommand()).thenReturn(Vista.SEARCH);
        when(a.buscaEvent(any())).thenReturn(null);
        when(v.getFields()).thenReturn(new HashMap<>());
        c.actionPerformed(ev);
        verify(v).getFields();
        verify(a).buscaEvent(any());
        verify(v).message("Evento no encontrado");
        verify(v, times(2)).show("");
    }

    // Reset test
    @Test
    public void reset() {
        when(ev.getActionCommand()).thenReturn(Vista.RESET);
        c.actionPerformed(ev);
        verify(v, times(2)).show("");
        verify(v).clearEvent();
        verify(a).reset();
        verify(v).message("Calendario reseteado");
    }
}
