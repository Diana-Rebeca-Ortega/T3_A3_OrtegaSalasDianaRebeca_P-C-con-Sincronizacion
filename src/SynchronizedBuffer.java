/*
 * La clase SynchronizedBuffer implementa la interfaz Buffer y proporciona un
 *  mecanismo de comunicación entre hilos PRODUCTOR y CONSUMIDOR utilizando
 *   un ===>buffer compartido
 */
public class SynchronizedBuffer implements Buffer{
	private int buffer = -1; // Buffer compartido por hilos de productores y consumidores
	 private boolean EstaOcupado_SioNo = false;
	 
	 @Override
	 public synchronized void blockingPut(int value) throws InterruptedException {
		 /* ***************blockingPut synchronized*******************
		  * Este método se utiliza para escribir un valor en el buffer compartido.
		  *  Es sincronizado (synchronized) para garantizar que solo un hilo puede
		  *   acceder al buffer a la vez.

		  */
		 while (EstaOcupado_SioNo) {//Si el buffer está ocupado, el hilo productor espera hasta que se libere.

			 System.out.println("El productor intenta escribir."); // for demo only
			 displayState("Buffer full. El productor espera...");//muestra el estado actual del buffer
			 wait();//El hilo productor se bloquea hasta que se libere el buffer.

		 }
		 buffer = value;//Se escribe el valor en el buffer.

		 EstaOcupado_SioNo = true;
		 displayState("El productor escribe " + buffer);///muestra el estado actual del buffer
		 notifyAll();//Se notifica a todos los hilos que esperan que el buffer se ha liberado.
	 }
	 
	 @Override
	 public synchronized int blockingGet () throws InterruptedException {
		 /* *******************blockingGet synchronized**********************
		  * Este método se utiliza para leer un valor del buffer compartido. 
		  * Es sincronizado (synchronized) para garantizar que solo un hilo puede
		  *  acceder al buffer a la vez.

		  * 
		  */
		 while (!EstaOcupado_SioNo) {//Si el buffer está vacío, el hilo consumidor espera hasta que se llene.
			 System.out.println("El consumidor intenta leer."); // for demo only
			  displayState("Buffer vacío. Consumer esperando...");//muestra el estado actual del buffer
			  wait();//El hilo consumidor se bloquea hasta que se llene el buffer.

		 }
		 EstaOcupado_SioNo = false;
		 displayState("El consumidor lee " + buffer);
		 notifyAll();//Se notifica a todos los hilos que esperan que el buffer se ha liberado.

		 return buffer;//devuelve el valor leido del buffer
	 }
	 private void displayState(String operation) {
		 System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, EstaOcupado_SioNo);
	 }
	 
}