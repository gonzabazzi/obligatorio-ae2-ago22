package sistema;

import abb.AbbJugador;
import abb.NodoAbbJugador;
import dominio.CentroUrbano;
import dominio.Camino;
import dominio.Jugador;
import grafo.GrafoMapa;
import interfaz.Consulta;
import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;
import lista.Lista;
import lista.ListaImp;


import java.util.HashMap;
import java.util.Objects;

public class ImplementacionSistema implements Sistema {
    AbbJugador abbJugador;
    Jugador jugador;
    GrafoMapa mapa;

    // Creo HashMap vacío y le indico tipo de clave y valor
    HashMap<Integer, Lista<Jugador>> jugadoresPorTipo = new HashMap<>();

    @Override
    public Retorno inicializarSistema(int maxCentros) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");
        if (maxCentros > 5) {
            abbJugador = new AbbJugador();
            mapa = new GrafoMapa(maxCentros, true);
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
        Jugador aAgregar = new Jugador(ci, nombre, edad, escuela, tipo);
        String retorno = abbJugador.insertarJugador(aAgregar);
        if (Objects.equals(retorno, "1")){
            return Retorno.error1("Los campos no pueden estar vacíos");
        } else if (Objects.equals(retorno, "2")) {
            return Retorno.error2("El formato de la CI no es válido");
        } else if (Objects.equals(retorno, "3")) {
            return Retorno.error3("Ya existe un jugador registrado con esa CI");
        } else {
            agregarJugadorPorTipo(aAgregar);
            return Retorno.ok("El jugador fue registrado exitosamente");
        }
    }

    @Override
    //ToDo CONSULTAR RECORRIDA DE ANDS y ORS
    public Retorno filtrarJugadores(Consulta consulta) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");

        ret.valorString = abbJugador.filtrarJugadores(consulta);

        if(!Objects.equals(ret.valorString, "")) {
            ret.valorString = ret.valorString.substring(0, ret.valorString.length() - 1);
        }

        return ret ;
    }

    @Override
    public Retorno buscarJugador(String ci) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");

        if(Jugador.validarCi(ci)){
            Jugador jugadorEncontrado = abbJugador.buscarJugador(ci);
            if(jugadorEncontrado != null){
                ret.valorInteger = abbJugador.cantIteraciones;
                ret.valorString =
                        ci + ";" +
                        jugadorEncontrado.getNombre() + ";" +
                        jugadorEncontrado.getEdad() + ";" +
                        jugadorEncontrado.getEscuela() + ";" +
                        jugadorEncontrado.getTipoJugador().getValor();
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
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");
        ret.valorString = abbJugador.listarJugadoresPorCedulaAscendenteAbb();
        if(!Objects.equals(ret.valorString, "")) {
            ret.valorString = ret.valorString.substring(0, ret.valorString.length() - 1);
        }
        return ret;
    }

    @Override
    public Retorno listarJugadoresPorCedulaDescendente() {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");
        ret.valorString = abbJugador.listarJugadoresPorCedulaDescendenteAbb();
        if(!Objects.equals(ret.valorString, "")) {
            ret.valorString = ret.valorString.substring(0, ret.valorString.length() - 1);
        }
        return ret;
    }

    @Override
    public Retorno listarJugadoresPorTipo(TipoJugador unTipo) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");
        if(unTipo != null){
            Lista<Jugador> jugadoresPorTipo = obtenerJugadorPorTipo(unTipo);
            if(jugadoresPorTipo != null){
                for (Jugador j : jugadoresPorTipo) {
                    ret.valorString = ret.valorString +
                            j.getCedula() + ";" +
                            j.getNombre() + ";" +
                            j.getEdad() + ";" +
                            j.getEscuela() + ";" +
                            j.getTipoJugador().getValor() + "|";
                }
                ret.valorString = ret.valorString.substring(0, ret.valorString.length() - 1);
            } else {
                ret.valorString = "";
            }
        } else {
            return Retorno.error1("No se indica tipo de jugador");
        }
        return ret;
    }

    @Override
    public Retorno registrarCentroUrbano(String codigo, String nombre) {
        CentroUrbano aAgregar = new CentroUrbano(codigo, nombre);
        String retorno = mapa.insertarCentroUrbano(aAgregar);
        if (Objects.equals(retorno, "1")){
            return Retorno.error1("Máximo de registro alcanzado");
        } else if (Objects.equals(retorno, "2")) {
            return Retorno.error2("Los datos no pueden ser vacíos");
        } else if (Objects.equals(retorno, "3")) {
            return Retorno.error3("Ya existe un centro urbano registrado con ese código");
        } else {
            return Retorno.ok("El centro urbano fue registrado exitosamente");
        }
    }

    @Override
    public Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        Camino aAgregar = new Camino(codigoCentroOrigen, codigoCentroDestino, costo, tiempo, kilometros, estadoDelCamino);
        String retorno = mapa.insertarCamino(aAgregar);
        if (retorno == "1"){
            return Retorno.error1("Costo, tiempo y kilometros deben ser mayores a 0");
        } else if (retorno == "2") {
            return Retorno.error2("El código del centro de origen, centro de destino y estado del camino no pueden ser vacios");
        } else if (retorno == "3") {
            return Retorno.error3("El centro de origen no existe");
        } else if (retorno == "4") {
            return Retorno.error4("El centro de destino no existe");
        } else if (retorno == "5") {
        return Retorno.error5("Ya existe camino entre origen y destino");
        }else {
            return Retorno.ok("El camino fue registrado exitosamente");
        }
    }

    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        String retorno = mapa.actualizarCamino(codigoCentroOrigen, codigoCentroDestino, costo, tiempo, kilometros, estadoDelCamino);
        if (retorno == "1"){
            return Retorno.error1("Costo, tiempo y kilometros deben ser mayores a 0");
        } else if (retorno == "2") {
            return Retorno.error2("El código del centro de origen, centro de destino y estado del camino no pueden ser vacios");
        } else if (retorno == "3") {
            return Retorno.error3("El centro de origen no existe");
        } else if (retorno == "4") {
            return Retorno.error4("El centro de destino no existe");
        } else if (retorno == "5") {
            return Retorno.error5("No existe un camino entre origen y destino");
        }else {
            return Retorno.ok("El camino fue actualizado exitosamente");
        }
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

    //Función auxiliar para agregar jugadores por tipo
    public void agregarJugadorPorTipo(Jugador aAgregar) {
        int claveTipoJugador = aAgregar.getTipoJugador().getIndice(); // Para el jugador que deseo agregar obtengo su tipo y de su tipo el indice
        Lista<Jugador> listaTipo = jugadoresPorTipo.get(claveTipoJugador); // Obtengo los jugadores del hashmap que tienen el indice anterior
        if (listaTipo == null) {
            listaTipo = new ListaImp<>(); // Si la lista no existe la creo
        }
        listaTipo.insertar(aAgregar); // Infreto en el nodo lista al lugador de ese tipo
        jugadoresPorTipo.put(claveTipoJugador, listaTipo); // Al hash le voy a agregar para esta clave, el valor que es la lista de ese tipo
    }

    public Lista<Jugador> obtenerJugadorPorTipo (TipoJugador aBuscar) {
        return jugadoresPorTipo.get(aBuscar.getIndice());
    }
}
