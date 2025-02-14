package gestion.libreria;

public class Usuario {
	private String nombre;
	private Libro[] libros;
	private boolean multado;
	private int codigoUsuario;
	public static int contador = 0;

	public Usuario(String nombre) {
		this.nombre = nombre;
		this.libros = new Libro[3];
		this.multado = false;
		this.codigoUsuario = ++contador;
	}

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

	public boolean isMultado() {
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

	public boolean agregarLibro(Libro libro) {
		boolean exito = false, seguro = true;
		for (int i = 0; i < libros.length & seguro; i++) {
			if (libros[i] == null) {
				libros[i] = libro;
				exito = true;
				seguro = false;
			}
		}
		return exito;
	}

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

	public void multarUsuario(long diasFueraDePlazo) {
		if (diasFueraDePlazo > 0) {
			this.multado = true;
		}

	}

	public int numeroLibrosPrestados() {
		int count = 0;
		for (int i = 0; i < libros.length; i++) {
			if (libros[i] == null) {
				count += 1;
			}
		}
		return count;
	}

}
