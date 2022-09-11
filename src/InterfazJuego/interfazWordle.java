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
	private int intentos;
	private String palabraCompleta;
	private StringBuilder palabraObtenida;
	private ArrayList<JTextField[]> arrayListDeJTextFields;
	
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
		
// ---> ARREGLAR QUE SI LE DOY INTENTAR CON LOS FIELDS VACIOS SE ROMPE EL PROGRAMA. SOLUCIONAR. <----
		
		//Inicializando Frame
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(36, 31, 49));
		frame.setBounds(100, 100, 455, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Instanciando el juego.
		Wordle wordle = new Wordle();
		
		//Inicializando StringBuilder.
		palabraObtenida = new StringBuilder();
		
		//Palabra Seleccionado
		System.out.println(wordle.getpalabraSecretaElegida());
 		
		//
		JLabel GanasteTexto = new JLabel("Ganaste!");
		GanasteTexto.setFont(new Font("Dialog", Font.PLAIN, 14));
		GanasteTexto.setForeground(new Color(255, 255, 255));
		GanasteTexto.setBounds(194, 236, 100, 17);
		GanasteTexto.setVisible(false);
		
		//
		JLabel PerdisteTexto = new JLabel("Perdiste!");
		PerdisteTexto.setFont(new Font("Dialog", Font.PLAIN, 14));
		PerdisteTexto.setForeground(new Color(255, 255, 255));
		PerdisteTexto.setBounds(194, 236, 100, 17);
		PerdisteTexto.setVisible(false);
		
// --> Insertando cuadros de texto en la interfaz.
		
		//InsertarFields en ArrayList
 		arrayListDeJTextFields = crearFieldsDeInterfaz();
		
		// > Variables constantes
			int localizacionYFila = 38;
			int localizacionXFila = 115;
		
		// > Insertando en intefaz cada fila de JTextFields --> METODO
		insertarFieldsEnInterfaz(arrayListDeJTextFields, localizacionXFila, localizacionYFila);
		
// --> Boton de la interfaz
		
		// > Incializando boton
		JButton btnNewButton = new JButton("INTENTAR");
		
		// > Seteando propiedades
		btnNewButton.setBounds(118, 256, 210, 34);
		
		// > Seteando intentos
		intentos = 0;
		
	/** Colores segun corresponda de fondo para fields: 		
	* VERDE: new Color(96, 181, 90)
	* AMARILLO: new Color(219, 193, 44)
		**/		
		
		// > Eventos del boton
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println(intentos);
				
				if(!wordle.ganado(palabraCompleta) && palabraCompleta.length() == 5) 
				{
					
					JTextField[] cuadrosDeTextoFila = arrayListDeJTextFields.get(intentos);
					comprobarPalabraIngresada(palabraCompleta, wordle, cuadrosDeTextoFila);
				
				}else
				{
					JTextField[] cuadrosDeTextoFila = arrayListDeJTextFields.get(intentos);
					interfazHasGanado(cuadrosDeTextoFila);
					
					//Ganaste
					GanasteTexto.setVisible(true);
				}
				
				//Control de intentos
				if(intentos < 4)
				{
					intentos++;
				}else {
					PerdisteTexto.setVisible(true);
				}
				
				
			}
		});
		
		//Ingresando boton en frame
		frame.getContentPane().add(btnNewButton);
		
		//Ingresando Label
		frame.getContentPane().add(GanasteTexto);
		frame.getContentPane().add(PerdisteTexto);
	}
	
	// > asdasd
	private void interfazHasGanado(JTextField[] cuadrosDeTextoFila)
	{
		for(int i = 0; i < palabraCompleta.length(); i ++) 
		{
				cuadrosDeTextoFila[i].setBackground(new Color(96, 181, 90));
		}
		
	}
	
	// > asdasd
	private ArrayList<JTextField[]> crearFieldsDeInterfaz() 
	{
		// > Inicializacion del arrayList de JTextFields.
		 ArrayList<JTextField[]> arrayListDeJTextFields = new ArrayList<JTextField[]>();
		 
		// > Rellenando posiciones del arrayListDeJTextFields.
		for(int i = 0; i < 5; i++)
		{
			JTextField[] cuadrosDeTextoFila = new JTextField[5];
			arrayListDeJTextFields.add(cuadrosDeTextoFila);

		}
		
		return arrayListDeJTextFields;
	}
	
	// -->
	private void insertarFieldsEnInterfaz(ArrayList<JTextField[]> arrayListDeJTextFields, int localizacionXFila, int localizacionYFila) 
	{
		
		for(int i = 0; i < arrayListDeJTextFields.size(); i++) 
		{
			JTextField[] cuadrosDeTextoFila = arrayListDeJTextFields.get(i);
	
			colocacionDeFieldsEnInterfaz(cuadrosDeTextoFila, localizacionXFila, localizacionYFila);
			localizacionYFila += 40;
			
		}
		
	}
	
	//Funcion que obtiene la palabra caracter a caracter y la concateca con un StringBuilder
	private String obtenerPalabraCompleta(char caracterIngresado, int index)
	{
		
		// Si la palabra tiene 5 caracteres empieza a reemplazar en la pos correspondiente
		// si no concatena.
		
		if(palabraObtenida.length() == 5) 
			palabraObtenida.setCharAt(index, caracterIngresado);
		else 
			palabraObtenida.append(caracterIngresado);
		
		return palabraObtenida.toString().toLowerCase();
	}
	
	//Funcion que compara la palabra ingresada por el usuario con la elegida por el juego
	private void comprobarPalabraIngresada(String palabraCompleta, Wordle wordle,JTextField[] filaDeJTextFields)
	{
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
	}
	
	// Getters && Setter Palabra Completa
	public void setPalabraCompleta(String palabraCompetaObtenida) 
	{
		palabraCompleta = palabraCompetaObtenida;
	}
	
	public String palabraCompleta() 
	{
		return palabraCompleta;
	}

	//Funcion para insertar campos de texto en interfaz
	private void colocacionDeFieldsEnInterfaz(JTextField[] cuadrosDeTexto, int localizacionX, int localizacionY) 
	{
		
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
			
			//Eventos de para cada field
			cuadrosDeTexto[i].addKeyListener(new KeyAdapter(){
				
				@Override
				public void keyTyped(KeyEvent e) 
				{
					
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
					
					//Poniendo en mayuscula el texto ingresado en el field
					String textUpper = cuadrosDeTexto[posicion].getText().toUpperCase();
					cuadrosDeTexto[posicion].setText(textUpper);
					
					//Capturando el caracter ingresado en el field
					if(cuadrosDeTexto[posicion].getText().length() >= 1) 
					{
						
						palabraCompleta = obtenerPalabraCompleta(e.getKeyChar(), posicion);
						
						if(palabraCompleta.length() == 5) 
						{
							setPalabraCompleta(palabraCompleta);
						}
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
