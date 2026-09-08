package ImplementacionProfesor;

public class Empleado {
    private  Info info;
    private static long id;

    Empleado(Info ni){
        info = ni;
        mi_id = ++Empleado.id;
    }

    public void setInfo(Info n){
        info = n;
    }

    public Info getInfo() {
        return info;
    }

    public static long getId() {
        return id;
    }
}
