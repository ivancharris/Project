package cr.ac.ulead.controlador;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import cr.ac.ulead.entities.Persona;

public class controlador {

	ArrayList<Persona> lista = new ArrayList();

	private static Scanner input = new Scanner(System.in);
	private static PrintStream output = new PrintStream(System.out);

	Persona nueva = null;
	private Nodo head = null;
	private int longitud = 0;

	private class Nodo {
		public Persona persona;
		public Nodo siguiente = null;

		public Nodo(Persona persona) {
			this.persona = persona;
		}
	}

	public void listaPush(Persona persona) {
		Nodo nodo = new Nodo(persona);
		if (head == null) {
			head = nodo;
		} else {
			Nodo puntero = head;
			while (puntero.siguiente != null) {
				puntero = puntero.siguiente;
			}
			puntero.siguiente = nodo;
		}
		longitud++;
	}

	public void consultarLista() {

		for (int i = 0; i < lista.size(); i++) {
			output.println(lista.get(i));
		}

	}

	public int contar() {
		return longitud;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void listaPop() {
		Nodo puntero = head;
		if (head != null) {
			if (head.siguiente == null) {
				head = null;
				longitud--;
			} else {
				while (puntero.siguiente.siguiente != null) {
					puntero = puntero.siguiente;
				}
				puntero.siguiente = null;
				longitud--;
			}
			lista.remove(lista.size() - 1);
			if (puntero.persona == null) {
				puntero.persona = null;
			}
			output.print("Persona eliminada: " + nueva);
			UI.mostrarTexto(nueva.toString());
		}
	}

	public void desplegarInterfaz() {
		int opcion = 0;
		do {
			UI.mostrarMenu();
			opcion = UI.leerOpcion();
			procesadorOpcion(opcion);
		} while (opcion != 4);

	}

	private void procesadorOpcion(int opcion) {
		String cedula, nombre, primerApellido, segundoApellido, direccion, provincia, canton, distrito, salto,
				nacimientoUser;

		switch (opcion) {
		case 1:
			salto = UI.leerTexto("Llenne el siguiente formulario:");
			nombre = UI.leerTexto("Ingrese el nombre");
			primerApellido = UI.leerTexto("Ingrese 1er Apellido");
			segundoApellido = UI.leerTexto("Ingrese 2do Apellido");
			cedula = UI.leerTexto("Ingrese el cedula");
			nacimientoUser = UI.leerTexto("Ingrese el nacimiento (dd/mm/yyyy)");
			LocalDate nacimiento = LocalDate.parse(nacimientoUser, DateTimeFormatter.ofPattern("d/M/yyyy"));
			direccion = UI.leerTexto("Ingrese el direccion");
			provincia = UI.leerTexto("Ingrese el provincia");
			canton = UI.leerTexto("Ingrese el canton");
			distrito = UI.leerTexto("Ingrese distrito");

			nueva = new Persona(nombre, primerApellido, segundoApellido, cedula, nacimiento, direccion, provincia,
					canton, distrito);

			listaPush(nueva);
			output.println("Persona ingresada" + nueva.toString());
			lista.add(nueva);

			break;

		case 2:
			listaPop();
			break;

		case 3:
			consultarLista();
			break;
		case 4:
			opcion = 4;
			break;
		default:
		}
	}

}
