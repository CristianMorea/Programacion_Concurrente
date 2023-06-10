package Monitores;
//QUE SON LOS MONITORES?
/*
 * son clases las cuales todos sus metodos publicos tinen que ser de tipo siincronizet
 * un monitor provee esclucion mutua a cada uno de los metodos
 * si hay un metodo y un hilo dentra al metodo no podran entrar mas hilos
 * hasta que este termine
 * 
 */


public class monitor 
{
	private int cont =0;
	


	public synchronized int inc() 
	{
		for(int i=0;i<20000;i++)
		{
			 cont++;
		}
		return cont;
		
	}
	
	
	
	
}
