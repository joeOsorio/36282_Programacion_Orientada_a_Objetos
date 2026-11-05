package app;

public class Cliente
{
    private String nombre;
    private boolean premium;
    public Cliente(String nombre, boolean premium)
    {
        this.nombre = nombre;
        this.premium = premium;
    }
    public void setNombre(String n)
    {
        nombre = n;
    }
    public void setPremium(boolean n)
    {
        premium = n;
    }
    public String getNombre()
    {
        return nombre;
    }
    public boolean esPremium()
    {
        return premium;
    }
}
