
import java.security.SecureRandom;

public class Productor implements Runnable {
	private static final SecureRandom generator = new SecureRandom();
	private final Buffer sharedLocation;

	// constructor
	public Productor(Buffer sharedLocation) {
		this.sharedLocation = sharedLocation;
	}

	@Override
	public void run() {
		int sum = 0;

		for (int count = 1; count <= 10; count++) {
			try { // sleep 0 to 3 seconds, then place value in Buffer

				Thread.sleep(generator.nextInt(3000)); 
				sharedLocation.blockingPut(count); //Aqui se establece el valor del buffer compartido

				sum += count; 
				System.out.println("_________________________P");
				System.out.printf("\t%2d%n", sum);//total de todos los valores producidos
			}
			catch (InterruptedException exepcion) {
				Thread.currentThread().interrupt();
			}

		}//for
		//Esta es la cadena de texto que se va a imprimir. Contiene dos mensajes separados por saltos de línea (%n).
		System.out.printf( "El productor termino de producir %n ---El productor termino---%n");
	}//run
}//class