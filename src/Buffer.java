
public interface Buffer{//El tanque de gasolina
	//Se declara throws para no tener que modificar esta interfaz en los ejemplos posteriores 
	public void blockingPut (int value) throws  InterruptedException;
	public int blockingGet() throws InterruptedException;

}