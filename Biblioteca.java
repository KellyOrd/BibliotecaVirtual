package ec.edu.ups.bibliotecav;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Biblioteca {
		private String nombre;
	    private String direccion;
	    private ArrayList<Libro> listaLibros;
	    private ArrayList<Usuario> listaUsuarios;
	    private Date fechaActual;

	    public Biblioteca(String nombre, String direccion) {
	        this.nombre = nombre;
	        this.direccion = direccion;
	        listaLibros = new ArrayList<>();
	        listaUsuarios = new ArrayList<>();
	        fechaActual = new Date();
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }

	    public ArrayList<Libro> getListaLibros() {
	        return listaLibros;
	    }

	    public void setListaLibros(ArrayList<Libro> listaLibros) {
	        this.listaLibros = listaLibros;
	    }

	    public ArrayList<Usuario> getListaUsuarios() {
	        return listaUsuarios;
	    }

	    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
	        this.listaUsuarios = listaUsuarios;
	    }

	    public void agregarLibro(Libro libro) {
	        listaLibros.add(libro);
	    }

	    public void registrarUsuario(Usuario usuario) {
	        listaUsuarios.add(usuario);
	    }

	    public Libro buscarLibro1(String titulo) {
	        for (Libro libro : listaLibros) {
	            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
	                return libro;
	            }
	        }
	        return null;
	    }

	    public Libro buscarLibro(String autor) {
	        for (Libro libro : listaLibros) {
	            if (libro.getAutor().equalsIgnoreCase(autor)) {
	                return libro;
	            }
	        }
	        return null;
	    }

	    public Libro buscarLibro(int anio) {
	        for (Libro libro : listaLibros) {
	            if (libro.getAnio() == anio) {
	                return libro;
	            }
	        }
	        return null;
	    }

	    public void prestarLibro(Libro libro, Usuario usuario) {
	        if (libro.isDisponible()) {
	            libro.setDisponible(false);
	            Prestamo prestamo = new Prestamo(libro, usuario);
	            libro.getListaPrestamos().add(prestamo);
	            System.out.println("Libro prestado correctamente.");
	        } else {
	            System.out.println("El libro " + libro.getTitulo() + " no está disponible.");
	        }
	    }

	    public void devolverLibro(Libro libro, Usuario usuario) {
	        for (Prestamo prestamo : libro.getListaPrestamos()) {
	            if (prestamo.getUsuario().equals(usuario)) {
	                libro.setDisponible(true);
	                prestamo.setFechaDevolucion(new Date());
	                System.out.println("Libro devuelto correctamente.");
	                return;
	            }
	        }
	        System.out.println("El usuario no tiene prestado este libro.");
	    }

	    public boolean verificarFechaVencimiento(Prestamo prestamo) {
	        return prestamo.getFechaDevolucion().before(fechaActual);
	    }

	    public double calcularMulta(Prestamo prestamo) {
	        int diasAtrasados = (int) ((fechaActual.getTime() - prestamo.getFechaDevolucion().getTime()) / (1000 * 60 * 60 * 24));
	        return diasAtrasados * 0.5;
	    }

	    public void cobrarMulta(Prestamo prestamo) {
	        Usuario usuario = prestamo.getUsuario();
	        usuario.setSaldo(usuario.getSaldo() + calcularMulta(prestamo));
	    }

	    public static void main(String[] args) {
	        Biblioteca biblioteca = new Biblioteca("Biblioteca Central", "Avenida 54");
	        Scanner scanner = new Scanner(System.in);
	        int opcion;

	        do {
	            System.out.println("Bienvenido al Sistema de Gestión de Biblioteca");
	            System.out.println("1. Agregar Libro");
	            System.out.println("2. Registrar Usuario");
	            System.out.println("3. Buscar Libro");
	            System.out.println("4. Llevar Libro");
	            System.out.println("5. Devolver Libro");
	            System.out.println("6. Salir");
	            System.out.print("Ingrese una opción: ");
	            opcion = scanner.nextInt();
	            scanner.nextLine();

	            switch (opcion) {
	                case 1:
	                    agregarLibro(biblioteca, scanner);
	                    break;
	                case 2:
	                    registrarUsuario(biblioteca, scanner);
	                    break;
	                case 3:
	                    buscarLibro(biblioteca, scanner);
	                    break;
	                case 4:
	                    prestarLibro(biblioteca, scanner);
	                    break;
	                case 5:
	                    devolverLibro(biblioteca, scanner);
	                    break;
	                case 6:
	                    System.out.println("Se ha salido del programa con éxito.");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Por favor intente de nuevo.");
	            }
	        } while (opcion != 6);

	        scanner.close();
	    }

	    private static void agregarLibro(Biblioteca biblioteca, Scanner scanner) {
	        System.out.print("Ingrese el título del libro: ");
	        String tituloLibro = scanner.nextLine();
	        System.out.print("Ingrese el autor del libro: ");
	        String autorLibro = scanner.nextLine();
	        System.out.print("Ingrese el año de publicación del libro: ");
	        int anioLibro = scanner.nextInt();
	        scanner.nextLine();  // Consumir la nueva línea

	        Libro nuevoLibro = new Libro(tituloLibro, autorLibro, anioLibro);
	        biblioteca.agregarLibro(nuevoLibro);
	        System.out.println("Libro agregado correctamente.");
	    }

	    private static void registrarUsuario(Biblioteca biblioteca, Scanner scanner) {
	        System.out.print("Ingrese el nombre del usuario: ");
	        String nombreUsuario = scanner.nextLine();
	        System.out.print("Ingrese la identificación del usuario: ");
	        String identificacionUsuario = scanner.nextLine();
	        System.out.print("Ingrese el correo del usuario: ");
	        String correoUsuario = scanner.nextLine();

	        Usuario nuevoUsuario = new Usuario(nombreUsuario, identificacionUsuario, correoUsuario);
	        biblioteca.registrarUsuario(nuevoUsuario);
	        System.out.println("Usuario registrado correctamente.");
	    }

	    private static void buscarLibro(Biblioteca biblioteca, Scanner scanner) {
	        System.out.print("Ingrese el título, autor o año del libro a buscar: ");
	        String busqueda = scanner.nextLine();
	        Libro libroEncontrado = biblioteca.buscarLibro1(busqueda);

	        if (libroEncontrado != null) {
	            System.out.println("Libro encontrado:");
	            libroEncontrado.mostrarInformacion();
	        } else {
	            System.out.println("Libro no encontrado.");
	        }
	    }

	    private static void prestarLibro(Biblioteca biblioteca, Scanner scanner) {
	        System.out.print("Ingrese el título, autor o año del libro a prestar: ");
	        String busqueda = scanner.nextLine();
	        Libro libro = biblioteca.buscarLibro1(busqueda);

	        if (libro != null) {
	            System.out.print("Ingrese el identificador del usuario que prestará el libro: ");
	            String identificacionUsuario = scanner.nextLine();
	            Usuario usuario = buscarUsuario(biblioteca, identificacionUsuario);

	            if (usuario != null) {
	                biblioteca.prestarLibro(libro, usuario);
	            } else {
	                System.out.println("Usuario no encontrado.");
	            }
	        } else {
	            System.out.println("Libro no encontrado.");
	        }
	    }

	    private static void devolverLibro(Biblioteca biblioteca, Scanner scanner) {
	        System.out.print("Ingrese el título, autor o año del libro a devolver: ");
	        String busqueda = scanner.nextLine();
	        Libro libro = biblioteca.buscarLibro1(busqueda);

	        if (libro != null) {
	            System.out.print("Ingrese el identificador del usuario que devolverá el libro: ");
	            String identificacionUsuario = scanner.nextLine();
	            Usuario usuario = buscarUsuario(biblioteca, identificacionUsuario);

	            if (usuario != null) {
	                biblioteca.devolverLibro(libro, usuario);
	            } else {
	                System.out.println("Usuario no encontrado.");
	            }
	        } else {
	            System.out.println("Libro no encontrado.");
	        }
	    }

	    private static Usuario buscarUsuario(Biblioteca biblioteca, String identificacion) {
	        for (Usuario usuario : biblioteca.getListaUsuarios()) {
	            if (usuario.getIdentificacion().equalsIgnoreCase(identificacion)) {
	                return usuario;
	            }
	        }
	        return null;
	    }

}
