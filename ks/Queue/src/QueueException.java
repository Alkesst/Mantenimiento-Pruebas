/*
 * @author: Jesus Parejo Aliaga & Alejandro Garau Madrigal
 */

/**
 * Clase excepcion para la gestion de situaciones excepcionales de Queue
 */
public class QueueException extends RuntimeException {

    public QueueException() {
    }

    public QueueException(String arg0) {
        super(arg0);
    }
}