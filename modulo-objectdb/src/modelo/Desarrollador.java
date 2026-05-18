package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Desarrollador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private int anyosExperiencia;
    private double salario;
    private List<Proyecto> proyectos;

    public Desarrollador(String nombre, int anyosExperiencia, double salario) {
        this.nombre = nombre;
        this.anyosExperiencia = anyosExperiencia;
        this.salario = salario;
    }

    public Desarrollador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnyosExperiencia() {
        return anyosExperiencia;
    }

    public void setAnyosExperiencia(int anyosExperiencia) {
        this.anyosExperiencia = anyosExperiencia;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public String toString() {
        return "Desarrollador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", anyosExperiencia=" + anyosExperiencia +
                ", salario=" + salario +
                ", proyectos=" + proyectos +
                '}';
    }
}
