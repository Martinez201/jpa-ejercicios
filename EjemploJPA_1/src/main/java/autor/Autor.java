package autor;

import mensaje.Mensaje;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Autor {

    @Id
    @GeneratedValue
    @Column( name = "autor_id")
    private int id;

    private String nombre;

    private String apellidos;

    @Column(name="email", nullable = false, unique = true)

    private String correo;

    private String edad;

    private String telefono;

    private String direccion;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private Set<Mensaje> mensajes  = new HashSet<>();

    @Version
    private int version;

    public String getNombre() {

        return nombre;

    }

    public Autor() {

    }

    public Autor(String nombre, String apellidos, String correo, String edad, String telefono, String direccion) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.edad = edad;
        this.telefono = telefono;
        this.direccion = direccion;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;

        if (!correo.equals(autor.correo)) return false;
        if (!nombre.equals(autor.nombre)) return false;

        return true;
    }



    @Override
    public int hashCode() {

        int result = correo.hashCode();
        result = 31 * result + nombre.hashCode();
        return result;

    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", edad='" + edad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", mensajes=" + mensajes +
                '}';
    }

    public int getId() {

        return id;

    }

    public void setNombre(String nombre) {

        this.nombre = nombre;

    }

    public String getApellidos() {

        return apellidos;

    }

    public void setApellidos(String apellidos) {

        this.apellidos = apellidos;

    }

    public String getCorreo() {

        return correo;

    }

    public void setCorreo(String correo) {

        this.correo = correo;

    }

    public String getEdad() {

        return edad;

    }

    public void setEdad(String edad) {

        this.edad = edad;

    }

    public String getTelefono() {

        return telefono;

    }

    public void setTelefono(String telefono) {

        this.telefono = telefono;

    }

    public String getDireccion() {

        return direccion;

    }

    public void setDireccion(String direccion) {

        this.direccion = direccion;

    }

    public Set<Mensaje> getMensajes() {

        return mensajes;

    }

    public void setMensajes(Set<Mensaje> mensajes) {

        this.mensajes = mensajes;

    }

    public int getVersion() {

        return version;

    }

    public void setVersion(int version) {

        this.version = version;

    }
}
