import java.util.Observable;
import java.util.Observer;

public class Usuario implements Observer{

    private String nome;
    private String ultimaNotificacao;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getUltimaNotificacao() {
        return this.ultimaNotificacao;
    }

    public void cadastrarAdministrador(Sensor sensor) {
        sensor.addObserver(this);
    }

    public void update(Observable sensor, Object arg1) {
        this.ultimaNotificacao = this.nome + ", mudança no sensor: " + sensor.toString();
        // System.out.println(this.ultimaNotificacao);
    }
}
