import java.util.Observable;

public class Sensor extends Observable {

    private String nome;
    private SensorEstado estado;

    public Sensor() {
        this.estado = SensorEstadoSeguro.getInstance();
    }

    public Sensor(String nome) {
        this.nome = nome;
        this.estado = SensorEstadoSeguro.getInstance();
    }

    public void setEstado(SensorEstado estado) {
        this.estado = estado;
    }

    public boolean ativar () {
        setChanged();
        notifyObservers();
        return estado.ativar(this);
    }

    public boolean detectarMovimento () {
        setChanged();
        notifyObservers();
        return estado.detectarMovimento(this);
    }

    public boolean normalizar() {
        setChanged();
        notifyObservers();
        return estado.normalizar(this);
    }

    public boolean desativar() {
        setChanged();
        notifyObservers();
        return estado.desativar(this);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "nome=" + nome +
                ", estado atual=" + getNomeEstado() +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SensorEstado getEstado() {
        return estado;
    }

    public String getNomeEstado() {
        return estado.getEstado();
    }
}
