package cr.ac.ulead.controlador;

import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import cr.ac.ulead.entities.Persona;

public class controlador {

	ArrayList<Persona> lista = new ArrayList<>();

	private static PrintStream output = new PrintStream(System.out);
	private static Scanner input = new Scanner(System.in);

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

	public Persona colaPrioridad() {
		Collections.sort(lista);
		Persona personaEliminada = null;
		if (lista.size() != 0) {
			personaEliminada = lista.remove(lista.size() - 1);
			output.println("Persona atendida (eliminada de la cola) " + personaEliminada);
		}
		return personaEliminada;
	}

	public static Integer anoFecha() {
		String nacimientoUser;
		LocalDate nacimiento, fechaActual;
		Period anno;
		Integer edad;
		nacimientoUser = UI.leerTexto("Ingrese el nacimiento (dd/mm/yyyy)");
		nacimiento = LocalDate.parse(nacimientoUser, DateTimeFormatter.ofPattern("d/M/yyyy"));
		fechaActual = LocalDate.now();
		anno = Period.between(nacimiento, fechaActual);
		edad = Math.abs(anno.getYears());
		return edad;
	}

	public class Node {
		private Node left, right, parent, personaCola;
		private int height = 1;
		private int value;

		private Node(int val) {
			this.value = val;
		}
	}

	private int height(Node N) {
		if (N == null)
			return 0;
		return N.height;
	}

	private Node insert(Node node, Integer personaNacimiento, Persona personNombre) {
		/* 1. Perform the normal BST rotation */
		if (node == null) {
			return (new Node(personaNacimiento));
		}

		if (personaNacimiento < node.value)
			node.left = insert(node.left, personaNacimiento, personNombre);
		else
			node.right = insert(node.right, personaNacimiento, personNombre);

		// 2. Update height of this ancestor node
		node.height = Math.max(height(node.left), height(node.right)) + 1;

		/*
		 * 3. Get the balance factor of this ancestor node to check whether this node
		 * became unbalanced
		 */

		int balance = getBalance(node);

		// If this node becomes unbalanced, then there are 4 cases

		// Left Left Case
		if (balance > 1 && personaNacimiento < node.left.value)
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && personaNacimiento > node.right.value)
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && personaNacimiento > node.left.value) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && personaNacimiento < node.right.value) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		// return the (unchanged) node pointer
		return node;
	}

	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		// Return new root
		return x;
	}

	private Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		// Perform rotation
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;

		// Return new root
		return y;
	}

	// Get Balance factor of node N
	private int getBalance(Node N) {
		if (N == null)
			return 0;
		return height(N.left) - height(N.right);
	}

	public void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			System.out.printf("%d ", root.value, root.personaCola);
			inOrder(root.right);
		}
	}

	public void posOrder(Node root) {
		if (root != null) {
			posOrder(root.left);
			posOrder(root.right);
			System.out.printf("%d ", root.value, root.personaCola);
		}
	}

	public void preOrder(Node root) {
		if (root != null) {
			System.out.printf("%d ", root.value, root.personaCola);
			preOrder(root.left);
			posOrder(root.right);
		}
	}

	public void print(Node root, Persona personaCola) {

		if (root == null) {
			System.out.println("(XXXXXX)");
			return;
		}

		int height = (int) root.height, width = (int) Math.pow(2, height - 1);
		// Preparing variables for loop.
		List<Node> current = new ArrayList<Node>(1), next = new ArrayList<Node>(2);
		current.add(root);

		final int maxHalfLength = 4;
		int elements = 1;

		StringBuilder sb = new StringBuilder(maxHalfLength * width);
		for (int i = 0; i < maxHalfLength * width; i++)
			sb.append(' ');
		String textBuffer;

		// Iterating through height levels.
		for (int i = 0; i < height; i++) {

			sb.setLength(maxHalfLength * ((int) Math.pow(2, height - 1 - i) - 1));

			// Creating spacer space indicator.
			textBuffer = sb.toString();

			// Print tree node elements
			for (Node n : current) {

				System.out.print(textBuffer);

				if (n == null) {

					System.out.print("        ");
					next.add(null);
					next.add(null);

				} else {

					System.out.printf("(%6d)", n.value, n.personaCola);
					next.add(n.left);
					next.add(n.right);

				}

				System.out.print(textBuffer);

			}

			System.out.println();
			// Print tree node extensions for next level.
			if (i < height - 1) {

				for (Node n : current) {

					System.out.print(textBuffer);

					if (n == null)
						System.out.print("        ");
					else
						System.out.printf("%s      %s", n.left == null ? " " : "/", n.right == null ? " " : "\\");

					System.out.print(textBuffer);

				}

				System.out.println();

			}

			// Renewing indicators for next run.
			elements *= 2;
			current = next;
			next = new ArrayList<Node>(elements);

		}

	}

	public void arbolAVL() throws IOException {
		Node root = null;
		Persona personaCola;
		output.println("Arbol AVL: ");
		int i = 0;
		while (true && i < lista.size()) {
			System.out.println("(1) Insert");
			System.out.println("(2) Delete");
			System.out.println("(3) salir");
			personaCola = lista.get(i);
			System.out.print("Persona ingresada al Arbol: ");
			root = insert(root, personaCola.getNacimiento(), personaCola);
			output.println(personaCola.getNombre());
			i++;

			print(root, personaCola);

		}
		UI.mostrarArbol();
		int opcion = 0;
		while (opcion != 4) {
			opcion = input.nextInt();
			switch (opcion) {
			case 1:
				output.println("Arbol en PreOrder ");
				preOrder(root);
				output.println("");
				break;
			case 2:
				output.println("Arbol en InOrder ");
				inOrder(root);
				output.println("");
				break;
			case 3:
				output.println("Arbol en PosOrder ");
				posOrder(root);
				output.println("");
				break;
			default:
				output.println("Inserte opción correcta");
				output.println("");
				break;
			}
		}
	}

	public void desplegarInterfaz() throws IOException {
		int opcion = 0;
		do {
			UI.mostrarMenu();
			opcion = UI.leerOpcion();
			procesadorOpcion(opcion);
		} while (opcion != 6);

	}

	private void procesadorOpcion(int opcion) throws IOException {
		String cedula, nombre, primerApellido, segundoApellido, direccion, provincia, canton, distrito, salto;
		Integer nacimiento;

		switch (opcion) {
		case 1:
			salto = UI.leerTexto("Llenne el siguiente formulario:");
			nombre = UI.leerTexto("Ingrese el nombre");
			primerApellido = UI.leerTexto("Ingrese 1er Apellido");
			segundoApellido = UI.leerTexto("Ingrese 2do Apellido");
			cedula = UI.leerTexto("Ingrese el cedula");
			nacimiento = anoFecha();
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
			arbolAVL();
			break;
		case 6:
			opcion = 6;
			output.print("Ha salido del programada");
			break;
		default:
			output.print("Inserte una opción de las anteriores");
			break;
		}
	}
}
