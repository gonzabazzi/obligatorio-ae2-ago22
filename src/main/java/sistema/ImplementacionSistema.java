package sistema;

import abb.AbbJugador;
import abb.NodoAbbJugador;
import dominio.Jugador;
import interfaz.Consulta;
import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;

public class ImplementacionSistema implements Sistema {

    AbbJugador abbJugador;
    Jugador jugador;


    @Override
    public Retorno inicializarSistema(int maxCentros) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");
        if (maxCentros > 5) {
            abbJugador = new AbbJugador();
            //grafoMundo = new GrafoMundo(maxAeropuertos, true);
            //matrizAsientos = new MatrizAsientos();


        } else {
            return Retorno.error1("La cantidad de centros debe ser mayor a 5.");
        }
        return ret;
    }

    @Override
    public Retorno explorarCentroUrbano(boolean[] correctas, int[] puntajes, int minimo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarJugador(String ci, String nombre,int edad, String escuela, TipoJugador tipo) {
        String retorno = abbJugador.insertarJugador(ci, nombre, edad, escuela, tipo);
        if (retorno == "1"){
            return Retorno.error1("Los campos no pueden estar vacíos");
        } else if (retorno == "2") {
            return Retorno.error2("El formato de la CI no es válido");
        } else if (retorno == "3") {
            return Retorno.error3("Ya existe un jugador registrado con esa CI");
        } else {
            return Retorno.ok("El jugador fue registrado exitosamente");
        }
    }

    @Override
    public Retorno filtrarJugadores(Consulta consulta) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarJugador(String ci) {

        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");

        if(jugador.validarCi(ci)){
            NodoAbbJugador jugadorEncontrado = abbJugador.buscarJugador(ci);
            if(jugadorEncontrado != null){
                //ret.valorInteger = abbJugador.cantIteraciones; //Variable de entorno con el contador
                ret.valorInteger = abbJugador.iteracionesAlBuscarJugador(ci); //Metodo para contar
                ret.valorString =
                        ci + ";" +
                        jugadorEncontrado.getJugador().getNombre() + ";" +
                        jugadorEncontrado.getJugador().getEdad() + ";" +
                        jugadorEncontrado.getJugador().getEscuela() + ";" +
                        jugadorEncontrado.getJugador().getTipoJugador();
                return ret;
            }else{
                return Retorno.error2("No se existe jugador con esa CI");
            }
        }else{
            return Retorno.error1("CI con formato invalido");
        }
    }

    @Override
    public Retorno listarJugadoresPorCedulaAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCedulaDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorTipo(TipoJugador unTipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCentroUrbano(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }
}
