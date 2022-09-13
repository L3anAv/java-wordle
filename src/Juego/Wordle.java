package Juego;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Wordle {
	
	private int intentosJugados;
	private int cantidadDeIntentosTotales;
	private char[] charsDePalabraSecreta;	// > Array con chars de la palabra
	private String palabraSecretaElegida; 	// > Palabra Secreta
	private Map<Character, Integer> repeticionesDeChars; // > Diccionario con char repetidos las veces que lo estan en palabra < 
	
	
	 // > Constructor Wordle
	
	public Wordle(int cantidadDeIntentos){
		
		String[] palabras = {"busto", "burro", "burla", "bucal", "bueno", "dulce", "salud", "bingo", "menta", "drama",
							 "fumar", "freno", "guiso", "gusto", "guapo", "huevo", "hotel", "jugar", "jarra", "garra"};
		
		
		// Inciando lo necesario para el juego.
		int numeroAleatorio = darUnNumeroParaSeleccionPalabra(palabras.length-1);
		palabraSecretaElegida = palabras[numeroAleatorio].toLowerCase();
	
		// Rellenar el array con los chars de palabra secreta elegida.
		charsDePalabraSecreta = arrayDeCharsDePalabraSecreta(palabraSecretaElegida);
		
		// Rellenar el HashMap si hay chars repetidos en la palabra.
		repeticionesDeChars = rellenarDiccionarConCharRepetidos();
		
		// Seteando la cantidad de intentos
		intentosJugados = 0;
		
		// Cantidad de intentos Totales
		this.cantidadDeIntentosTotales = cantidadDeIntentos;
		
	}
	
	// > Metodo que retorna si se gano el juego
	public boolean ganado(String palabraUsuario) 
	{
		
		if(palabraUsuario.equals(palabraSecretaElegida) && quedanIntentos()) 
			return true;
		else
			return false;
	}
	
	// > Metodo que controla si quedan intentos disponibles
	public boolean quedanIntentos() 
	{
		if(intentosJugados == cantidadDeIntentosTotales)
			return false;
		
		return true;
	}
	
	// Metodo que suma un intento a los intentos hechos por el jugador
	public void sumarUnIntento()
	{
		intentosJugados++;
	}
	
	// Obtiene la cantidad de intentos del jugador
	public int obtenerCantidadDeIntentos() 
	{
		return intentosJugados;
	}
	
	// > Metodo que da un numero aleatorio entre 0 y cantidad total del array de palabras para elegir del juego
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
	
	
	// > Metodo que Comprueba si el char pasado está en la misma posicion que en la palabraSecretaElegida
	public boolean estaCharEnMismaPosEnPalabraSecreta(char c, int posicion) 
	{
		boolean existeCharEnMismaPos = false;
		
		if(charsDePalabraSecreta[posicion] == c)
			existeCharEnMismaPos = true;
		
		return existeCharEnMismaPos;
	}

	
	// > Metodo que dice si char pertenece a la palabra sin tener en cuenta la posicion.
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
	
	
	// > Getters && Setter de: Palabra Secreta Elegida
	public void setpalabraSecretaElegida(String palabra) {
		this.palabraSecretaElegida = palabra;
	}
	
	public String getpalabraSecretaElegida() {
		return this.palabraSecretaElegida;
	}
	
	
}
