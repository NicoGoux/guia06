package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import died.guia06.util.Registro;

/**
 * Clase que representa un curso. Un curso se identifica por su ID y por su nombre y ciclo lectivo.
 * Un curso guarda una lista de los inscriptos actuales que tienen.
 * Un curso, al aprobarlo, otorga una cantidad de creditos definidas en el curso.
 * Un curso requiere que para inscribirnos tengamos al menos la cantidad de creditos requeridas, y que haya cupo disponible
 * @author marti
 *
 */
public class Curso {

	private Integer id;
	private String nombre;
	private Integer cicloLectivo;
	private Integer cupo; 
	private List<Alumno> inscriptos;
	private Integer creditos;
	private Integer creditosRequeridos;
	
	private Registro log;
	
	public Curso() {
		super();
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
	}

	/**
	 * Este método, verifica si el alumno se puede inscribir y si es así lo agrega al curso,
	 * agrega el curso a la lista de cursos en los que está inscripto el alumno y retorna verdadero.
	 * Caso contrario retorna falso y no agrega el alumno a la lista de inscriptos ni el curso a la lista
	 * de cursos en los que el alumno está inscripto.
	 * 
	 * Para poder inscribirse un alumno debe
	 * 		a) tener como minimo los creditos necesarios
	 *      b) tener cupo disponibles
	 *      c) puede estar inscripto en simultáneo a no más de 3 cursos del mismo ciclo lectivo.
	 * @param a
	 * @return
	 */
	
	public Boolean inscribir(Alumno a) {
		if (this.cupo>this.inscriptos.size() && a.cursandoCicloLectivo(this.cicloLectivo) &&this.creditosRequeridos<=a.getCreditos()) {
			try {
				log.registrar(this, "inscribir ",a.toString());
				a.inscripcionAceptada(this);
			}
			
			catch (IOException e) {
				return false;
			}
			
			return true;
		}
		
		else {
			return false;
		}
	}
	
	
	/**
	 * imprime los inscriptos en orden alfabetico
	 */
	public void imprimirInscriptos() {
		try {
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
			this.ordenarListaPorNombre();
			int i=1;
			for (Alumno a : this.inscriptos) {
				System.out.println("Alumno "+i+": "+a.getNombre());
				i++;
			}
		}
		catch (IOException e) { 
			
		}
	}
	
	public int getCreditos() {
		return this.creditos;
	}
	
	public int getCicloLectivo() {
		return this.cicloLectivo;
	}
	
	public void ordenarListaPorNombre() {
		this.inscriptos.sort(Comparator.comparing(Alumno::getNombre));
	}
	
	public void ordenarListaPorLegajo() {
		this.inscriptos.sort(Comparator.comparing(Alumno::getNumeroLibreta));
	}
	
	public void ordenarListaPorCreditos() {
		this.inscriptos.sort(Comparator.comparing(Alumno::getCreditos));
	}

}
