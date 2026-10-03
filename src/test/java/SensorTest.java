
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SensorTest {

    Sensor sensor;

    @BeforeEach
    public void setUp() {
        sensor = new Sensor();
    }

    // Sensor Alerta

    @Test
    public void naoDeveAtivarSensorEmAlerta() {
        sensor.setEstado(SensorEstadoAlerta.getInstance());
        assertFalse(sensor.ativar());
    }

    @Test
    public void naoDeveDetectarMovimentosQuandoSensorEmAlerta() {
        sensor.setEstado(SensorEstadoAlerta.getInstance());
        assertFalse(sensor.detectarMovimento());
    }

    @Test
    public void deveDesativarSensorEmAlerta() {
        sensor.setEstado(SensorEstadoAlerta.getInstance());
        assertTrue(sensor.desativar());
        assertEquals(SensorEstadoDesativado.getInstance(), sensor.getEstado());
    }

    @Test
    public void deveNormalizarSensorEmAlerta() {
        sensor.setEstado(SensorEstadoAlerta.getInstance());
        assertTrue(sensor.normalizar());
        assertEquals(SensorEstadoSeguro.getInstance(), sensor.getEstado());
    }

    // Sensor Seguro

    @Test
    public void naoDeveAtivarSensorSeguro() {
        sensor.setEstado(SensorEstadoSeguro.getInstance());
        assertFalse(sensor.ativar());
    }

    @Test
    public void naoDeveNormalizarSensorSeguro() {
        sensor.setEstado(SensorEstadoSeguro.getInstance());
        assertFalse(sensor.normalizar());
    }

    @Test
    public void deveDesativarSensorSeguro() {
        sensor.setEstado(SensorEstadoSeguro.getInstance());
        assertTrue(sensor.desativar());
        assertEquals(SensorEstadoDesativado.getInstance(), sensor.getEstado());
    }

    @Test
    public void deveDetectarMovimentoQuandoSensorSeguro() {
        sensor.setEstado(SensorEstadoSeguro.getInstance());
        assertTrue(sensor.detectarMovimento());
        assertEquals(SensorEstadoAlerta.getInstance(), sensor.getEstado());
    }

    // Sensor Desativado

    @Test
    public void deveAtivarSensorDesativado() {
        sensor.setEstado(SensorEstadoDesativado.getInstance());
        assertTrue(sensor.ativar());
        assertEquals(SensorEstadoSeguro.getInstance(), sensor.getEstado());
    }

    @Test
    public void naoDeveNormalizarQuandoSensorDesativado() {
        sensor.setEstado(SensorEstadoDesativado.getInstance());
        assertFalse(sensor.normalizar());
    }

    @Test
    public void naoDeveDetectarMovimentoQuandoSensorDesativado() {
        sensor.setEstado(SensorEstadoDesativado.getInstance());
        assertFalse(sensor.detectarMovimento());
    }

    @Test
    public void naoDeveDEsativarSensorDesativado() {
        sensor.setEstado(SensorEstadoDesativado.getInstance());
        assertFalse(sensor.desativar());
    }


    // Observer

    @Test
    void deveNotificarUmUsuario() {
        Sensor sensor = new Sensor("Sensor Garagem");
        Usuario usuario = new Usuario("Usuario 1");
        usuario.cadastrarAdministrador(sensor);
        sensor.detectarMovimento();
        assertEquals("Usuario 1, mudança no sensor: Sensor{nome=Sensor Garagem, estado atual=Sensor Seguro}", usuario.getUltimaNotificacao());
    }

    @Test
    void deveNotificarUsuarios() {
        Sensor sensor = new Sensor("Sensor Garagem");
        Usuario usuario1 = new Usuario("Usuario 1");
        Usuario usuario2 = new Usuario("Usuario 2");
        usuario1.cadastrarAdministrador(sensor);
        usuario2.cadastrarAdministrador(sensor);
        sensor.detectarMovimento();
        assertEquals("Usuario 1, mudança no sensor: Sensor{nome=Sensor Garagem, estado atual=Sensor Seguro}", usuario1.getUltimaNotificacao());
        assertEquals("Usuario 2, mudança no sensor: Sensor{nome=Sensor Garagem, estado atual=Sensor Seguro}", usuario2.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarUsuario() {
        Sensor sensor = new Sensor("Sensor Cozinha");
        Usuario usuario = new Usuario("Usuario 1");
        sensor.detectarMovimento();
        assertEquals(null, usuario.getUltimaNotificacao());
    }

    @Test
    void deveNotificarUsuarioSensorGaragem() {
        Sensor sensorGaragem = new Sensor("Sensor Garagem");
        Sensor sensorCozinha = new Sensor("Sensor Cozinha");
        Usuario usuario1 = new Usuario("Usuario 1");
        Usuario usuario2= new Usuario("Usuario 2");
        usuario1.cadastrarAdministrador(sensorGaragem);
        usuario2.cadastrarAdministrador(sensorCozinha);
        sensorGaragem.detectarMovimento();
        assertEquals("Usuario 1, mudança no sensor: Sensor{nome=Sensor Garagem, estado atual=Sensor Seguro}", usuario1.getUltimaNotificacao());
        assertEquals(null, usuario2.getUltimaNotificacao());
    }

}
