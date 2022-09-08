package Juego;

import java.util.Random;

public class Wordle {
	
	//Array con char repetidos y veces que estan repetidas en palabraSecreta.
	//Array con char y veces que fueron procesados, para verificar que existan estos repetidos en la palabra. Dinamico. EVALUAR SI ACA O EN INTERFAZ.
	private char[] charsDePalabraSecreta;
	private String palabraSecretaElegida; //palabra Secreta.
		
	/**
	 * Constructor Wordle
	 * */
	public Wordle(){
		String[] palabras = {"busto", "burro", "burla", "bucal", "bueno", "dulce", "salud", "bingo", "menta", "drama",
							 "fumar", "freno", "guiso", "gusto", "guapo", "huevo", "hotel", "jugar", "jarra", "garra"};
		
		
		//Inciando lo necesario para el juego
		int numeroAleatorio = darUnNumeroParaSeleccionPalabra(palabras.length);
		palabraSecretaElegida = palabras[numeroAleatorio].toLowerCase();
		
		//Obtener el array de chars de palabra secreta
		charsDePalabraSecreta = arrayDeCharsDePalabraSecreta(palabraSecretaElegida);
		
	}
	
	
	//Declara si se gano el juego o no
	public boolean ganado(String palabraUsuario) 
	{
		
		if(palabraUsuario.equals(palabraSecretaElegida)) 
			return true;
		
		return false;
	}
	
	//Da un numero aleatorio entre 0 y cantidad de palabras del array palabras.
	private int darUnNumeroParaSeleccionPalabra(int cantidadDePalabras) 
	{
		
		Random random = new Random();
		int numero = random.nextInt(cantidadDePalabras+1);
		
		return numero;
	}
	
	//Crea un array que contiene en cada posicion un char de la palabraSecretaElegida
	public char[] arrayDeCharsDePalabraSecreta(String palabra) 
	{
		char[] caracteres = new char[5];
		
		for(int i = 0; i < 5;i++) 
		{
			caracteres[i] = palabra.charAt(i);
		}
		
		return caracteres;
	}
	
	
	//Si char esta en la misma posicion que en la palabraSecreta
	public boolean estaCharEnMismaPosEnPalabraSecreta(char c, int posicion) 
	{
		boolean existeCharEnMismaPos = false;
		
		for(int i = 0; i < charsDePalabraSecreta.length; i++) 
		{
			if(charsDePalabraSecreta[i] == c && posicion == i) 
				existeCharEnMismaPos = true;
		}
		
		return existeCharEnMismaPos;
	}
	
	//Si char pertenece a la palabra
	public void existeCharEnPalabraSecreta() 
	{
		
	}
	
	public void setpalabraSecretaElegida(String palabra) {
		this.palabraSecretaElegida = palabra;
	}
	
	public String getpalabraSecretaElegida() {
		return this.palabraSecretaElegida;
	}
	
	
}
