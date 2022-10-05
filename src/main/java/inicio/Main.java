package inicio;

import interfaz.TipoJugador;
import sistema.ImplementacionSistema;

public class Main {
    public static void main(String[] args) {

        ImplementacionSistema nuevoSistema = new ImplementacionSistema();
        nuevoSistema.inicializarSistema(6);
        System.out.println(nuevoSistema.registrarJugador("1.111.111-1","Nicki", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("4.444.444-4","Jorge", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("3.333.333-3","Laura", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("5.555.555-5","Lalo", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("2.222.222-2","Juan", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.buscarJugador("8.444.444-4").valorString);
        System.out.println(nuevoSistema.buscarJugador("2.222.222-2").valorInteger + " recorridas.");


        System.out.println(nuevoSistema.registrarJugador("6.111.111-1","Nicki", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("9.333.333-3","Laura", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("7.444.444-4","Jorge", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("8.555.555-5","Lalo", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("1.222.222-2","Juan", 9, "Rural", TipoJugador.INICIAL).valorString);

        System.out.println(nuevoSistema.buscarJugador("2.222.222-2").valorInteger + " recorridas.");
        System.out.println(nuevoSistema.buscarJugador("1.222.222-2").valorInteger + " recorridas.");

    }
}
