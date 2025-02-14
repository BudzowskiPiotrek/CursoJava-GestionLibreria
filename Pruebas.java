package gestion.libreria;

import java.time.LocalDate;

public class App {
	public static void main(String[] args) {

		// CREACION DE BASE DE DATOS PARA VER SI APPLICACION FUNCCIONA BIEN

		Libro l1 = new Libro(0, "Titulo de Libro", "Autor del Libro", LocalDate.of(2000, 01, 01));
		Libro l2 = new Libro(1, "Titulo de Libro", "Autor del Libro", LocalDate.of(2000, 01, 01));
		Libro l3 = new Libro(2, "Titulo de Libro", "Autor del Libro", LocalDate.of(2000, 01, 01));
		Libro l4 = new Libro(3, "Titulo de Libro", "Autor del Libro", LocalDate.of(2000, 01, 01));

		Usuario u1 = new Usuario("Pablo Jimenez");
		Usuario u2 = new Usuario("Pedro Jimenez");

		// PRUEBAS DE SI SE PUEDE ALQUILAR LIBROS ALQUILADOS, MAS DE 3 LIBROS ECT

		System.out.println(u1.numeroLibrosPrestados());
		System.out.println("Libro es : " + (l1.isDisponible() ? "Disponible" : "No Disponible"));
		System.out.println("Préstamo libro 1 a Juan: " + l1.prestarLibro(u1, LocalDate.of(2025, 2, 14)));
		System.out.println("Libro es : " + (l1.isDisponible() ? "Disponible" : "No Disponible"));
		System.out.println(l1.isDisponible() ? "Disponible" : "No Disponible");
		System.out.println("Préstamo libro 2 a Juan: " + l2.prestarLibro(u1, LocalDate.of(2025, 2, 14)));
		System.out.println(u1.numeroLibrosPrestados());
		System.out.println("Préstamo libro 3 a Juan: " + l3.prestarLibro(u1, LocalDate.of(2025, 2, 14)));
		System.out.println(u1.numeroLibrosPrestados());
		System.out.println("Préstamo libro 4 a Juan: " + l4.prestarLibro(u1, LocalDate.of(2025, 2, 14)));
		System.out.println(u1.numeroLibrosPrestados());
		System.out.println("Devolucion libro 1 a Juan: " + l3.devolverLibro(u1, LocalDate.of(2025, 2, 14)));
		System.out.println(u1.numeroLibrosPrestados());

		// PRUEBA SI LA ID SE ASIGNA BIEN

		System.out.println("Numero de usuario : " + u1.getCodigoUsuario());
		System.out.println("Numero de usuario : " + u2.getCodigoUsuario());

	}

}
