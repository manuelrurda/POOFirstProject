import java.util.Scanner;

public class Administrador {


    // Se pasa el scanner para poder reutilizar el mismo scanner en todo el programa
    public static void registrarCliente(Scanner sc){
        System.out.println("\nIngresa el nombre del nuevo cliente:");
        new Cliente(sc.nextLine());
    }

    public static void mostrarClientes(){
        if(Cliente.clientes.isEmpty()){
            System.out.println("\nNO HAY CLIENTES REGISTRADOS");
        }else{
            int i = 1;
            for (Cliente cliente : Cliente.clientes) {
                System.out.println("\n" + i + ". Cliente: " + cliente.getNombre());
                if(cliente.getReservacion() != null){
                    imprimirReservacion(cliente);
                }
                i++;
            }
        }
    }

    public static void reservarHabitacion(Scanner sc){
        System.out.println("\nIngrese el nombre del cliente para la reservacion");
        Cliente cliente = Cliente.buscarCliente(sc.nextLine());
        if (cliente != null){
            if (cliente.getReservacion() != null){
                System.out.println("Este cliente ya tiene una reservacion.");
            }else{
                reservar(sc, cliente);
                Reservacion.reservaciones.add(cliente.getReservacion());
            }
        }else{
            System.out.println("EL CLIENTE INGRESADO NO HA SIDO REGISTRADO");
        }
    }

    public static void modificarReservacion(Scanner sc){
        System.out.println("\nIngrese el nombre del cliente para modificar la reservacion");
        Cliente cliente = Cliente.buscarCliente(sc.nextLine());
        if (cliente != null){
            imprimirReservacion(cliente);
            if (cliente.getReservacion() != null){
                reservar(sc, cliente);
            }
        }else{
            System.out.println("EL CLIENTE INGRESADO NO HA SIDO REGISTRADO");
        }
    }

    public static void verReservacion(Scanner sc){
        System.out.println("\nIngrese el nombre del cliente");
        Cliente cliente = Cliente.buscarCliente(sc.nextLine());
        if (cliente != null){
            imprimirReservacion(cliente);
        }else{
            System.out.println("EL CLIENTE INGRESADO NO HA SIDO REGISTRADO");
        }
    }

    public static void habitacionesOcupadas(Scanner sc){
        System.out.println("\nIngrese la fecha a consultar (dd/MM/yy)");
        String fecha = Reservacion.validarFecha(sc.nextLine(), sc);
        Habitacion.mostrarHabitaciones(fecha);
    }

    public static void habitacionesDisponibles(Scanner sc){
        System.out.println("\nIngrese la fecha de llegada (dd/MM/yy)");
        String fechaLegada = Reservacion.validarFecha(sc.nextLine(), sc);
        System.out.println("\nIngrese la fecha de salida (dd/MM/yy)");
        String fechaSalida = Reservacion.validarFecha(sc.nextLine(), sc);
        Habitacion.mostrarHabitaciones(fechaLegada, fechaSalida);
    }

    // Como el acto de reservar se utiliza para una nueva reservacion y para
    // modificar una reservacion, para evitar repetir codigo se definio este
    // metodo, el cual es llamado tanto por reservarHabitacion() como
    // por modificarReservacion
    private static void reservar(Scanner sc, Cliente cliente){
        Reservacion res = new Reservacion();

        System.out.println("Ingresa la fecha de llegada (dd/MM/yy)");
        String fechaLlegada = Reservacion.validarFecha(sc.nextLine(), sc);
        res.setFechaLlegada(fechaLlegada);
        String fechaSalida;
        do{
            System.out.println("Ingresa la fecha de salida (dd/MM/yy)");
            fechaSalida = Reservacion.validarFecha(sc.nextLine(), sc);
            res.setFechaSalida(fechaSalida);
        }while(res.getFechaSalida().equals(Reservacion.fechaNula));

        Habitacion.mostrarHabitaciones(fechaLlegada, fechaSalida);
        System.out.println("Que habitacion desea reservar?");
        // Validar el numero de habitacion
        int numeroHabitacion = Habitacion.validarNumeroHabitacion(sc, Habitacion.numHabitaciones);
        Habitacion habitacion = Habitacion.habitaciones.get(numeroHabitacion);

        // Asociar Cliente <-> Reservacion <-> Habitacion
        res.setCliente(cliente);
        cliente.setReservacion(res);
        res.setHabitacion(habitacion);
        habitacion.getReservaciones().add(res);
    }

    private static void imprimirReservacion(Cliente cliente){
        if (cliente != null){
            if(cliente.getReservacion() != null){
                System.out.println("\nCliente:\n" + cliente.getNombre());
                System.out.println(cliente.getReservacion().toString());
                System.out.println(cliente.getReservacion().getHabitacion().toString());
            }else{
                System.out.println("\n" + cliente.getNombre() + ", used no tienen ninguna reservacion");
            }
        }else{
            System.out.println("EL CLIENTE INGRESADO NO HA SIDO REGISTRADO");
        }
    }
}
