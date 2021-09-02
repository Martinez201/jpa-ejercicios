package pelicula;

import critica.Critica;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pelicula  {

    @Id
    @GeneratedValue
    @Column( name = "pelicula_id")
    private int id;

    private String titulo;
    private Date estreno;
    private double presupuesto;
    private double recaudacion;
    private String pais;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Critica> criticas = new HashSet<>(); ;

    public Pelicula() {

    }

    public Pelicula(String titulo, Date estreno, double presupuesto, double recaudacion, String pais) {

        this.titulo = titulo;
        this.estreno = estreno;
        this.presupuesto = presupuesto;
        this.recaudacion = recaudacion;
        this.pais = pais;

    }

    public int getId() {

        return id;

    }

    public String getTitulo() {

        return titulo;

    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;

    }

    public Date getEstreno() {

        return estreno;

    }

    public void setEstreno(Date estreno) {

        this.estreno = estreno;

    }

    public double getPresupuesto() {

        return presupuesto;

    }

    public void setPresupuesto(double presupuesto) {

        this.presupuesto = presupuesto;

    }

    public double getRecaudacion() {

        return recaudacion;

    }

    public void setRecaudacion(double recaudacion) {

        this.recaudacion = recaudacion;

    }

    public String getPais() {

        return pais;

    }

    public void setPais(String pais) {

        this.pais = pais;

    }

    public Set<Critica> getCriticas() {

        return criticas;

    }

    public void setCriticas(Set<Critica> criticas) {

        this.criticas = criticas;

    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", estreno=" + estreno +
                ", presupuesto=" + presupuesto +
                ", recaudacion=" + recaudacion +
                ", pais='" + pais + '\'' +
                ", criticas=" + criticas +
                '}';
    }
}
