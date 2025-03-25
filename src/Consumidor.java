

	import java.security.SecureRandom;

	public class Consumidor implements Runnable {
		private static final SecureRandom generator = new SecureRandom();
		private final Buffer sharedLocation; // reference to shared object

		// constructor
		public Consumidor(Buffer sharedLocation) {
			this.sharedLocation = sharedLocation;
		}

		//Lea el valor de sharedLocation 10 veces y sume los valores
		@Override
		public void run() {
			int sum = 0;

			for (int count = 1; count <= 10; count++) {
				// sleep 0 to 3 seconds, read value from buffer and add to sum
				try {
					Thread.sleep(generator.nextInt(3000));
					sum += sharedLocation.blockingGet();

					System.out.printf("\t\t\t%2d%n", sum);
				}
				catch (InterruptedException exepcion) {
					Thread.currentThread().interrupt();
				}
			}
			System.out.printf("%n%s %d%n%s%n","Consumer read values totaling", sum, "Terminating Consumer");
		}

	}
