public class SensorEstadoAlerta extends SensorEstado{

    private SensorEstadoAlerta(){};
    private static SensorEstadoAlerta instance = new SensorEstadoAlerta();
    public static SensorEstadoAlerta getInstance(){
        return instance;
    }

    public String getEstado() {
        return "Sensor Em Alerta";
    }

    public boolean normalizar(Sensor sensor) {
        sensor.setEstado(SensorEstadoSeguro.getInstance());
        return true;
    }

    public boolean desativar(Sensor sensor) {
        sensor.setEstado(SensorEstadoDesativado.getInstance());
        return true;
    }
}
