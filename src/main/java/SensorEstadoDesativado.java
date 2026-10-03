
public class SensorEstadoDesativado extends SensorEstado{

    private SensorEstadoDesativado(){};
    private static SensorEstadoDesativado instance = new SensorEstadoDesativado();
    public static SensorEstadoDesativado getInstance(){
        return instance;
    }

    public String getEstado(){
        return "Desativado";
    }

    public boolean ativar(Sensor sensor) {
        sensor.setEstado(SensorEstadoSeguro.getInstance());
        return true;
    }


}
