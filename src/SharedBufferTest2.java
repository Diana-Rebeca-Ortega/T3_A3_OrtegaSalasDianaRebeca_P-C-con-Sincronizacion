import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedBufferTest2 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("***P/C Con Sincronizacion***");
		// create a newCachedThreadPool
		/* (grupo de hilos en caché).

		 * Este método crea un nuevo grupo de hilos que se pueden reutilizar para ejecutar tareas
		 * 
		 * Esto significa que:

- Cuando se ejecuta una tarea, se utiliza un hilo existente en el grupo si está disponible.
- Si no hay hilos disponibles, se crea un nuevo hilo en el grupo.
- Cuando una tarea se completa, el hilo se devuelve al grupo para que se pueda reutilizar.

		 */
		ExecutorService executorService = Executors.newCachedThreadPool();

		Buffer sharedLocation = new SynchronizedBuffer();

		System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operacion",
				"Buffer", "Ocupado", "---------", "------\t\t--------");

		executorService.execute(new Productor(sharedLocation));
		executorService.execute(new Consumidor(sharedLocation));

		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);//espera a que todas las tareas se completen en un maximo de 1 minuto
	}
}
