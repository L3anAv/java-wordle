package Juego;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class wordleTest {

	private Wordle wordle;
	private String palabraSecreta;

	@Before
	public void wordleInitial() 
	{
		wordle = new Wordle();
	}
	
	@Test
	public void testGanadoTrue() 
	{
		String palabraSecreta = wordle.getpalabraSecretaElegida();
		assertTrue(wordle.ganado(palabraSecreta));
	}
	
	@Test
	public void testGanadoFalse() 
	{
		assertFalse(wordle.ganado("Palabra"));
	}
	
	@Test
	public void testestaCharEnMismaPosEnPalabraSecretaTrue() 
	{
		palabraSecreta = wordle.getpalabraSecretaElegida();
		char caracterPrimero = palabraSecreta.charAt(0);
		assertTrue(wordle.estaCharEnMismaPosEnPalabraSecreta(caracterPrimero, 0));
		
	}
	
	@Test
	public void testestaCharEnMismaPosEnPalabraSecretaFalse() 
	{
		palabraSecreta = wordle.getpalabraSecretaElegida();
		char caracterPrimero = palabraSecreta.charAt(0);
		assertFalse(wordle.estaCharEnMismaPosEnPalabraSecreta(caracterPrimero, 3));
		
	}
	
	
}
