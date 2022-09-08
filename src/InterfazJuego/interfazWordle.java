package InterfazJuego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import Juego.Wordle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class interfazWordle {

	private JFrame frame;
	private String palabra;
	private StringBuilder palabraCompleta;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public interfazWordle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Inicializando Frame
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(36, 31, 49));
		frame.setBounds(100, 100, 455, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Inicializando StringBuilder
		palabraCompleta = new StringBuilder();
		
		//Instanciando el juego
		Wordle wordle = new Wordle();
	
// --> Cuadros para ingresar texto

/** Colores segun corresponda de fondo para fields: 		
* VERDE: new Color(96, 181, 90
* AMARILLO: new Color(219, 193, 44)
	**/
		
		//Variables comunes
		int localizacionXFila = 115;
		
		//Parametros para la primer fila de fields
		int localizacionYFila1 = 38;
		JTextField[] cuadrosDeTextoFila1 = new JTextField[5];
		
		//Parametros para la segunda fila de fields
		int localizacionYFila2 = 78;
		JTextField[] cuadrosDeTextoFila2 = new JTextField[5];
		
		//Parametros para la tecera fila de fields
		int localizacionYFila3 = 118;
		JTextField[] cuadrosDeTextoFila3 = new JTextField[5];
		
		//Parametros para la cuarta fila de fields
		int localizacionYFila4 = 158;
		JTextField[] cuadrosDeTextoFila4 = new JTextField[5];
		
		//Parametros para la quinta fila de fields
		int localizacionYFila5 = 198;
		JTextField[] cuadrosDeTextoFila5 = new JTextField[5];
		
		//Inicializando JTextField
		insertarFieldsEnInterfaz(cuadrosDeTextoFila1, localizacionXFila, localizacionYFila1);
		insertarFieldsEnInterfaz(cuadrosDeTextoFila2, localizacionXFila, localizacionYFila2);
		insertarFieldsEnInterfaz(cuadrosDeTextoFila3, localizacionXFila, localizacionYFila3);
		insertarFieldsEnInterfaz(cuadrosDeTextoFila4, localizacionXFila, localizacionYFila4);
		insertarFieldsEnInterfaz(cuadrosDeTextoFila5, localizacionXFila, localizacionYFila5);
		
		//Inicializando boton
		JButton btnNewButton = new JButton("INTENTAR");
		btnNewButton.setBounds(118, 256, 210, 34);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		
		
		//Evento boton
		
		
		//Ingresando boton en frame
		frame.getContentPane().add(btnNewButton);
		
	}

	
	//Funcion que obtiene la palabra caracter a caracter y la concateca con un StringBuilder
	private String obtenerPalabraCompleta(char caracterIngresado, int index)
	{
		
		// Si la palabra tiene 5 caracteres empieza a reemplazar en la pos correspondiente
		// si no concatena.
		
		if(palabraCompleta.length() == 5) 
			palabraCompleta.setCharAt(index, caracterIngresado);
		else 
			palabraCompleta.append(caracterIngresado);
		
		return palabraCompleta.toString().toLowerCase();
	}
	
	
//	//Funcion que pasa la comprobacion de la palabra ingresada
//	private void comprobarPalabra() {
//		// Llamado a clase Wordle
//	}
	
	//Funcion para insertar campos de texto en interfaz
	private void insertarFieldsEnInterfaz(JTextField[] cuadrosDeTexto, int localizacionX, int localizacionY) {
		
		//Ciclo que recorre arrreglo de textField
		for(int i = 0; i < cuadrosDeTexto.length; i++)
		{
			//Instancia de JTextFields
			cuadrosDeTexto[i] = new JTextField();
			cuadrosDeTexto[i].setColumns(0);
			
			// Position
			cuadrosDeTexto[i].setBounds(localizacionX, localizacionY, 30, 30);
			cuadrosDeTexto[i].setHorizontalAlignment(JTextField.CENTER);
			
			// Styles
			cuadrosDeTexto[i].setBackground(new Color(36, 31, 49));
			cuadrosDeTexto[i].setForeground(new Color(255, 255, 255));
			cuadrosDeTexto[i].setBorder(new LineBorder(Color.WHITE, 1));
			cuadrosDeTexto[i].setFont(new Font("Dialog", Font.BOLD, 14));

			//Variables para eventos de Fields
			int posicion = i;
			localizacionX += 46;
			
			//Eventos de Fiedls
			
/** Consultar profes: Si escribo 
 * cuando ya hay algo me lo capta para
 * la palabra y me lo agrega en la ultima posicion. 
 * Esto solo presiono cuando ya hay alguna letra. Como la reemplazo?
 **/
			cuadrosDeTexto[i].addKeyListener(new KeyAdapter(){
				
				@Override
				public void keyTyped(KeyEvent e) 
				{
					
					if(cuadrosDeTexto[posicion].getText().length() >= 1)
						e.consume();
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) 
				{
					
					//Poniendo en mayuscula el texto ingresado en el field
					String textUpper = cuadrosDeTexto[posicion].getText().toUpperCase();
					cuadrosDeTexto[posicion].setText(textUpper);
					
					//Capturando el caracter ingresado en el field
					if(cuadrosDeTexto[posicion].getText().length() >= 1) 
					{
						palabra = obtenerPalabraCompleta(e.getKeyChar(), posicion);
						
					
					}
					
					//Moviendo automaticamente al siguiente fild despues de escribir
					if(cuadrosDeTexto[posicion].getText().length() != 0 && posicion < 4)
						cuadrosDeTexto[posicion+1].requestFocusInWindow();
					else if(posicion == 5)
						cuadrosDeTexto[5].requestFocusInWindow();
					
					//Si palabra == 5 de lenght, llamar a la comprobacion de la palabra con un boton
					
				}
				
			});
			
			frame.getContentPane().add(cuadrosDeTexto[i]);
		}
	}
}
