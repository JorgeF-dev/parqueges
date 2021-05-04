package com.sanvalero.parqueges;

import com.sanvalero.parqueges.dao.CiudadDAO;
import com.sanvalero.parqueges.dao.Conexion;
import com.sanvalero.parqueges.dao.ParqueDAO;
import com.sanvalero.parqueges.domain.Ciudad;
import com.sanvalero.parqueges.domain.Parque;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 *
 */
public class ParqueGes {

    private boolean salir;
    private final Scanner teclado;
    private final Conexion connection;
    private final CiudadDAO ciudadDAO;
    private final ParqueDAO parqueDAO;

    public ParqueGes() {
        salir = false;
        teclado = new Scanner(System.in);
        connection = new Conexion();
        connection.connect();
        parqueDAO = new ParqueDAO(connection);
        ciudadDAO = new CiudadDAO(connection);
    }

    public void ejecutar() {

        System.out.println("¡Hola! Te damos la bienvenida al programa de gestion ParqueGes");
        do {
            System.out.println("¿qué deseas hacer?");
            System.out.println("1. Listar todos los parques de una determinada ciudad por nombre");
            System.out.println("2. Listar todos los parques de una cierta comunidad autónoma por nombre");
            System.out.println("3. Añadir un parque a una determinada ciudad");
            System.out.println("4. Actualizar la informacion de un parque");
            System.out.println("5. Seleccionar todos los parques cuyo nombre contenga una determinada cadena");
            System.out.println("6. Mostrar el número de parques de una ciudad que tienen una extensión individual mayor a la que indiques");
            System.out.println("7. Borrar todos los parques de la ciudad que indiques");
            System.out.println("8. Listar el nombre de todas las ciudades que contengan parques cuya suma total de su extensión, sea mayor a la que indiques");
            System.out.println("x. Salir");
            System.out.println("Selecciona: ");
            String menu = teclado.nextLine();

            switch (menu.toUpperCase()) {
                case "1":
                    verParquesCiudad();
                    break;
                case "2":
                    verParquesComunidad();
                    break;
                case "3":
                    addParqueAciudad();
                    break;
                case "4":
                    modificarParque();
                    break;
                case "5":
                    buscarParque();
                    break;
                case "6":
                    numParquesExt();
                    break;
                case "7":
                    borrarParquesDeCiudad();
                    break;
                case "8":
                    listarCiudadesParquesExtension();
                    break;
                case "X":
                    salir();
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (!salir);

    }

    private void verParquesCiudad() {
        System.out.println("Introduce el nombre de la ciudad:");
        String nombre = teclado.nextLine();
        try {
            int id_ciudad = ciudadDAO.verIdCiudad(nombre);
            ArrayList<Parque> parque1;
            parque1 = parqueDAO.listarParques(id_ciudad);
            for (Parque parque : parque1) {
                System.out.println(parque);
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
    }
//quiero saber el nombre de las ciudades de una ccaa para luego poder usar ciudadDAO.verID(nombre);
    private void verParquesComunidad() {
        System.out.println("Introduce el nombre de la comunidad autónoma: ");
        String ccaa = teclado.nextLine();
        try {
            ArrayList<Ciudad> nombresC;
            nombresC = ciudadDAO.nombresCiudad(ccaa);
            for (Ciudad ciudad : nombresC) {
                System.out.println(ciudad); // == ciudad.toString();
                String nombre = ciudad.getNombre();
            int id_ciudad = ciudadDAO.verIdCiudad(nombre);
            ArrayList<Parque> parque1;
            parque1 = parqueDAO.listarParques(id_ciudad);
            for (Parque parque : parque1) {
                System.out.println(parque);
            }
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
    }
    //TODO Falta equals para ver si el parque a añadir ya existe en la bbddd    
    private void addParqueAciudad() {
        int id_ciudad;
        System.out.println("Nombra la ciudad: (ej. Madrid)");
        String nombre = teclado.nextLine();
        try {
            id_ciudad = ciudadDAO.verIdCiudad(nombre);

            System.out.println("Introduce el nombre del parque que quieres añadir: ");
            String nuevoparque = teclado.nextLine();
            System.out.println("Introduce la extension del parque: ");
            int extension = Integer.parseInt(teclado.nextLine());

            Parque parque = new Parque(id_ciudad, extension, nuevoparque);
            try {
                parqueDAO.crearParque(parque);
                System.out.println("Se ha creado el parque correctamente");
            } catch (SQLException sqle) {
                System.out.println("Se ha producido un problema. Inténtelo de nuevo");
                sqle.printStackTrace();
            }
        } catch (SQLException sqle) {
            System.out.println("La ciudad introducida no existe, inténtelo de nuevo: (ej. Zaragoza)");
            sqle.printStackTrace();
        }

    }

    private void modificarParque() {
        System.out.println("Nombra el parque: (ej. Labordeta)");
        String nombre = teclado.nextLine();
        try {
            int id_parque = parqueDAO.verIdParque(nombre);
            System.out.println("Nuevo nombre del parque: ");
            String nuevonombre = teclado.nextLine();
            System.out.println("Ciudad: ");
            String nuevaciudad = teclado.nextLine();
            int id_ciudad = ciudadDAO.verIdCiudad(nuevaciudad);  //***************************************************
            System.out.println("Nueva extension: ");
            int nuevaextension = Integer.parseInt(teclado.nextLine());
            Parque parque = new Parque(id_parque, id_ciudad, nuevaextension, nuevonombre);
            parqueDAO.modificarParque(parque);
        } catch (SQLException sqle) {
            System.out.println("El nombre del parque no es correcto");
            sqle.printStackTrace();
        }
    }

    private void buscarParque() {
        System.out.println("Introduce la cadena de busqueda: ");
        String cadenaBusqueda1 = teclado.nextLine();
        String cadenaBusqueda = '%' + cadenaBusqueda1 + '%';
        try {
            ArrayList<Parque> parque1;
            parque1 = parqueDAO.buscarParque(cadenaBusqueda);
            for (Parque parque : parque1) {
                System.out.println(parque);
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
    }

    private void numParquesExt() {
        //parques de una determinada ciudad cuya extension individual sea mayor de la que ponga
        int id_ciudad;
        int cantidad1;
        System.out.println("Nombra la ciudad: (ej. Madrid)");
        String nombre = teclado.nextLine();
        try {
            id_ciudad = ciudadDAO.verIdCiudad(nombre);

            System.out.println("Introduce la extensión: ");
            int extension = Integer.parseInt(teclado.nextLine());
            cantidad1 = parqueDAO.numParquesExt(id_ciudad, extension);
            System.out.println("Número de parques con más extensión = " + cantidad1);

        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
    }

    private void borrarParquesDeCiudad() {
        System.out.println("Nombra la ciudad: (ej. Madrid)");
        String nombre = teclado.nextLine();
        try {
            int id_ciudad = ciudadDAO.verIdCiudad(nombre);
            parqueDAO.borrarParquesDeCiudad(id_ciudad);
            System.out.println("Parques de la ciudad borrados");
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }

    }
//TODO falta por hacer, NO FUNCIONA.
    private void listarCiudadesParquesExtension() {
        System.out.println("Indica la extensión: ");
        int extension = Integer.parseInt(teclado.nextLine());

        try {
            ArrayList<Ciudad> ciudad1;
            ciudad1 = ciudadDAO.listarCiudadesParquesExtension(extension);
            for (Ciudad ciudad : ciudad1) {
                System.out.println(ciudad);
            }

        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
    }

    private void salir() {
        salir = true;
    }

}
