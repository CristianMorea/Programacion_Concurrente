package a_lanzarHilosHeredandoThread;

public class Contador extends Thread
{
	 private static int contador;

	    public synchronized void incrementarContador() {
	        contador++;
	    }

	    public void run() {
	        for (int i = 0; i < 3; i++) {
	            incrementarContador();
	            System.out.println("SOY EL HILO " + getId() + " con contador " + contador);
	        }
	    }

	    public static void main(String[] args) {
	        Contador[] hilos = new Contador[2];

	        for (int i = 0; i < hilos.length; i++) {
	            hilos[i] = new Contador();
	            hilos[i].start();
	        }

	        // Esperar a que los hilos terminen
	        for (int i = 0; i < hilos.length; i++) {
	            try {
	                hilos[i].join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        System.out.println("Contador final: " + contador);
	    }
	}