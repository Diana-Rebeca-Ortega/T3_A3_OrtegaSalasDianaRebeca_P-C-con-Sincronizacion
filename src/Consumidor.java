

import java.security.SecureRandom;

public class Consumidor implements Runnable {
	/*
     1. *private*: El modificador de acceso private indica que el campo generator solo puede ser accedido dentro de la clase Consumidor.

     2. *static*: El modificador static indica que el campo generator es compartido por todas las instancias de la clase Consumidor.

     3. *final*: El modificador final indica que el campo generator no puede ser modificado una vez que se ha inicializado.

     4. *SecureRandom*: La clase SecureRandom es una clase de Java que proporciona un generador de números aleatorios seguros.
         Es más seguro que la clase Random porque utiliza un algoritmo de generación de números aleatorios más robusto.
	 */
	private static final SecureRandom generator = new SecureRandom();
	private final Buffer sharedLocation; // El sharredLcacion es un objeto de tipo buffer

	// constructor
	public Consumidor(Buffer sharedLocation) {//es para que los hilos puedan acceder y modificar el buffer
		this.sharedLocation = sharedLocation;
	}

	//Lea el valor de sharedLocation 10 veces y sume los valores
	@Override
	public void run() {
		int sum = 0;

		for (int i = 1; i <= 10; i++) {
			// sleep 0 to 3 seconds, read value from buffer and add to sum
			try {
				Thread.sleep(generator.nextInt(3000));
				sum += sharedLocation.blockingGet();

				/*
				 * *%n*: Salto de línea.
				 *%s*: Cadena de texto.
				 *%d*: Número entero.
				 *%n*: Salto de línea.
				 *%s*: Cadena de texto.
				 *%n*: Salto de línea.
				 *%2d*: Formato de número entero con un mínimo de 2 dígitos.
				
				 */
				System.out.println("-----------------------------C:El numero de aqui abajo se consumio ");
				System.out.printf("\t\t\t%2d%n", sum);
			}
			catch (InterruptedException exepcion) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.printf("%n%s %d%n%s%n","Valores leidos por el consumidor totalizando ", sum, "---Terminacion del consumidor---");
	}

}
