
public class SensorEstadoSeguro extends SensorEstado{

    private  SensorEstadoSeguro(){};
    private static SensorEstadoSeguro instance = new SensorEstadoSeguro();
    public static SensorEstadoSeguro getInstance(){
        return instance;
    }

    public String getEstado() {
        return "Sensor Seguro";
    }

    public boolean detectarMovimento(Sensor sensor) {
        sensor.setEstado(SensorEstadoAlerta.getInstance());
        return true;
    }

    public boolean desativar(Sensor sensor) {
        sensor.setEstado(SensorEstadoDesativado.getInstance());
        return true;
    }

}
