package Concurrencia_Ejemplos;



//PARA LANZAR ILOS SE HACE DESDE OBJETOS QUE HEREDAN DE THREAD
// CON STARD SE LANZA LOS HILOS
//LOS HILOS NO SE TIENE QUE EJECUTAR CUANDO SE LANZAN
//NO SABEMOS EL ORDEN DE EJECUCION DE LOS HILOS
public class Principal extends Thread{
   private int id;
   
   
	public Principal(int id) 
	{
		this.id = id;
	}
	
	
	public void run()
	{
		//INSTRUCCIONES QUE EJECUTA EL NUEVO HILO O SECUNDARIO
		System.out.println("SOY EL HILO "+id);
	}
	
	public static void main(String[] args)
	{
	Principal h1 = new Principal(1);
	Principal h2 = new Principal(2);
	Principal h3 = new Principal(3);

	//LANZANDO LOS HILOS
	h1.start();
	h2.start();
	h3.start();
	System.out.println("SOY EL HILO PRINCIPAL");
	// EL HILO PRINCIPAL ES EL QUE SE EJECUTA EN EL MAIN
	//ESTE CONTINUA LAS INSTRUCIONES
	
	}


public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
}
