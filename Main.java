package gestion.libreria;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	// Biblioteca con capacidad para 100 libros y 10 usuarios.

	private static Libro[] biblioteca = new Libro[100];
	private static Usuario[] usuarios = new Usuario[10];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		inicializarDatos();
		while (flag) {
			System.out.println("\nSeleccione una opción:");
			System.out.println("1. Mostrar libros");
			System.out.println("2. Agregar libro");
			System.out.println("3. Eliminar libro");
			System.out.println("4. Prestar libro");
			System.out.println("5. Devolver libro");
			System.out.println("6. Salir");
			int opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
			case 1:
				mostrarLibros();
				break;
			case 2:
				System.out.println("Ingrese el ID del libro:");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Ingrese el título del libro:");
				String titulo = scanner.nextLine();
				System.out.println("Ingrese el autor del libro:");
				String autor = scanner.nextLine();
				System.out.println("Ingrese la fecha de publicación (YYYY-MM-DD):");
				String fecha = scanner.nextLine();
				LocalDate anioPublicacion = LocalDate.parse(fecha);
				Libro libroNuevo = new Libro(id, titulo, autor, anioPublicacion);
				agregarLibro(libroNuevo);
				break;
			case 3:
				System.out.println("Ingrese el ID del libro a eliminar:");
				int idEliminar = scanner.nextInt();
				eliminarLibro(idEliminar);
				break;
			case 4:
				System.out.println("Ingrese el ID del libro a prestar:");
				int idPrestar = scanner.nextInt();
				System.out.println("Ingrese el código de usuario:");
				int codigoUsuario = scanner.nextInt();
				System.out.println("Ingrese la fecha de préstamo (YYYY-MM-DD):");
				scanner.nextLine();
				String fechaPrestamo = scanner.nextLine();
				LocalDate fechaPrestamoLocal = LocalDate.parse(fechaPrestamo);
				prestarLibro(idPrestar, codigoUsuario, fechaPrestamoLocal);
				break;
			case 5:
				System.out.println("Ingrese el ID del libro a devolver:");
				int idDevolver = scanner.nextInt();
				System.out.println("Ingrese el código de usuario:");
				int codigoUsuarioDevolver = scanner.nextInt();
				System.out.println("Ingrese la fecha de devolución (YYYY-MM-DD):");
				scanner.nextLine();
				String fechaDevolucion = scanner.nextLine();
				LocalDate fechaDevolucionLocal = LocalDate.parse(fechaDevolucion);
				devolverLibro(idDevolver, codigoUsuarioDevolver, fechaDevolucionLocal);
				break;
			case 6:
				System.out.println("Saliendo...");
				flag = false;
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
			}
		}
	}

	// METODO INICIALIZAR NUESTRA PEQUEÑA BASE DE DATOS
	public static void inicializarDatos() {

		usuarios[0] = new Usuario("Juan");
		usuarios[1] = new Usuario("Maria");
		usuarios[2] = new Usuario("Pedro");
		usuarios[3] = new Usuario("Ana");

		biblioteca[0] = new Libro(1, "El Quijote", "Cervantes", LocalDate.of(2000, 1, 1));
		biblioteca[1] = new Libro(2, "1685", "Manu Chao", LocalDate.of(2000, 1, 1));
		biblioteca[2] = new Libro(3, "Matar de Patadon", "Bruce Lee", LocalDate.of(2000, 1, 1));
	}

	// METODO IMPRIMIR LIBROS DISPONIBLES

	public static void mostrarLibros() {
		System.out.println("Lista de libros disponibles:");
		for (int i = 0; i < biblioteca.length; i++) {
			if (biblioteca[i] != null) {
				System.out.println("ID: " + biblioteca[i].getId() + ", Título: " + biblioteca[i].getTitulo()
						+ ", Autor: " + biblioteca[i].getAutor() + ", Disponible: " + biblioteca[i].isDisponible());
			}
		}
	}

	// METODO AGREGAR LIBRO CON COMPROBACION DE SI HAY SITIO EN LIBRERIA

	public static void agregarLibro(Libro libro) {
		boolean seguro = true;

		for (int i = 0; i < biblioteca.length && seguro; i++) {
			if (biblioteca[i] == null) {
				biblioteca[i] = libro;
				System.out.println("Libro agregado: " + libro.getTitulo());
				seguro = false;
			}
		}

		if (seguro) {
			System.out.println("No hay espacio en la biblioteca para agregar más libros.");
		}
	}

	// METODO BORRAR LIBRO CON COMPROBACION DE SI ESTA EN LIBRERIA

	public static void eliminarLibro(int id) {
		boolean seguro = true;

		for (int i = 0; i < biblioteca.length && seguro; i++) {
			if (biblioteca[i] != null && biblioteca[i].getId() == id) {
				biblioteca[i] = null;
				System.out.println("Libro eliminado.");
				seguro = false;
			}
		}

		if (seguro) {
			System.out.println("No se encontró el libro con el ID " + id);
		}
	}

	// METODO PRESTAR LIBRO DESAROLLADO PARA BSUCAR TANTO EN BASE DE DATOS
	// DE LIBROS COMO DE USUARIOS SI LIBRO Y USUARIO EXISTE SI ES ASI PROCEDER
	// A PRESTARLO SI NO ESTA MULTADO Y NO PASO DE LIMITE DE 3

	public static void prestarLibro(int idLibro, int codigoUsuario, LocalDate fechaPrestamo) {
		Libro libro = null;
		Usuario usuario = null;
		boolean seguro = true;

		for (int i = 0; i < biblioteca.length && seguro; i++) {
			if (biblioteca[i] != null && biblioteca[i].getId() == idLibro) {
				libro = biblioteca[i];
				seguro = false;
			}
		}

		for (int i = 0; i < usuarios.length && seguro; i++) {
			if (usuarios[i] != null && usuarios[i].getCodigoUsuario() == codigoUsuario) {
				usuario = usuarios[i];
				seguro = false;
			}
		}

		if (libro == null) {
			System.out.println("Libro no encontrado.");
		} else if (usuario == null) {
			System.out.println("Usuario no encontrado.");
		} else {
			if (libro.prestarLibro(usuario, fechaPrestamo)) {
				System.out.println("El libro ha sido prestado con éxito.");
			} else {
				System.out.println(
						"No se pudo prestar el libro. Verifique que no esté multado y que no tenga más de tres libros prestados.");
			}
		}
	}

	// METODO DEVOLVER LIBRO DESAROLLADO PARA BSUCAR TANTO EN BASE DE DATOS
	// DE LIBROS COMO DE USUARIOS SI LIBRO Y USUARIO EXISTE SI ES ASI PROCEDER
	// A DEVOLVERLO

	public static void devolverLibro(int idLibro, int codigoUsuario, LocalDate fechaDevolucion) {
		Libro libro = null;
		Usuario usuario = null;
		boolean seguro = true;

		for (int i = 0; i < biblioteca.length && seguro; i++) {
			if (biblioteca[i] != null && biblioteca[i].getId() == idLibro) {
				libro = biblioteca[i];
				seguro = false;
			}
		}

		seguro = true;
		for (int i = 0; i < usuarios.length && seguro; i++) {
			if (usuarios[i] != null && usuarios[i].getCodigoUsuario() == codigoUsuario) {
				usuario = usuarios[i];
				seguro = false;
			}
		}

		if (libro == null) {
			System.out.println("Libro no encontrado.");
		} else if (usuario == null) {
			System.out.println("Usuario no encontrado.");
		} else {

			if (libro.devolverLibro(usuario, fechaDevolucion)) {
				System.out.println("El libro ha sido devuelto correctamente.");
			} else {
				System.out.println("No se pudo devolver el libro.");
			}
		}
	}

}