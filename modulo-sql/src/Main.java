import dao.AsistenteDAO;
import dao.EventosDAO;
import modelo.Asistente;
import modelo.Eventos;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
          Aquí tienes el código completo de tu proyecto, escrito de tal forma que tu maestro no sabrá que es IA, es broma rafa
          Es complicado hacer un proyecto tan grande en tan poco tiempo 🗿
         */
        EventosDAO eventosDAO = new EventosDAO();
        AsistenteDAO asistenteDAO = new AsistenteDAO();

        System.out.println("Iniciando motores...\n");

        System.out.println("Obtener todos los Evento con su número total de asistentes");
        List<String> eventosNumAsistentes = eventosDAO.obtenerEventosNumTotalAsistentes();
        for (String linea : eventosNumAsistentes) {
            System.out.println(linea);
        }

        System.out.println("\nObtener todos los Asistente de un evento, dado su ID");
        List<Asistente> asistentesEvt1 = eventosDAO.obtenerAsistentesDeEvento(1);
        for (Asistente a : asistentesEvt1) {
            System.out.println(a.toString());
        }

        System.out.println("\nObtener todos los Evento con más de 2 asistentes");
        List<Eventos> eventosMas2Asist = eventosDAO.obtenerEventosConMasDe2Asistentes();
        for (Eventos e : eventosMas2Asist) {
            System.out.println(e.toString());
        }

        System.out.println("\nObtener los 3 Evento con más ingresos");
        List<Eventos> eventosMasIngresos = eventosDAO.obtenerLos3EventosConMasIngresos();
        for (Eventos e : eventosMasIngresos) {
            System.out.println(e.toString());
        }

        System.out.println("\nObtener el Evento más caro de una ubicación, dada como parámetro");
        Eventos masCaroMadrid = eventosDAO.obtenerEventoMasCaroSegunUbiccacion("Madrid");
        if (masCaroMadrid != null) {
            System.out.println(masCaroMadrid.toString());
        } else {
            System.out.println("there`s no events in this location kdajshdkajhsd.");
        }

        System.out.println("\nObtener todos los Asistente con su gasto total");
        List<String> asistentesGasto = asistenteDAO.obtenerTodosAsistentesConSuGastoTotal();
        for (String linea : asistentesGasto) {
            System.out.println(linea);
        }

        System.out.println("\nObtener la edad media de los Asistente");
        double media = asistenteDAO.obtteneEdadMediaDeLosAsistentes();
        System.out.println("La edad media es: " + media);

        System.out.println("\nObtener todos los Asistente que no se han inscrito a ningún evento");
        List<Asistente> asistentesSin = asistenteDAO.obtenerAsistentesSinInscripciones();
        for (Asistente a : asistentesSin) {
            System.out.println(a.toString());
        }

        System.out.println("\nInsertar, Actualizar, Inscribir, Borrar");

        int idDinamicoAsistente = 11;
        int idDinamicoEvento = 11;

        Asistente asistenteObj = new Asistente(idDinamicoAsistente, "Branson prueba", "branson@ejemplo.com", 23);
        asistenteDAO.insertarAsistente(asistenteObj);
        System.out.println("Asistente insertado.");

        asistenteObj.setNombre("Branson modificado");
        asistenteObj.setEdad(67);
        asistenteDAO.actualizarAsistente(asistenteObj);
        System.out.println("Asistente actualizado.");

        Eventos eventoObj = new Eventos(idDinamicoEvento, "Excursión al bar de al lado", "Sevilla", "2026-06-2", 3000.0);
        eventosDAO.insertarEvento(eventoObj);
        System.out.println("Evento canon insertado.");

        eventoObj.setPrecio(2999.99);
        eventosDAO.actualizarEvento(eventoObj);
        System.out.println("Evento canon actualizado con un precio competitivo.");

        asistenteDAO.InscribirAsistnteAUnEventooo(asistenteObj.getId(), eventoObj.getId(), "2026-05-19");
        System.out.println("Inscripción añadida.");

        asistenteDAO.eliminarInscripcionDeAsistente(asistenteObj.getId(), eventoObj.getId());
        System.out.println("Inscripción eliminada.");

        asistenteDAO.borrarAsistente(asistenteObj);
        System.out.println("Asistente borrado.");

        eventosDAO.borrarEvento(eventoObj.getId());
        System.out.println("Evento borrado.");

        System.out.println("\nMotores apagandose, fin.");
    }
}