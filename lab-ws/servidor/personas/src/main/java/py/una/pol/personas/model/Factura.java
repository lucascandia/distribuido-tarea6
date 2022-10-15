package py.una.pol.personas.model;

import java.io.Serializable;
import java.sql.Date;

// Se creo una clase factura como lo solicita la tarea
public class Factura {
   String ruc;
   String razonSocial;
   String fecha;
   Double monto;

    public Factura() {
    }

    public Factura(String ruc, String razonSocial, String fecha, Double monto) {
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.fecha = fecha;
        this.monto = monto;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

}
