package MotorReglas;
import app.Orden;
import Reglas.Regla;

public class MotorReglas
{
    private Regla[] reglas;
    public MotorReglas(Regla[] r)
    {
        reglas = r;
    }
    public ReporteReglas procesar(Orden orden)
    {
        ReporteReglas reporte = new ReporteReglas(orden);

        for(var regla : reglas)
        {
            if(regla.aplica(orden))
            {
                regla.ejecuta(orden);
                reporte.registrar(regla.describe());
            }
        }
        return reporte;
    }
}
