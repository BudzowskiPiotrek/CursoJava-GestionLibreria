package gestion.libreria;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Libro {
	
	// VARIABLES DE CLASE LIBRO
	
	private int id;
	private String titulo;
	private String autor;
	private LocalDate anioPublicacion;
	private boolean disponible;
	private int codigoPretamista;
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	
	// CONSTRUCTOR DE LA CLASE
	
	public Libro(int id, String titulo, String autor, LocalDate anioPublicacion) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anioPublicacion = anioPublicacion;
		this.disponible = true;
	}
	
	// LOS GETER Y LOS SETER
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(LocalDate anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public int getCodigoPretamista() {
		return codigoPretamista;
	}

	public void setCodigoPretamista(int codigoPretamista) {
		this.codigoPretamista = codigoPretamista;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolución() {
		return fechaDevolucion;
	}

	public void setFechaDevolución(LocalDate fechaDevolución) {
		this.fechaDevolucion = fechaDevolución;
	}

	
	// METODO PARA PRESTAR LIBROS
	
	public boolean prestarLibro(Usuario usuario, LocalDate fechaPrestamo) {
		boolean exito = false;
		if (this.disponible && usuario.numeroLibrosPrestados() < 3 && !usuario.isMultado()) {
			this.codigoPretamista = usuario.getCodigoUsuario();
			this.fechaPrestamo = fechaPrestamo;
			this.fechaDevolucion = fechaPrestamo.plusDays(15);
			this.disponible = false;
			usuario.agregarLibro(this);
			exito = true;
		}
		return exito;
	}

	// METODO PARA DEVOLVER LIBROS
	
	public boolean devolverLibro(Usuario usuario, LocalDate fechaDevolucion) {
        boolean exito = false;
        long fueraPlazo = ChronoUnit.DAYS.between(fechaDevolucion, this.fechaDevolucion);
        if (!this.disponible && usuario.getCodigoUsuario() == this.codigoPretamista) {
            this.disponible = true;
            usuario.borrarLibro(this);
            if (fueraPlazo > 0) {
                usuario.multarUsuario(fechaDevolucion); 
            }
            exito = true;
        }
        return exito;
	}

}
