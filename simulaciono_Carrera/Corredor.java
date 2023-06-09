package simulaciono_Carrera;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Corredor extends Thread {
    private String nombre;
    private int posicion;
    private CyclicBarrier barrera;

    public Corredor(String nombre, CyclicBarrier barrera) {
        this.nombre = nombre;
        this.posicion = 0;
        this.barrera = barrera;
    }

    @Override
    public void run() {
        try {
            System.out.println(nombre + " ha comenzado la carrera.");
            // Avance del corredor
            while (posicion < Carrera.distancia) {
                // Simulación de avance
            	Thread.sleep(100);
                posicion++;
                System.out.println(nombre + " ha avanzado a la posición " + posicion);
            }

            // Esperar a que todos los corredores terminen
            barrera.await();

            // Mostrar orden de llegada
            System.out.println(nombre + " ha terminado la carrera.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
