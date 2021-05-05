package died.guia06;

import java.util.ArrayList;
import java.util.List;


public class Alumno implements Comparable<Alumno> {

	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando;
	private List<Curso> aprobados;
	private int creditos;
	
	public Alumno(String nombre, Integer nroLibreta, List<Curso> cursando, List<Curso> aprobados, int creditos) {
		this.nombre = nombre;
		this.nroLibreta = nroLibreta;
		this.cursando = new ArrayList<Curso>(1);
		this.aprobados = new ArrayList<Curso>(1);
		this.creditos = 0;
	}

	public boolean equals(Alumno unAlumno) {
		if (this.nroLibreta==unAlumno.nroLibreta) {
			return true;
		}
		else {
			return false;
		}
	}

	public int creditosObtenidos() {
		return creditos;
	}

	public void aprobar(Curso c) {
		for (Curso unCurso : this.cursando) {
			if (unCurso==c) {
				this.cursando.remove(c);
				this.aprobados.add(c);
				this.creditos+=c.getCreditos();
			}
			else {
				System.out.println("El alumno no esta cursando el curso indicado");
			}
		}
	}

	public void inscripcionAceptada(Curso c) {
			this.cursando.add(c);
	}

	@Override
	public int compareTo(Alumno o) {
		return this.nombre.compareToIgnoreCase(o.nombre);
	}
	
	public boolean cursandoCicloLectivo(Integer c) {
		int i=0;
		for (Curso unCurso : this.cursando) {
			if (unCurso.getCicloLectivo()==c) {
				i++;
			}
		}
		if (i>=3) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int getNumeroLibreta() {
		return this.nroLibreta;
	}
	
	public int getCreditos() {
		return this.creditos;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
