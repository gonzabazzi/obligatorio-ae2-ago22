package dominio;

public class CentroUrbano implements Comparable<CentroUrbano>{
    private String codigo;
    private String nombre;

    public CentroUrbano(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int compareTo(CentroUrbano centro){
        if(this.getCodigo().compareTo(centro.getCodigo())<0){
            return -1;
        }
        else if(centro.getCodigo().compareTo(this.getCodigo()) < 0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre + '|';
    }
}
