public class ReglaNoNulo implements IValidar{
    @Override
    public boolean validar(String texto){
        return texto != null;
    }

    @Override
    public String toString(){
        return "No nulo";
    }
}
