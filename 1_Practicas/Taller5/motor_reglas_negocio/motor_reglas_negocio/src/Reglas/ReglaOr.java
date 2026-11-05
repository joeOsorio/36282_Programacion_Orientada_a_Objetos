package Reglas;
import app.Orden;

public class ReglaOr implements Regla
{
    private Regla primera;
    private Regla segunda;
    public ReglaOr(Regla a, Regla b)
    {
        primera = a;
        segunda = b;
    }


    @Override
    public boolean aplica(Orden o)
    {
        return (primera.aplica(o) || segunda.aplica(o));
    }
    @Override
    public void ejecuta(Orden o)
    {
        primera.ejecuta(o);
        segunda.ejecuta(o);
    }
    public String describe()
    {
        return String.format("{ %s OR %s }",primera.describe(),segunda.describe());
    }
}
