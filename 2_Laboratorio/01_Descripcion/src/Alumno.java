/* ********************************************
 * Laboratorio 1: Primeros pasos sobre clases con setter y getter.
 * @Author: J03_0$0
 * @Date: Agust/2026
 * *********************************************/
public class Alumno{
	/* Atributos principales de la clase.
	 * El ancapsulamiento u ocultacion de atributos se lo damos con el private.*/
	private String nombres;
	private String apellidos;
	private int matricula;
	private double calificacion1;
	private double calificacion2;
	private double  calificacion3;

	/*--------------- Setters -------------------------------------*/
	public void setNombre(String nombre){
		this.nombres = nombre;
	}

	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}

	public void setMatricula(int matricula){
		this.matricula = matricula;
	}

	public void setCalificacion1(double calificacion){
		calificacion1 = calificacion;
	}
	
	public void setCalificacion2(double calificacion){
		calificacion2 = calificacion;
	}

	public void setCalificacion3(double calificacion){
		calificacion3 = calificacion;
	}
	/*--------------- Getters --------------------------------------*/
	public String getNombres(){
		return nombres;
	}

	public String getApellidos(){
		return apellidos;
	}
	
	public int getMatricula(){
		return matricula;
	}

	public double getCalificacion1(){
		return calificacion1;
	}

	public double getCalificacion2(){
		return calificacion2;
	}

	public double getCalificacion3(){
		return calificacion3;
	}
	/* Metodo caracteristico de la clas alumno para calcuar el promedio*/
	public double promedio(){
		return (calificacion1 + calificacion2 + calificacion3)/3;
	}
}
