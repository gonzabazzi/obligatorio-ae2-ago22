package inicio;

import interfaz.TipoJugador;
import sistema.ImplementacionSistema;

public class Main {
    public static void main(String[] args) {

        System.out.println("Func 1- Inicializar sistema:");
        ImplementacionSistema nuevoSistema = new ImplementacionSistema();
        nuevoSistema.inicializarSistema(6);

        System.out.println("Func 3- Registrar Jugador:");
        System.out.println(nuevoSistema.registrarJugador("1.111.111-1","Nicki", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("4.444.444-4","Jorge", 9, "Rural", TipoJugador.AVANZADO).valorString);
        System.out.println(nuevoSistema.registrarJugador("3.333.333-3","Laura", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("5.555.555-5","Lalo", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("2.222.222-2","Juan", 9, "Rural", TipoJugador.AVANZADO).valorString);

        System.out.println("Func 5- Buscar jugador:");
        System.out.println(nuevoSistema.buscarJugador("8.444.444-4").valorString);
        System.out.println(nuevoSistema.buscarJugador("2.222.222-2").valorInteger + " recorridas.");

        System.out.println(nuevoSistema.registrarJugador("6.111.111-1","Nicki", 9, "Rural", TipoJugador.AVANZADO).valorString);
        System.out.println(nuevoSistema.registrarJugador("9.333.333-3","Laura", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("7.444.444-4","Jorge", 9, "Rural", TipoJugador.MEDIO).valorString);
        System.out.println(nuevoSistema.registrarJugador("8.555.555-5","Lalo", 9, "Rural", TipoJugador.MONITOR).valorString);
        System.out.println(nuevoSistema.registrarJugador("1.222.222-2","Juan", 9, "Rural", TipoJugador.INICIAL).valorString);

        System.out.println("Func 6- Listar Desc:");
        System.out.println(nuevoSistema.listarJugadoresPorCedulaDescendente().valorString);

        System.out.println("Func 7- Listar Asc:");
        System.out.println(nuevoSistema.listarJugadoresPorCedulaAscendente().valorString);

        System.out.println("Func 8- Listar Jugadores por Tipo:");
        System.out.println(nuevoSistema.listarJugadoresPorTipo(TipoJugador.AVANZADO).valorString);

        System.out.println("Func 9- Registrar centro urbano:");
        System.out.println(nuevoSistema.registrarCentroUrbano("1", "Primer centro"));
    }
}
