public class Mascota{
  private String nombre;
  private int edad;
  private String tipo;
  private String raza;
  private String diagnostico;
  

  public Mascota(String nombre, int edad, String tipo, String raza,String diagnostico){
    this.nombre  = nombre;
    this.edad    = edad;
    this.tipo    = tipo;
    this.raza    = raza;
    this.diagnostico = diagnostico;
  }
  
  public Mascota(){
    this("", 0, "", "", "");
  }
  /* Setters */
  public void setNombre(String nombre){this.nombre= nombre;}
  public void setEdad(int  edad){this.edad = edad;}
  public void setTipo(String tipo){this.tipo = tipo;}
  public void setRaza(String raza){this.raza = raza;}
  public void setDiagnostico(String diagnostico ){this.diagnostico = diagnostico;}
  
  /* Getters */ 
  public String getNombre(){return nombre;}
  public int getEdad(){return edad;}
  public String getTipo(){return tipo;}
  public String getRaza(){return raza;}
  public String getDiagnostico(){return diagnostico;}
  
}
