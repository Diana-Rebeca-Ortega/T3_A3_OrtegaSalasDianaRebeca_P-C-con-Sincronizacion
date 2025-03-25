
public class SynchronizedBuffer implements Buffer{
	private int buffer = -1; // shared by producer and consumer threads
	 private boolean occupied = false;
	 
	 @Override
	 public synchronized void blockingPut(int value) throws InterruptedException {
		 while (occupied) {
			 System.out.println("Producer tries to write."); // for demo only
			 displayState("Buffer full. Producer waits.");
			 wait();
		 }
		 buffer = value;
		 occupied = true;
		 displayState("Producer writes " + buffer);
		 notifyAll();
	 }
	 
	 @Override
	 public synchronized int blockingGet () throws InterruptedException {
		 while (!occupied) {
			 System.out.println("Consumer tries to read."); // for demo only
			  displayState("Buffer empty. Consumer waits.");
			  wait();
		 }
		 occupied = false;
		 displayState("Consumer reads " + buffer);
		 notifyAll();
		 return buffer;
	 }
	 private void displayState(String operation) {
		 System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, occupied);
	 }
	 
}