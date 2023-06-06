package Indeterminacion_Solucion;

import java.util.Random;
//REALIZAR LA MULTIPLICACION DE TODOS LOS ELEMENTOS DE UN VECTOR DE ENTEROS POR 10.

//ESTA ESTRATEGIA ES LA MAS EFICIENTE PARA RESOLVER UN PROBLEMA DE INDETERMINISMO
//SE SUELE  USAR PARA EL RENDERIZADO DE IMAGENES 
public class Principal extends Thread
{
	private static int tam = 20;
	private static int[] vec = new int[tam];
	private int ini, fin;
	
	
 public void run()
 {
	for(int i=ini;i<fin;i++)//INI=0, FIN=4 
	{
		vec[i]*=10;
	}
 }
	public static void main(String[] args) 
	{
		Random rand = new Random(System.nanoTime());
		
		for(int i=0; i<vec.length;i++)
		{
			vec[i] = rand.nextInt(10);
		}
		Principal h1 = new Principal(0,5);//INI= 0  FIN = 5  RANGO DONDE OPERA EL HILO 1
		Principal h2 = new Principal(5,10);
		Principal h3 = new Principal(10,15);
		Principal h4 = new Principal(15,20);
		
		//LANZANDO LOS HILOS
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		
		try {
			h1.join();
			h2.join();
			h3.join();
			h4.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		for (int i = 0;i<vec.length;i++)
		{
			System.out.print(vec[i]+ " ");
		}

	}
	public Principal(int ini, int fin) 
	{
		this.ini = ini;
		this.fin = fin;
	}

}
