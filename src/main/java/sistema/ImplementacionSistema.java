package sistema;

import abb.AbbJugador;
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

    /////////////////////////
    //252473 Fátima Noble
    //203859 Gonzalo Bazzi
    //159689 Sebastián Paulos
    /////////////////////////
    AbbJugador abbJugador;
    GrafoMapa mapa;

    HashMap<Integer, Lista<Jugador>> jugadoresPorTipo = new HashMap<>();

    @Override
    public Retorno inicializarSistema(int maxCentros) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");
        if (maxCentros > 5) {
            abbJugador = new AbbJugador();
            mapa = new GrafoMapa(maxCentros, true);
        } else {
            return Retorno.error1("La cantidad de centros debe ser mayor a 5.");
        }
        return ret;
    }

    @Override
    public Retorno explorarCentroUrbano(boolean[] correctas, int[] puntajes, int minimo) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");

        if (correctas == null || puntajes == null) {
            return Retorno.error1("Alguno de los parámetros no es correcto");
        } else if (correctas.length < 3 || puntajes.length < 3) {
            return Retorno.error2("No puede haber menos de 3 respuestas");
        } else if (correctas.length != puntajes.length) {
            return Retorno.error3("Debe haber la misma cantidad de respuestas y puntajes");
        } else if (minimo <= 0) {
            return Retorno.error4("El puntaje mínimo tiene que ser mayor a 0");
        } else {
            int resultadoExploracion = this.resultadoDeExplorarCentro(correctas, puntajes);
            if (resultadoExploracion >= minimo) {
                ret.valorString = "pasa";
            } else {
                ret.valorString = "no pasa";
            }
            ret.valorInteger = resultadoExploracion;
        }

        return ret;
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
    public Retorno filtrarJugadores(Consulta consulta) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");
        if(consulta != null){
            ret.valorString = abbJugador.filtrarJugadores(consulta);
            if(!Objects.equals(ret.valorString, "")) {
                ret.valorString = ret.valorString.substring(0, ret.valorString.length() - 1);
            }
        } else {
            return Retorno.error1("La consulta es vacía");
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
        if (Objects.equals(retorno, "1")){
            return Retorno.error1("Costo, tiempo y kilometros deben ser mayores a 0");
        } else if (Objects.equals(retorno, "2")) {
            return Retorno.error2("El código del centro de origen, centro de destino y estado del camino no pueden ser vacios");
        } else if (Objects.equals(retorno, "3")) {
            return Retorno.error3("El centro de origen no existe");
        } else if (Objects.equals(retorno, "4")) {
            return Retorno.error4("El centro de destino no existe");
        } else if (Objects.equals(retorno, "5")) {
        return Retorno.error5("Ya existe camino entre origen y destino");
        }else {
            return Retorno.ok("El camino fue registrado exitosamente");
        }
    }

    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        String retorno = mapa.actualizarCamino(codigoCentroOrigen, codigoCentroDestino, costo, tiempo, kilometros, estadoDelCamino);
        if (Objects.equals(retorno, "1")){
            return Retorno.error1("Costo, tiempo y kilometros deben ser mayores a 0");
        } else if (Objects.equals(retorno, "2")) {
            return Retorno.error2("El código del centro de origen, centro de destino y estado del camino no pueden ser vacios");
        } else if (Objects.equals(retorno, "3")) {
            return Retorno.error3("El centro de origen no existe");
        } else if (Objects.equals(retorno, "4")) {
            return Retorno.error4("El centro de destino no existe");
        } else if (Objects.equals(retorno, "5")) {
            return Retorno.error5("No existe un camino entre origen y destino");
        }else {
            return Retorno.ok("El camino fue actualizado exitosamente");
        }
    }

    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");
        if (cantidad < 0) {
            return Retorno.error1("La cantidad de saltos debe ser mayor o igual a 0.");
        }
        String centrosPorCantidadSaltos = mapa.listadoCentrosPorCantSaltos(codigoCentroOrigen, cantidad);
        if (centrosPorCantidadSaltos.equals("-1")) {
            return Retorno.error2("No existe un centro con ese código.");
        } else {
            ret.valorString = centrosPorCantidadSaltos.substring(0, centrosPorCantidadSaltos.length() - 1);
            return ret;
        }
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");

        int viajeConCostoMinimoKilometros = mapa.viajeConCostoMinimo(codigoCentroOrigen, codigoCentroDestino);
        if (viajeConCostoMinimoKilometros == -4) {
           return Retorno.error1("Los codigos no pueden ser vacios");
        } else if (viajeConCostoMinimoKilometros == -3) {
            return Retorno.error2("No hay camino entre origen y destino.");
        } else if (viajeConCostoMinimoKilometros == -1) {
            return Retorno.error3("No existe centro de origen.");
        } else if (viajeConCostoMinimoKilometros == -2) {
            return Retorno.error4("No existe centro de destino.");

        } else {
            String auxiliar = mapa.getListaCentro().toString();
            ret.valorString = auxiliar.substring(0, auxiliar.length() - 1);
            ret.valorInteger = viajeConCostoMinimoKilometros;
            return ret;
        }
    }

    @Override
    public Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, "");

        int viajeConCostoMinimoMonedas = mapa.viajeConCostoMinimoMonedas(codigoCentroOrigen, codigoCentroDestino);
        if (viajeConCostoMinimoMonedas == -4) {
            return Retorno.error1("Los codigos no pueden ser vacios");
        } else if (viajeConCostoMinimoMonedas == -3) {
            return Retorno.error2("No hay camino entre origen y destino.");
        } else if (viajeConCostoMinimoMonedas == -1) {
            return Retorno.error3("No existe centro de origen.");
        } else if (viajeConCostoMinimoMonedas == -2) {
            return Retorno.error4("No existe centro de destino.");
        } else {
            String auxiliar = mapa.getListaCentro().toString();
            ret.valorString = auxiliar.substring(0, auxiliar.length() - 1);
            ret.valorInteger = viajeConCostoMinimoMonedas;
            return ret;
        }
    }

    public void agregarJugadorPorTipo(Jugador aAgregar) {
        int claveTipoJugador = aAgregar.getTipoJugador().getIndice();
        Lista<Jugador> listaTipo = jugadoresPorTipo.get(claveTipoJugador);
        if (listaTipo == null) {
            listaTipo = new ListaImp<>();
        }
        listaTipo.insertar(aAgregar);
        jugadoresPorTipo.put(claveTipoJugador, listaTipo);
    }

    public Lista<Jugador> obtenerJugadorPorTipo (TipoJugador aBuscar) {
        return jugadoresPorTipo.get(aBuscar.getIndice());
    }

    private int resultadoDeExplorarCentro(boolean[] correctas, int[] puntajes) {
        int puntosTotales = 0;
        int cantCorrectasSeguidas = 0;

        for (int i = 0; i < correctas.length; i++) {
            if (correctas[i]) {
                cantCorrectasSeguidas++;
                puntosTotales += puntajes[i];
            } else {
                cantCorrectasSeguidas = 0;
            }

            if (cantCorrectasSeguidas == 3) {
                puntosTotales += 3;
            }

            if (cantCorrectasSeguidas == 4) {
                puntosTotales += 5;
            }

            if (cantCorrectasSeguidas >= 5) {
                puntosTotales += 8;
            }

        }

        return puntosTotales;
    }
}
