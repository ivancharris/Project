package cr.ac.ulead.main;

import cr.ac.ulead.controlador.controlador;

public class Main {
	
	public static void main(String[] args) {

		startProgram();
		
	}

	public static void startProgram() {

		controlador controlador = new controlador();
		controlador.desplegarInterfaz();

	}

}
