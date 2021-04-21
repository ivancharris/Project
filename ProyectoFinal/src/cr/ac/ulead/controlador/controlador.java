package cr.ac.ulead.controlador;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import cr.ac.ulead.entities.Persona;

public class controlador {

	ArrayList<Persona> lista = new ArrayList<>();

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
		if (head != null) {
			if (head.siguiente == null) {
				head = null;
				longitud--;
			} else {
				Nodo puntero = head;
				while (puntero.siguiente.siguiente != null) {
					puntero = puntero.siguiente;
					output.print("Persona eliminada: " + puntero.siguiente);
				}
				puntero.siguiente = null;
				longitud--;
			}
		}

		if (lista.size() != 0) {
			lista.remove(lista.size() - 1);
			UI.mostrarTexto(nueva.toString());
		}
	}

	public void colaPrioridad() {
		Collections.sort(lista, Collections.reverseOrder());
		if (lista.size() != 0) {
			output.println("Persona eliminada " + lista.remove(lista.size() - 1));
		}
	}

	public void desplegarInterfaz() {
		int opcion = 0;
		do {
			UI.mostrarMenu();
			opcion = UI.leerOpcion();
			procesadorOpcion(opcion);
		} while (opcion != 5);

	}

	private void procesadorOpcion(int opcion) {
		String cedula, nombre, primerApellido, segundoApellido, direccion, provincia, canton, distrito, salto,
				nacimientoUser;
		LocalDate nacimiento, fechaActual;
		Period anno;
		int edad;

		switch (opcion) {
		case 1:
			salto = UI.leerTexto("Llenne el siguiente formulario:");
			nombre = UI.leerTexto("Ingrese el nombre");
			primerApellido = UI.leerTexto("Ingrese 1er Apellido");
			segundoApellido = UI.leerTexto("Ingrese 2do Apellido");
			cedula = UI.leerTexto("Ingrese el cedula");
			nacimientoUser = UI.leerTexto("Ingrese el nacimiento (dd/mm/yyyy)");
			nacimiento = LocalDate.parse(nacimientoUser, DateTimeFormatter.ofPattern("d/M/yyyy"));
			fechaActual = LocalDate.now();
			anno = Period.between(nacimiento, fechaActual);
			edad = Math.abs(anno.getYears());
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
			colaPrioridad();
			break;
		case 5:
			opcion = 5;
			output.print("Ha salido del programada");
			break;
		default:
			output.print("Inserte una opción de las anteriores");
			break;
		}
	}

}
