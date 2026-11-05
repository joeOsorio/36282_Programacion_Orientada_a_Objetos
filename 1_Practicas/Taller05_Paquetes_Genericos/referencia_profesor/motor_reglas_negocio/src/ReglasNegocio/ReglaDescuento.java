package ReglasNegocio;
import Reglas.Regla;
import app.Orden;

public class ReglaDescuento implements Regla
{
    private float descuento;

    public ReglaDescuento(float porcentaje)
    {
        descuento = porcentaje;
    }

    @Override
    public boolean aplica(Orden o)
    {
        //si se usa la regla es que se quiere aplicar descuento por eso true
        return true;
    }
    @Override 
    public void ejecuta(Orden o)
    {
        o.aplicarDescuento(descuento);
    }
    @Override 
    public String describe()
    {
        return String.format("Regla de Descuento: [%.3f]",
                descuento);
    }
}
