package ReglasNegocio;
import Reglas.Regla;
import app.Orden;

public class ReglaCantidadMinima implements Regla
{
    private float minima;
    public ReglaCantidadMinima(float m)
    {
        minima = m;
    }
    @Override
    public boolean aplica(Orden o)
    {
        return o.getTotal() >= minima;
    }
    @Override 
    public void ejecuta(Orden o)
    {
       //esta interfaz debería estar en su propia categoria? 
    }
    @Override 
    public String describe()
    {
        return String.format("Regla de Cantidad Mínima: [x >= %.3f]",minima);
    }
}



