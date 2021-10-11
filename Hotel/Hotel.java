import java.util.Scanner;

public class Hotel{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        short opcion = -1;

        // Se crean las habitaciones disponibles del hotel
        Habitacion.crearHabitaciones(Habitacion.numHabitaciones);
        
        System.out.println("\nBIENVENIDO ADMINISTRADOR");
        do{
            // El bloque try-catch servira para detectar si se ingresa algun caracter que no sea un numero no se rompa el programa.
            try{
                System.out.println("\nQue operacion desea realizar...\n");
                System.out.println("1.....Registrar Cliente");
                System.out.println("2.....Reservar Habitacion");
                System.out.println("3.....Modificar Reservacion");
                System.out.println("4.....Mostrar Clientes");
                System.out.println("5.....Mostrar Reservacion");
                System.out.println("6.....Mostrar Habitaciones Ocupadas");
                System.out.println("7.....Mostrar Habitaciones Disponibles");
                System.out.println("0.....Salir");

                // Si se hace la recoleccion de datos del usuario de esta manera, no se tiene que 'limpiar' el buffer despues de ingresar un numero
                opcion = Short.valueOf(sc.nextLine());

                switch(opcion){
                    case 0:
                    break;
                    case 1:
                        Administrador.registrarCliente(sc);
                    break;
                    case 2:
                        Administrador.reservarHabitacion(sc);
                    break;
                    case 3:
                        Administrador.modificarReservacion(sc);
                    break;
                    case 4:
                        Administrador.mostrarClientes();
                    break;
                    case 5:
                        Administrador.verReservacion(sc);
                    break;
                    case 6:
                        Administrador.habitacionesOcupadas(sc);
                    break;
                    case 7:
                        Administrador.habitacionesDisponibles(sc);
                    break;
                    // Si se ingresa algun numero fuera del rango 0-7
                    default:
                        System.out.println("\nIngrese una opcion valida."); 
                    break;

                }

            }catch(NumberFormatException e){
                System.out.println("\nIngrese una opcion valida.");
            }

        }while(opcion != 0);
        System.out.println("\nHasta luego.\n");
        sc.close();
    }
}