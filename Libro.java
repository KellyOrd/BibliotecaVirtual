package ec.edu.ups.bibliotecav;

import java.util.ArrayList;

public class Libro {
	private String titulo;
    private String autor;
    private int anio;
    private boolean disponible;
    private ArrayList<Prestamo> listaPrestamos;

    public Libro(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.disponible = true;
        listaPrestamos = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnio() {
        return anio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public ArrayList<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }

    public void mostrarInformacion() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Año: " + anio);
        System.out.println("Disponible: " + disponible);
    }
}
