package ReglasNegocio;
import Reglas.Regla;
import app.Orden;

public class ReglaClientePremium implements Regla
{

    @Override
    public boolean aplica(Orden o)
    {
        return o.getCliente().esPremium();
    }
    @Override 
    public void ejecuta(Orden o)
    {
       //esta interfaz debería estar en su propia categoria? 
    }
    @Override 
    public String describe()
    {
        return String.format("Regla de Cliente Premium");
    }
}
