package pelicula;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Largometraje")
public class Largometraje extends Pelicula{

    private int duracion;

    public Largometraje(){

    }

    public Largometraje(String titulo, Date estreno, double presupuesto, double recaudacion, String pais, int duracion) {

        super(titulo, estreno, presupuesto, recaudacion, pais);

        this.duracion = duracion;

    }

    public int getDuracion() {

        return duracion;

    }

    public void setDuracion(int duracion) {

        this.duracion = duracion;

    }


}
