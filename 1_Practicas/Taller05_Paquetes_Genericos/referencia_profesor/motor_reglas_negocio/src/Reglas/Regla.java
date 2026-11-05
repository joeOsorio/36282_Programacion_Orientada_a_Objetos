package Reglas;
import app.Orden;

public interface Regla
{
    public boolean aplica(Orden o);
    public void ejecuta(Orden o);
    public String describe();
}
