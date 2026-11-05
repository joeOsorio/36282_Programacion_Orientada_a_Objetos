package Reglas;
import app.Orden;

public class ReglaNot implements Regla
{
    private Regla primera;
    public ReglaNot(Regla a)
    {
        primera = a;
    }

    @Override
    public boolean aplica(Orden o)
    {
        return !primera.aplica(o);
    }
    @Override
    public void ejecuta(Orden o)
    {
        primera.ejecuta(o);
    }
    public String describe()
    {
        return String.format("{ NOT %s }",primera.describe());
    }
}

