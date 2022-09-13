package Juego;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Wordle {
	
	private int intentosJugados;
	private int cantidadDeIntentosTotales;
	private char[] charsDePalabraSecreta;
	private String palabraSecretaElegida; 	
	
	 // > Constructor Wordle
	
	public Wordle(int cantidadDeIntentos){
		
		String[] palabras = {"busto", "burla", "bucal", "bueno", "dulce", "salud", "bingo", "menta", "mucho", "lucha",
							 "fumar", "freno", "guiso", "gusto", "guapo", "huevo", "hotel", "jugar", "recto", "dogma"};
		
		// Inciando lo necesario para el juego.
		int numeroAleatorio = darUnNumeroParaSeleccionPalabra(palabras.length-1);
		palabraSecretaElegida = palabras[numeroAleatorio].toLowerCase();
	
		// Rellenar el array con los chars de palabra secreta elegida.
		charsDePalabraSecreta = arrayDeCharsDePalabraSecreta(palabraSecretaElegida);
		
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
	
	// > Metodo que suma un intento a los intentos hechos por el jugador
	public void sumarUnIntento()
	{
		intentosJugados++;
	}
	
	// > Metodo que obtiene la cantidad de intentos del jugador
	public int obtenerCantidadDeIntentos() 
	{
		return intentosJugados;
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
	
	// > Metodo que da un numero aleatorio entre 0 y cantidad total del array de palabras para elegir del juego
		private int darUnNumeroParaSeleccionPalabra(int cantidadDePalabras) 
		{
			
			Random random = new Random();
			int numero = random.nextInt(cantidadDePalabras+1);
			
			return numero;
		}
		
	// > Crea un array que contiene en cada posicion un char de la palabraSecretaElegida
	// > Sin test hacerlo
	private char[] arrayDeCharsDePalabraSecreta(String palabra) 
	{
			char[] caracteres = new char[5];
		
			for(int i = 0; i < palabra.length();i++)
				caracteres[i] = palabra.charAt(i);
			
			return caracteres;
	}
		
	
	// > Getters && Setter de: Palabra Secreta Elegida
	public void setpalabraSecretaElegida(String palabra) {
		this.palabraSecretaElegida = palabra;
	}
	
	public String getpalabraSecretaElegida() {
		return this.palabraSecretaElegida;
	}
	
	
}
