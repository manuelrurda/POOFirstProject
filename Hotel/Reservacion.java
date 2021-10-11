import java.util.ArrayList;
import java.util.Hashtable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Reservacion {

    // Para manejar fechas se utilizara la clase LocalDate
    private final static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yy");
    // La fecha nula servira para tener una referencia de si se ingreso una fecha correcta.
    static final LocalDate fechaNula = LocalDate.parse("01/01/00", fmt);
    private LocalDate fechaLlegada = fechaNula;
    private LocalDate fechaSalida = fechaNula;
    private Habitacion habitacion;
    private Cliente cliente;

    // Lista que guardara todas las reservaciones creadas
    public static ArrayList<Reservacion> reservaciones = new ArrayList<Reservacion>();
    
    public void setFechaLlegada(String fecha){
        this.fechaLlegada = LocalDate.parse(fecha, fmt);
    }

    public void setFechaSalida(String fecha){
        LocalDate temp = LocalDate.parse(fecha, fmt);
        if(temp.isBefore(this.fechaLlegada)){
            this.fechaSalida = fechaNula;
            System.out.println("\nLa fecha de salida no puede ser antes que la llegada.");
        }else{
            this.fechaSalida = LocalDate.parse(fecha, fmt);
        }
    }

    public void setHabitacion(Habitacion habitacion){
        this.habitacion = habitacion;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public LocalDate getFechaLlegada(){
        return this.fechaLlegada;
    }

    public LocalDate getFechaSalida(){
        return this.fechaSalida;
    }

    public Habitacion getHabitacion(){
        return this.habitacion;
    } 

    public Cliente getCliente(){
        return this.cliente;
    }

    public static Hashtable<Integer, Reservacion>  habitacionesNoDisponibles(String fecha){
        Hashtable<Integer, Reservacion> habitacionesNoDisponibles = new Hashtable<Integer, Reservacion>();
        LocalDate temp = LocalDate.parse(fecha, fmt);
        for(Reservacion res : Reservacion.reservaciones){
            if(temp.isAfter(res.fechaLlegada) && temp.isBefore(res.fechaSalida)){
                habitacionesNoDisponibles.put(res.getHabitacion().getNumero(), res);
            }
        }
        return habitacionesNoDisponibles;
    }

    public static Hashtable<Integer, Reservacion>  habitacionesNoDisponibles(String fechaLlegada, String fechaSalida){
        Hashtable<Integer, Reservacion> habitacionesNoDisponibles = new Hashtable<Integer, Reservacion>();
        LocalDate tempLlegada = LocalDate.parse(fechaLlegada, fmt);
        LocalDate tempSalida = LocalDate.parse(fechaSalida, fmt);
        for(Reservacion res : Reservacion.reservaciones){
            // Tal vez hay una manera mejor de hacerlo pero esto cubre los cuatro casos de que se empalmen las fechas
            // re    <---->               <---->              <---->          <->
            //   <---->                  <-->                  <---->      <---->
            if((tempLlegada.isBefore(res.fechaLlegada) && tempSalida.isAfter(res.fechaLlegada))
            || (tempLlegada.isAfter(res.fechaLlegada) && tempSalida.isBefore(res.fechaSalida))
            || (tempLlegada.isBefore(res.fechaSalida) && tempSalida.isAfter(res.fechaSalida))){
                habitacionesNoDisponibles.put(res.getHabitacion().getNumero(), res);
            }
        }
        return habitacionesNoDisponibles;
    }

    public static String validarFecha(String fecha, Scanner sc){
        LocalDate temp;
        do{
             // try-catch para manejar el caso de que se ingrese una fecha en formato equivocado
            try{
                temp = LocalDate.parse(fecha, fmt);
            }catch(DateTimeParseException e){
                temp = fechaNula;
                System.out.println("Formato de fecha invalido, intente de nuevo. -> (dd/MM/yy)");
                fecha = sc.nextLine();
            }
        }while(temp.equals(fechaNula));
        return fecha;
    }

    // Se sobrescribe el metodo to String para imprimir de forma ordenada los elementos de la clase
    @Override
    public String toString() {
        return String.format("Reservacion:\nFecha de llegada: %s\nFecha de salida: %s", this.fechaLlegada.toString(), this.fechaSalida.toString());
    }
}
