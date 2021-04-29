package cr.ac.ulead.main;

import java.io.IOException;

import cr.ac.ulead.controlador.controlador;

public class Main {
	
	public static void main(String[] args) throws IOException  {

		startProgram();
		
	}

	public static void startProgram() throws IOException{

		controlador controlador = new controlador();
		controlador.desplegarInterfaz();

	}

}
