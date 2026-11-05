package app;
import Reglas.ReglaAnd;
import Reglas.ReglaOr;
import Reglas.ReglaNot;
import Reglas.Regla;
import ReglasNegocio.*;
import MotorReglas.MotorReglas;
import MotorReglas.ReporteReglas;

/*
 * TODO: Agregar reglas de negocio para
 *          * free shipping
 *          * descuento doble si es premium
 *          * regla de categoria
 *
 */          

public class Main
{
    public void main(String[] args)
    {
        Cliente c1 = new Cliente("Omar",false);
        Cliente c2 = new Cliente("Jessy",true);
        
        Orden o1  = new Orden("Test1", c1, Orden.Categoria.VENTA, 100.f);
        Orden o2  = new Orden("Test2");
        o2.setCliente(c2);
        o2.setCategoria(Orden.Categoria.COMPRA);
        o2.setTotal(200.f);

        Orden[] ordenes ={o1,o2};

        Regla[] reglas ={
            new ReglaAnd(new ReglaDescuento(0.2f), new ReglaCantidadMinima(95.3f)),
            new ReglaOr(new ReglaClientePremium(), new ReglaDescuento(0.5f)),
            new ReglaAnd(new ReglaNot(new ReglaClientePremium()), new ReglaDescuento(1.f))
        };

        MotorReglas motor = new MotorReglas(reglas);

        for(Orden orden: ordenes)
        {
            ReporteReglas reportereglas = motor.procesar(orden);
            reportereglas.reporte();
        }



    }
}
