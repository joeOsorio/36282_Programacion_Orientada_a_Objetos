package app;

public class Orden
{
    public static enum Categoria
    {
        COMPRA,
        VENTA,
        MANTENIMIENTO,
        DEVOLUCION,
        NONE;
        @Override
        public String toString()
        {
            return name();
        }
    };

    private static long id_cont=0;
    private long id;
    private float total;
    private Cliente cliente;
    private Orden.Categoria categoria;
    private String titulo;

    public Orden(String titulo, Cliente cliente, Orden.Categoria categoria, float total)
    {
        id = ++Orden.id_cont;
        this.titulo = titulo;
        this.cliente = cliente;
        this.categoria =categoria;
        this.total = total;
    }

    public Orden(String titulo)
    {
        id=++Orden.id_cont;
        this.titulo=titulo;
        cliente=null;
        categoria=Orden.Categoria.NONE;
        total =0;
    }

    public long getId(){return id;}
    public void setTitulo(String t){titulo = t;}
    public String getTitulo(){ return titulo;}
    public void setCliente(Cliente t){cliente = t;}
    public Cliente getCliente(){ return cliente;}
    public void setTotal(float t){total = t;}
    public float getTotal(){ return total;}
    public void setCategoria(Orden.Categoria t){categoria = t;}
    public Orden.Categoria getCategoria(){return categoria;};

    public void aplicarDescuento(float porcentaje)
    {
        total -= (total*porcentaje);
        if (total <= 0)
            total = 0.f;
    }
}
