package inicio;

import interfaz.Consulta;
import interfaz.EstadoCamino;
import interfaz.TipoJugador;
import sistema.ImplementacionSistema;

public class Main {
    public static void main(String[] args) {

        System.out.println("Func 1- Inicializar sistema:");
        ImplementacionSistema nuevoSistema = new ImplementacionSistema();
        nuevoSistema.inicializarSistema(6);

        System.out.println("Func 3- Registrar Jugador:");
        System.out.println(nuevoSistema.registrarJugador("1.111.111-1","Nicki", 2, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("4.444.444-4","Jorge", 16, "Rural", TipoJugador.AVANZADO).valorString);
        System.out.println(nuevoSistema.registrarJugador("3.333.333-3","Laura", 9, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("5.555.555-5","Lalo", 18, "Rural", TipoJugador.INICIAL).valorString);
        System.out.println(nuevoSistema.registrarJugador("2.222.222-2","Juan", 9, "Rural", TipoJugador.AVANZADO).valorString);

        System.out.println("Func 4- Filtrar jugadores:");
        System.out.println(nuevoSistema.filtrarJugadores(Consulta.fromString("edad > 15 OR escuela = 'Felipe'")).valorString);



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
        System.out.println(nuevoSistema.registrarCentroUrbano("1", "Primer centro").valorString);
        System.out.println(nuevoSistema.registrarCentroUrbano("", "Primer centro").valorString);
        System.out.println(nuevoSistema.registrarCentroUrbano("2", "").valorString);
        System.out.println(nuevoSistema.registrarCentroUrbano("1", "Primer centro").valorString);
        System.out.println(nuevoSistema.registrarCentroUrbano("2", "Segundo centro").valorString);
        System.out.println(nuevoSistema.registrarCentroUrbano("3", "Tercero centro").valorString);
        System.out.println(nuevoSistema.registrarCentroUrbano("4", "Cuarto centro").valorString);
        System.out.println(nuevoSistema.registrarCentroUrbano("5", "Quinto centro").valorString);
        System.out.println(nuevoSistema.registrarCentroUrbano("6", "Sexto centro").valorString);
        System.out.println(nuevoSistema.registrarCentroUrbano("7", "Septimo centro").valorString);

        System.out.println("Func 10- Registrar camino:");
        System.out.println(nuevoSistema.registrarCamino("1", "2", 2.5, 4.0, 200, EstadoCamino.BUENO).valorString);
        System.out.println(nuevoSistema.registrarCamino("", "2", 2.5, 4.0, 200, EstadoCamino.BUENO).valorString);
        System.out.println(nuevoSistema.registrarCamino("1", "", 2.5, 4.0, 200, EstadoCamino.BUENO).valorString);
        System.out.println(nuevoSistema.registrarCamino("1", "2", 2.5, 4.0, 200, null).valorString);
        System.out.println(nuevoSistema.registrarCamino("1", "2", 0, 4.0, 200, EstadoCamino.BUENO).valorString);
        System.out.println(nuevoSistema.registrarCamino("1", "2", 2.5, 0, 200, EstadoCamino.BUENO).valorString);
        System.out.println(nuevoSistema.registrarCamino("1", "2", 2.5, 4.0, 0, EstadoCamino.BUENO).valorString);
        System.out.println(nuevoSistema.registrarCamino("1", "2", 2.5, 4.0, 200, EstadoCamino.BUENO).valorString);
        System.out.println(nuevoSistema.registrarCamino("2", "4", 4.5, 5.0, 400, EstadoCamino.EXCELENTE).valorString);
        System.out.println(nuevoSistema.registrarCamino("2", "1", 4.5, 5.0, 400, EstadoCamino.EXCELENTE).valorString);
        System.out.println(nuevoSistema.registrarCamino("3", "4", 4.5, 5.0, 400, EstadoCamino.EXCELENTE).valorString);
        System.out.println(nuevoSistema.registrarCamino("2", "4", 4.5, 5.0, 400, EstadoCamino.EXCELENTE).valorString);
        System.out.println(nuevoSistema.registrarCamino("4", "2", 4.5, 5.0, 400, EstadoCamino.EXCELENTE).valorString);

        System.out.println("Func 11- Actualizar camino:");
        System.out.println(nuevoSistema.actualizarCamino("1", "2", 9, 45, 20, EstadoCamino.BUENO).valorString);
        System.out.println(nuevoSistema.actualizarCamino("28", "4", 9, 45, 20, EstadoCamino.BUENO).valorString);
    }
}
