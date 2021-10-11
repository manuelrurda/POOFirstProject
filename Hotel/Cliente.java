import java.util.Set;
import java.util.HashSet;

public class Cliente {
    private String nombre; 
    private Reservacion reservacion;

    static Set<Cliente> clientes = new HashSet<Cliente>();

    public Cliente(String nombre){
        this.nombre = nombre;
        clientes.add(this);
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setReservacion(Reservacion reservacion){
        this.reservacion = reservacion;
    }

    public Reservacion getReservacion(){
        return this.reservacion;
    }

    // Busqueda lineal del cliente dado su nombre dentro del Set de clientes.
    public static Cliente buscarCliente(String nombre){
        for(Cliente c : clientes){
            if(c.nombre.equals(nombre)){
                return c;
            }
        }
        return null;
    }

    // Tanto el metodo hashCode() como el metodo equals() son los que se
    // utilizan para verificar la igualdad de elementos dentro de un Set
    // Para que el Set clientes, no acepte clientes duplicados, se tienen
    // que sobrescribir estos metodos para solo comparar el nombre. 
    // https://stackoverflow.com/questions/14880450/java-hashset-with-a-custom-equality-criteria
    @Override
    public int hashCode() {
        return this.nombre.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // Como para sobrescribir los metodos no se puede cambiar el tipo de 
        // los parametros, es necesario utilizar el casting para tener acceso
        // a la propiedad de nombre.
        return (obj instanceof Cliente) && (((Cliente) obj).getNombre()).equals(this.nombre);
    }
}
