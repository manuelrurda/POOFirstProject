import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Habitacion {

    public static int numHabitaciones = 5;
    private int numero;
    private ArrayList<Reservacion> reservaciones = new ArrayList<Reservacion>();
    public static Hashtable<Integer, Habitacion> habitaciones = new Hashtable<Integer, Habitacion>();

    public Habitacion(int numero){
        this.numero = numero;
    }
    
    public ArrayList<Reservacion> getReservaciones(){
        return this.reservaciones;
    }

    public int getNumero(){
        return this.numero;
    }

    public static void crearHabitaciones(int numHabitaciones){
        for(int i = 1; i <= numHabitaciones; i++){
            habitaciones.put(i, new Habitacion(i));
        }
    }

    public static int validarNumeroHabitacion(Scanner sc, int numHabitaciones){
        int numeroHabitacion = -1;
            Boolean habitacionValida;
            do{
                habitacionValida = true;

                // Try-catch para evitar que se ingresen letras
                try{
                    numeroHabitacion = Integer.valueOf(sc.nextLine());
                    if (numeroHabitacion < 1 || numeroHabitacion > numHabitaciones){
                        habitacionValida = false;
                        System.out.println("Ingrese un numero de habitacion valido");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Ingrese un numero de habitacion valido");
                    habitacionValida = false;
                }
            }while(!habitacionValida);
        return numeroHabitacion;
    }

    public static void mostrarHabitaciones(){
        for (Habitacion habitacion : habitaciones.values()) {
            System.out.println(habitacion.toString());
        }
    }

    public static void mostrarHabitaciones(String fecha){
        Hashtable<Integer, Reservacion> habitacionesNoDisponibles = Reservacion.habitacionesNoDisponibles(fecha);
        System.out.println("\nHabitaciones ocupadas en esa fecha: \n");
        for (Habitacion habitacion : habitaciones.values()) {
            if(habitacionesNoDisponibles.containsKey(habitacion.numero)){
                System.out.println(habitacion.toString());
                System.out.println("Cliente: " + habitacionesNoDisponibles.get(habitacion.getNumero()).getCliente().getNombre());
                System.out.println(habitacionesNoDisponibles.get(habitacion.getNumero()).toString());
            }
        }
    }

    public static void mostrarHabitaciones(String fechaLlegada, String fechaSalida){
        Hashtable<Integer, Reservacion> habitacionesNoDisponibles = Reservacion.habitacionesNoDisponibles(fechaLlegada, fechaSalida);
        System.out.println("\nHabitaciones disponibles en estas fechas: \n");
        for (Habitacion habitacion : habitaciones.values()) {
            if(!habitacionesNoDisponibles.containsKey(habitacion.numero)){
                System.out.println(habitacion.toString()); 
            }
        }
    }

    @Override
    public String toString() {
        return "Habitacion " + this.getNumero(); 
    }
}
