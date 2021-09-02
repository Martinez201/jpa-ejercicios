package critica;

import pelicula.Pelicula;

import javax.persistence.*;

@Entity
public class Critica {
    @Id
    @GeneratedValue
    @Column( name = "critica_id")
    private int id;

    private String critico;
    private String texto;
    private int valoracion;

    @ManyToOne
    @JoinColumn(name = "pelicula" , nullable = false)
    private Pelicula pelicula;

    public Critica() {

    }

    public Critica(String critico, String texto, int valoracion) {

        this.critico = critico;
        this.texto = texto;
        this.valoracion = valoracion;

    }

    public int getId() {

        return id;

    }

    public String getCritico() {

        return critico;

    }

    public void setCritico(String critico) {

        this.critico = critico;

    }

    public String getTexto() {

        return texto;

    }

    public void setTexto(String texto) {

        this.texto = texto;

    }

    public int getValoracion() {

        return valoracion;

    }

    public void setValoracion(int valoracion) {

        this.valoracion = valoracion;

    }

    public Pelicula getPelicula() {

        return pelicula;

    }

    public void setPelicula(Pelicula pelicula) {

        this.pelicula = pelicula;

    }

    @Override
    public String toString() {
        return "Critica{" +
                "id=" + id +
                ", critico='" + critico + '\'' +
                ", texto='" + texto + '\'' +
                ", valoracion=" + valoracion +
                '}';
    }
}
