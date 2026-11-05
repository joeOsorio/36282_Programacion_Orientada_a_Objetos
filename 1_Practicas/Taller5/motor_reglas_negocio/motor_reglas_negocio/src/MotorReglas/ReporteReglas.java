package MotorReglas;
import app.Orden;
public class ReporteReglas
{
    private int id;
    private int max_registros;
    private String[] registros;
    private Orden orden;
    public ReporteReglas(Orden o)
    {
        orden =o;
        max_registros =10;
        id=0;
        registros = new String[max_registros];
    }
    public void registrar(String descripcion)
    {
        if(id>=max_registros)
        {
            System.out.println("Se llego al máximo de registros: "+max_registros);
            return;
        }
        registros[id++] = descripcion;
    }
    public void reporte()
    {
        for(int i=0;i<registros.length;i++)
        {
            if(registros[i]!=null)
            System.out.println("Se aplico-> "+registros[i]);
        }

        System.out.println("===ORDEN===");
        System.out.println(
                String.format("id: %d\nCliente: %s[%s]\nOrden: %s\nTotal: %.3f\nCategoria: %s",
                    orden.getId(),orden.getCliente().getNombre(),
                    ((orden.getCliente().esPremium())?"Premium":"Normal"),
                    orden.getTitulo(),orden.getTotal(),orden.getCategoria())
                );

    }
}
