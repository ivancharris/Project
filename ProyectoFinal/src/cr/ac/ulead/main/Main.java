package cr.ac.ulead.main;

import java.io.PrintStream;
import java.util.Scanner;

import cr.ac.ulead.controlador.UI;
import cr.ac.ulead.controlador.controlador;

public class Main {
	
	private static Scanner input = new Scanner(System.in);
	private static PrintStream output = new PrintStream(System.out);
	

	public static void main(String[] args) {

		startProgram();
		
	}

	public static void startProgram() {

		controlador controlador = new controlador();
		controlador.desplegarInterfaz();

	}

}
