
public interface Buffer{//El tanque de gasolina
	//Se declara throws para no tener que modificar esta interfaz en los ejemplos posteriores 
	
	/*EL BUFFER DEBE TENER LOS 2 SIGUIENTES METODOS YA QUE SE UTILIZAN PARA IMPLEMENTAR UN BUFFER QUE
	 * PUEDE MANEJAR UNA CANTIDAD FIJA DE ELEMENTOS
	 * 
	 * 
	 * blockingPut: 
	 *    Es bloqueante, es decir si el buffer esta lleno, el hilo que llama a este metodo se bloqueara hasta que 
	 *    haya espacio disponible en el buffer //Cuando invocamos al hilo-PRODUCTOR
	 *    
	 * blockingGet: 
	 *    Es bloqueante pero con la diferencia de que si el buffer esta vacio, este se bloqueara hasta que haya un elemento
	 *    disponible en el buffer. 
	 *    Es para que se bloquee el hilo_CONSUMIDOR  cuando el buffer esta vacio haci no lanza exepciones o valeres nullos
	 *    
	 * 
	 */
	public void blockingPut (int elemento) throws  InterruptedException;
	public int blockingGet() throws InterruptedException;

}