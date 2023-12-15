package ec.edu.ups.bibliotecav;

import java.util.ArrayList;


public class Usuario {
	private String nombre;
    private String identificacion;
    private String correo;
    private ArrayList<Prestamo> listaPrestamos;

    public Usuario(String nombre, String identificacion, String correo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.listaPrestamos = new ArrayList<>();
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public double getSaldo() {
        return 0.0;
    }

	public void setSaldo(double d) {
		// TODO Auto-generated method stub
		
	}
}