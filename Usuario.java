package gestion.libreria;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Usuario {
	
	// VARIABLES DE CLASE USUARIO
	
	private String nombre;
	private Libro[] libros;
	private boolean multado;
	private int codigoUsuario;
	public static int contador = 0;

	// CONSTRUCTOR DE LA CLASE
	
	public Usuario(String nombre) {
		this.nombre = nombre;
		this.libros = new Libro[3];
		this.multado = false;
		this.codigoUsuario = ++contador;
	}

	// LOS GETER Y LOS SETER
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Libro[] getLibros() {
		return libros;
	}

	public void setLibros(Libro[] libros) {
		this.libros = libros;
	}

	
	// DENTRO DE GETER DE MULTADO CREAMOS METODO DE CONTROL DE MULTADO PARA QUE ESTE MULTADO 10 DIAS
	
	public boolean isMultado() {
		boolean seguro = true;
		if (multado) {
			for (int i = 0; i < libros.length && seguro; i++) {
				if (libros[i] != null) {
					long diasDesdeDevolucion = ChronoUnit.DAYS.between(libros[i].getFechaDevolución(), LocalDate.now());
					if (diasDesdeDevolucion >= 10) {
						multado = false;
						seguro = false;
					}
				}
			}
		}
		return multado;
	}

	public void setMultado(boolean multado) {
		this.multado = multado;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Usuario.contador = contador;
	}

	// METODO DE AGREGAR LIBRO 
	
	public boolean agregarLibro(Libro libro) {
		boolean exito = false, seguro = true;
		for (int i = 0; i < libros.length && seguro; i++) {
			if (libros[i] == null) {
				libros[i] = libro;
				exito = true;
				seguro = false;
			}
		}
		return exito;
	}

	// METODO PARA BORRAR LIBRO
	
	public boolean borrarLibro(Libro libro) {
		boolean exito = false;
		for (int i = 0; i < libros.length; i++) {
			if (libros[i] != null && libros[i].getId() == libro.getId()) {
				libros[i] = null;
				exito = true;
			}
		}
		return exito;
	}

	// METODO PARA CAMBIAR A MULTADO POR NO DEVOLVER EN LOS PLAZOS
	
	public void multarUsuario(LocalDate fechaDevolucionTardia) {
		long diasFueraDePlazo = ChronoUnit.DAYS.between(fechaDevolucionTardia, LocalDate.now());
		if (diasFueraDePlazo > 0) {
			this.multado = true;
		}
	}

	// METODO PARA CALCULAR LIBROS PRESTADOS
	
	public int numeroLibrosPrestados() {
		int count = 0;
		for (int i = 0; i < libros.length; i++) {
			if (libros[i] != null) { 
				count++;
			}
		}
		return count;
	}
}
