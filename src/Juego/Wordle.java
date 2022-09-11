package Juego;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Wordle {
	
	private char[] charsDePalabraSecreta;	// > Array con chars de la palabra
	private String palabraSecretaElegida; 	// > Palabra Secreta
	private Map<Character, Integer> repeticionesDeChars; // > Diccionario con char repetidos las veces que lo estan en palabra < 
	
	
	/**
	 * > caso de evaluacion
	 * "anana" 
	 * "lacra"
	 * 
	 * 
	 * Constructor Wordle
	 * */
	public Wordle(){
		String[] palabras = {"busto", "burro", "burla", "bucal", "bueno", "dulce", "salud", "bingo", "menta", "drama",
							 "fumar", "freno", "guiso", "gusto", "guapo", "huevo", "hotel", "jugar", "jarra", "garra"};
		
		
		// > Inciando lo necesario para el juego.
		int numeroAleatorio = darUnNumeroParaSeleccionPalabra(palabras.length-1);
		palabraSecretaElegida = palabras[numeroAleatorio].toLowerCase();
		
		// > Rellenar el array con los chars de palabra secreta elegida.
		charsDePalabraSecreta = arrayDeCharsDePalabraSecreta(palabraSecretaElegida);
		
		// > Rellenar el HashMap si hay chars repetidos en la palabra.
		repeticionesDeChars = rellenarDiccionarConCharRepetidos();
		//System.out.println("\n" + repeticionesDeChars.toString());
		
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
	// > Sin test
	private char[] arrayDeCharsDePalabraSecreta(String palabra) 
	{
		char[] caracteres = new char[5];
		
		for(int i = 0; i < 5;i++)
			caracteres[i] = palabra.charAt(i);
		
		return caracteres;
	}
	
	// > Rellena el diccionario con chars repetidos de palabraSecretaElegida si los hay y cuantas veces.
	// > Sin test
	
	private Map<Character, Integer> rellenarDiccionarConCharRepetidos() {
		
		Map<Character, Integer> repeticionesDeChars = new HashMap<>();
		Character caracter;
		Integer numeroDeVeces;
		
		for(int i = 0; i < charsDePalabraSecreta.length;i++) 
		{
			
			if(estaRepetidoChar(charsDePalabraSecreta[i])) 
			{
				
				caracter = charsDePalabraSecreta[i];
				numeroDeVeces = cantidadDeVecesCharRepetido(caracter);
				
				System.out.println(caracter + " " + numeroDeVeces + "\n");
				repeticionesDeChars.put(caracter, numeroDeVeces);
				
			}
		}
		
		//repeticionesDeChars.put('c', 12);
		
		return repeticionesDeChars;
		
	}
	
	
	// > Devuelve true si hay char repetidos en la palabra
	// > Sin test
	private boolean estaRepetidoChar(char c) 
	{
		
		boolean estaRepetidoChar = false;
		
		if(cantidadDeVecesCharRepetido(c) >= 2)
			estaRepetidoChar = true;

		return estaRepetidoChar;
	}
	
	// > Devuelve la cantidad de veces que el char esta repetido
	// > Sin test
	private int cantidadDeVecesCharRepetido(char c) 
	{
		int cantidadDeVeces = 0;
		
		for(int i = 0; i < charsDePalabraSecreta.length; i++)
			if(charsDePalabraSecreta[i] == c)
				cantidadDeVeces++;
		
		return cantidadDeVeces;
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
