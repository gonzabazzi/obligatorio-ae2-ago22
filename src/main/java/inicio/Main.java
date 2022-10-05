package inicio;

import interfaz.TipoJugador;
import sistema.ImplementacionSistema;

public class Main {
    public static void main(String[] args) {

        ImplementacionSistema nuevoSistema = new ImplementacionSistema();
        nuevoSistema.inicializarSistema(6);
        System.out.println(nuevoSistema.registrarJugador("5.321.456-8","Nicki", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("4.321.456-8","Juan", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("2.321.456-8","Laura", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("1.321.456-8","Jorge", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("4.321.456-8","Lalo", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.buscarJugador("1.321.456-8").valorString);
        System.out.println(nuevoSistema.buscarJugador("2.321.456-8").valorInteger + " recorridas.");



    }
}
