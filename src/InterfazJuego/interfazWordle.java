package InterfazJuego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import Juego.Wordle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class interfazWordle {

	
	private JFrame frame;
	private int intentosTotales;
	private JLabel textoGanaste;
	private JLabel perdisteTexto;
	private JButton botonIngresar;
	private String palabraCompleta;
	private StringBuilder palabraObtenida;
	private ArrayList<JTextField[]> arrayListDeJTextFields;
	
	
	// > Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazWordle window = new interfazWordle();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	// > Create the application.
	 
	public interfazWordle() {
		initialize();
	}

	
	// > Initialize the contents of the frame.
	 
	private void initialize() {
		
		// Inicializando Frame
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(36, 31, 49));
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Intentos para juego
		intentosTotales = 6;
		
		// Instanciando el Wordle
		Wordle wordle = new Wordle(intentosTotales);
		
		// Inicializando StringBuilder
		palabraObtenida = new StringBuilder();
 		
		//Palabra elegida por Wordle.
		//System.out.println(wordle.getpalabraSecretaElegida());
		
		// JLabel de Ganaste
		textoGanaste = new JLabel("Ganaste!");
		textoGanaste.setFont(new Font("Dialog", Font.PLAIN, 14));
		textoGanaste.setForeground(new Color(255, 255, 255));
		textoGanaste.setBounds(194, 275, 100, 17);
		textoGanaste.setVisible(false);
		
		// JLabel de Perdiste
		String textoPerdiste = "Perdiste! :( La palabra era: " + wordle.getpalabraSecretaElegida().toUpperCase();
		int lenghtTexto = textoPerdiste.length();
		perdisteTexto = new JLabel(textoPerdiste);
		perdisteTexto.setFont(new Font("Dialog", Font.PLAIN, 12));
		perdisteTexto.setForeground(new Color(255, 255, 255));
		perdisteTexto.setBounds(170-lenghtTexto, 275, 320+lenghtTexto, 17);
		perdisteTexto.setVisible(false);
		
		// Creando boton para interfaz
		botonIngresar = crearBotonParaInterfaz(wordle, textoGanaste, perdisteTexto);
		
		// Insertar Fields en ArrayList que contiene arrays de fields
 		arrayListDeJTextFields = crearFieldsDeInterfaz(intentosTotales);
		
		// Localizacion de fields en interfaz
		int localizacionYFila = 38;
		int localizacionXFila = 115;
		
		// Insertando en intefaz cada fila de JTextFields
		insertarFieldsEnInterfaz(arrayListDeJTextFields, localizacionXFila, localizacionYFila, botonIngresar);
		
		// Ingresando elementos en la interfaz
		frame.getContentPane().add(botonIngresar);
		frame.getContentPane().add(textoGanaste);
		frame.getContentPane().add(perdisteTexto);
		
		
	}
	
	
	// > Metodo que setea los fields todos en verde, cuando se gana el juego.
	private void interfazHasGanado(JTextField[] cuadrosDeTextoFila)
	{
		for(int i = 0; i < palabraCompleta.length(); i ++) 
		{
				cuadrosDeTextoFila[i].setBackground(new Color(96, 181, 90));
		}
		
	}
	
	// > Metodo que crea y agrega evento corespondiente a boton
	private JButton crearBotonParaInterfaz(Wordle wordle, JLabel ganasteTexto, JLabel perdisteTexto) 
	{
		
		// Incializando boton
		JButton botonIntentar = new JButton("INTENTAR");
		botonIntentar.setBounds(118, 300, 210, 34);	
		
		// Desabilitando click
		botonIntentar.setEnabled(false);
		
		// Agregando eventos al boton
		botonIntentar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				int intentos = wordle.obtenerCantidadDeIntentos();
				
				if(!wordle.ganado(palabraCompleta) && palabraCompleta.length() == 5) 
				{
					
					JTextField[] cuadrosDeTextoFila = arrayListDeJTextFields.get(intentos);
					
					// Comprobacion de palabra ingresada
					comprobarPalabraIngresada(palabraCompleta, wordle, cuadrosDeTextoFila, botonIntentar);
					
					//Sumando un intento a los intentos del usuario
					wordle.sumarUnIntento();
					
					//Avanzando al siguiente primer JTextField
					if(intentos < 4)
						arrayListDeJTextFields.get(intentos+1)[0].requestFocusInWindow();
					else
						arrayListDeJTextFields.get(5)[0].requestFocusInWindow();
					
					// Resetear palabra ingresada por usuario
					palabraObtenida.setLength(0);
					palabraCompleta = "";
					
				}else
				{
					
					JTextField[] cuadrosDeTextoFila = arrayListDeJTextFields.get(intentos);
					
					// Coloca todos los fields de color verde
					interfazHasGanado(cuadrosDeTextoFila);
					
					// Desabilita el click del boton
					botonIntentar.setEnabled(false);
					
					// Habilita en pantalla el texto de ganaste
					ganasteTexto.setVisible(true);
					
				}
				
				// Controla la cantidad de intentos
				if(intentos == 5 && !wordle.ganado(palabraCompleta)) {
					
					// Habilita en pantalla el texto de perdiste
					perdisteTexto.setVisible(true);
					botonIntentar.setEnabled(false);
					
				}
		
			}
		});
		
		return botonIntentar;
		
	}
	
	// > Metodo que crea los fields de intefaz y los inserta en  un arrayList
	private ArrayList<JTextField[]> crearFieldsDeInterfaz(int intentos) 
	{
		// Inicializacion del arrayList de JTextFields
		 ArrayList<JTextField[]> arrayListDeJTextFields = new ArrayList<JTextField[]>();
		 
		// Rellenando posiciones del arrayListDeJTextFields
		for(int i = 0; i < intentos; i++)
		{
			JTextField[] cuadrosDeTextoFila = new JTextField[5];
			arrayListDeJTextFields.add(cuadrosDeTextoFila);

		}
		
		return arrayListDeJTextFields;
	}
	
	// > Metodo que inserta los fields en la interfaz desde el arrayList de fields
	private void insertarFieldsEnInterfaz(ArrayList<JTextField[]> arrayListDeJTextFields, int localizacionXFila, int localizacionYFila, JButton btnNewButton) 
	{
		
		for(int i = 0; i < arrayListDeJTextFields.size(); i++) 
		{
			JTextField[] cuadrosDeTextoFila = arrayListDeJTextFields.get(i);
	
			colocacionDeFieldsEnInterfaz(cuadrosDeTextoFila, localizacionXFila, localizacionYFila, btnNewButton);
			localizacionYFila += 40;
			
		}
		
	}
	
	// > Metodo que obtiene la palabra caracter a caracter y la concateca en un StringBuilder
	private String obtenerPalabraCompleta(char caracterIngresado, int index)
	{
		
		if(palabraObtenida.length() == 5) 
			palabraObtenida.setCharAt(index, caracterIngresado);
		else 
			palabraObtenida.append(caracterIngresado);
		
		return palabraObtenida.toString().toLowerCase();
		
	}
	
	// > Metodo que compara la palabra ingresada por el usuario con la elegida por Wordle
	private void comprobarPalabraIngresada(String palabraCompleta, Wordle wordle,JTextField[] filaDeJTextFields, JButton botonIntentar)
	{
		
		// Ciclo para la validacion correspondiente
		for(int i = 0; i < 5; i ++) 
		{
			if(wordle.estaCharEnMismaPosEnPalabraSecreta(palabraCompleta.charAt(i), i)) 
			{
				
				filaDeJTextFields[i].setBackground(new Color(96, 181, 90));
				filaDeJTextFields[i].setEnabled(false);
				
			}else if(wordle.existeCharEnPalabraSecreta(palabraCompleta.charAt(i)))	
			{
				filaDeJTextFields[i].setBackground(new Color(219, 193, 44));
				filaDeJTextFields[i].setEnabled(false);
				
			}else 
			{
				
				filaDeJTextFields[i].setBackground(new Color(132, 132, 132));
				filaDeJTextFields[i].setEnabled(false);
				
			}
		}
		
		// Desabilita el boton para el siguiente intento
		botonIntentar.setEnabled(false);
		
		
	}

	// > Metodo para insertar fields en la intefaz del juego
	private void colocacionDeFieldsEnInterfaz(JTextField[] cuadrosDeTexto, int localizacionX, int localizacionY, JButton botonIntentar) 
	{
		
		// Ciclo que recorre arrreglo de textField
		for(int i = 0; i < cuadrosDeTexto.length; i++)
		{
			// Instancia de JTextFields
			cuadrosDeTexto[i] = new JTextField();
			cuadrosDeTexto[i].setColumns(0);
			
			// Posicion de los JTextFields
			cuadrosDeTexto[i].setBounds(localizacionX, localizacionY, 30, 30);
			cuadrosDeTexto[i].setHorizontalAlignment(JTextField.CENTER);
			
			// Estilos correspondientes
			cuadrosDeTexto[i].setBackground(new Color(36, 31, 49));
			cuadrosDeTexto[i].setForeground(new Color(255, 255, 255));
			cuadrosDeTexto[i].setBorder(new LineBorder(Color.WHITE, 1));
			cuadrosDeTexto[i].setFont(new Font("Dialog", Font.BOLD, 14));
			cuadrosDeTexto[i].setCaretColor(Color.YELLOW);

			// Variables para eventos de JTextFields
			int posicion = i;
			localizacionX += 46;
			
			// Eventos de para cada JTextFields
			cuadrosDeTexto[i].addKeyListener(new KeyAdapter(){
				
				@Override
				public void keyTyped(KeyEvent e) 
				{
					// Colocando en interfaz las letras presionadas en cada JTextFields
					if(cuadrosDeTexto[posicion].getText().length() == 0) 
					{
						String charIngresado = cuadrosDeTexto[posicion].getText();
						cuadrosDeTexto[posicion].setText(charIngresado);
					}
					else if(cuadrosDeTexto[posicion].getText().length() == 1) 
					{
						cuadrosDeTexto[posicion].setText("");
						String charIngresado = cuadrosDeTexto[posicion].getText();
						cuadrosDeTexto[posicion].setText(charIngresado);
					}

						
				}
				
				@Override
				public void keyReleased(KeyEvent e) 
				{
					
					// Cambiando a mayuscula el texto ingresado en los JTextFields y comprobando que no se ingresen numeros
					if(cuadrosDeTexto[posicion].getText().length() >= 1 && !Character.isDigit(cuadrosDeTexto[posicion].getText().toCharArray()[0])) {
						String textUpper = cuadrosDeTexto[posicion].getText().toUpperCase();
						cuadrosDeTexto[posicion].setText(textUpper);
					}else{
						cuadrosDeTexto[posicion].setText("");
					}
				
					// Capturando el caracter ingresado en cada JTextField
					if(cuadrosDeTexto[posicion].getText().length() >= 1 && !Character.isDigit(e.getKeyChar()))
					{
						
						palabraCompleta = obtenerPalabraCompleta(e.getKeyChar(), posicion);
						
						if(palabraCompleta.length() == 5)
						{
							setPalabraCompleta(palabraCompleta);
							botonIntentar.setEnabled(true);
						}
					}
					
					// Moviendo automaticamente al siguiente JTextFields despues de escribir
					if(cuadrosDeTexto[posicion].getText().length() != 0 && posicion < 4)
						cuadrosDeTexto[posicion+1].requestFocusInWindow();
					else if(posicion == 5)
						cuadrosDeTexto[5].requestFocusInWindow();
					
					
				}
				
			});
			
			frame.getContentPane().add(cuadrosDeTexto[i]);
		}
	}
	
	// Getters && Setter de: Palabra Completa
	public void setPalabraCompleta(String palabraCompetaObtenida) 
	{
		palabraCompleta = palabraCompetaObtenida;
	}
	
	public String palabraCompleta() 
	{
		return palabraCompleta;
	}
}
