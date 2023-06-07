package simulaciono_Carrera;

import java.util.concurrent.CyclicBarrier;

public class Carrera {
	 public static int distancia = 100; // Distancia de la carrera

	    public static void main(String[] args) {
	        int numCorredores = 3; // Cantidad de corredores

	        CyclicBarrier barrera = new CyclicBarrier(numCorredores);

	        Corredor[] corredores = new Corredor[numCorredores];

	        // Crear corredores
	        for (int i = 0; i < numCorredores; i++) {
	            corredores[i] = new Corredor("Corredor " + (i + 1), barrera);
	        }

	        // Iniciar la carrera
	        for (Corredor corredor : corredores) {
	            corredor.start();
	        }

	        // Esperar a que todos los corredores terminen
	        for (Corredor corredor : corredores) {
	            try {
	                corredor.join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        System.out.println("Carrera finalizada.");
	    }
}
