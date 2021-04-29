package cr.ac.ulead.controlador;

import java.io.PrintStream;
import java.util.Scanner;

public class UI {

	private static Scanner input = new Scanner(System.in);
	private static PrintStream output = new PrintStream(System.out);

	public static void mostrarMenu() {
		output.println("Bienvenido");
		output.println("1. Meter Persona a la Lista");
		output.println("2. Sacar Persona de la Lista");
		output.println("3. Consultar lista");
		output.println("4. Atender por edad (Cola de Prioridad)");
		output.println("5. Ordenar por TreeAVL");
		output.println("6. Salir");
		output.println("Digite su opcion");
	}
	public static void mostrarArbol() {
		output.println("1. Arbol en PreOrder");
		output.println("2. Arbol en InOrder");
		output.println("3. Arbol en PosOrder");
		output.println("4. Salir");
		output.println("Digite su opcion");
	}

	public static int leerOpcion() {
		return input.nextInt();
	}

	public static String leerTexto(String label) {
		output.println(label);
		return input.nextLine();
	}
	

	public static int leerNumero(String label) {
		output.println(label);
		return input.nextInt();
	}

	public static void mostrarTexto(String toString) {
		output.println();
	}
}
