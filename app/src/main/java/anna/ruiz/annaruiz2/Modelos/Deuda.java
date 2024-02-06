package anna.ruiz.annaruiz2.Modelos;

public class Deuda {
    private String nombre;
    private float cantidad;
    private String motivo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Deuda(String nombre, float cantidad, String motivo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public Deuda() {
    }

    @Override
    public String toString() {
        return "Deuda{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}
