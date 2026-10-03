
public abstract class SensorEstado {

    public abstract String getEstado();

    public boolean ativar(Sensor sensor){
        return false;
    }

    public boolean detectarMovimento(Sensor sensor){
        return false;
    }

    public boolean normalizar(Sensor sensor){
        return false;
    }

    public boolean desativar(Sensor sensor){
        return false;
    }
}
