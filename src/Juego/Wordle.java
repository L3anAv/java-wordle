package Juego;

import java.util.Random;
import java.util.HashMap;


public class Wordle {
	
	private char[] charsDePalabraSecreta;	// > Array con chars de la palabra
	private String palabraSecretaElegida; 	// > Palabra Secreta
	private HashMap<Character, Integer> repeticionesDeChars; // > Diccionario con char repetidos las veces que lo estan en palabra < 
	
		
	/**
	 * Constructor Wordle
	 * */
	public Wordle(){
		String[] palabras = {"busto", "burro", "burla", "bucal", "bueno", "dulce", "salud", "bingo", "menta", "drama",
							 "fumar", "freno", "guiso", "gusto", "guapo", "huevo", "hotel", "jugar", "jarra", "garra"};
		
		
		// > Inciando lo necesario para el juego.
		int numeroAleatorio = darUnNumeroParaSeleccionPalabra(palabras.length);
		palabraSecretaElegida = palabras[numeroAleatorio].toLowerCase();
		
		// > Rellenar el array con los chars de palabra secreta elegida.
		charsDePalabraSecreta = arrayDeCharsDePalabraSecreta(palabraSecretaElegida);
		
	}
	
	// > Declara si se gano el juego o no
	public boolean ganado(String palabraUsuario) 
	{
		
		if(palabraUsuario.equals(palabraSecretaElegida)) 
			return true;
		
		return false;
	}
	
	// > Da un numero aleatorio entre 0 y cantidad de palabras del array palabras.
	private int darUnNumeroParaSeleccionPalabra(int cantidadDePalabras) 
	{
		
		Random random = new Random();
		int numero = random.nextInt(cantidadDePalabras+1);
		
		return numero;
	}
	
	// > Crea un array que contiene en cada posicion un char de la palabraSecretaElegida
	private char[] arrayDeCharsDePalabraSecreta(String palabra) 
	{
		char[] caracteres = new char[5];
		
		for(int i = 0; i < 5;i++)
			caracteres[i] = palabra.charAt(i);
		
		return caracteres;
	}
	
	// > Rellena el diccionario con chars repetidos si los hay y cuantas veces.
	private HashMap<Character,Integer> darMapConCharRepetidos()
	{
		// BORRAR
		return null;
	}
	
	// > Comprueba si char c esta en la misma posicion que en la palabraSecretaElegida
	public boolean estaCharEnMismaPosEnPalabraSecreta(char c, int posicion) 
	{
		boolean existeCharEnMismaPos = false;
		
		if(charsDePalabraSecreta[posicion] == c)
			existeCharEnMismaPos = true;
		
		return existeCharEnMismaPos;
	}
	
		
	// > Tener en cuenta cuantas veces esta repetida esa letra en la palabra, se podria dar que existe 
	// y marcar de  amarillo una letra que no existe tantas veces en la palabraSecretaElegida.

	// > Si char pertenece a la palabra sin importa la posicion.
	public boolean existeCharEnPalabraSecreta(char c)
	{
		int i = 0;
		boolean existeCharEnPalabraSecreta = false;
		
		while(!existeCharEnPalabraSecreta && i < 5) 
		{
			if(charsDePalabraSecreta[i] == c)
				existeCharEnPalabraSecreta = true; 
			i += 1;
		}
		
		return existeCharEnPalabraSecreta;
	}
	
	// > Getters y Setters
	public void setpalabraSecretaElegida(String palabra) {
		this.palabraSecretaElegida = palabra;
	}
	
	public String getpalabraSecretaElegida() {
		return this.palabraSecretaElegida;
	}
	
	
}
