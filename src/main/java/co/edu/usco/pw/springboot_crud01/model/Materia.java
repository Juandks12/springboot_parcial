package co.edu.usco.pw.springboot_crud01.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "materias")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 30, nullable = false)
	private String nombre;

	@Column(length = 100)
	private String descripcion;

	@Column(nullable = false)
	private int salon;

	@Column(nullable = false)
	private Date horaInicio;

	@Column(nullable = false)
	private Date horaFin;

	public Materia(Long id, String descripcion, String nombre, int salon, Date horaInicio, Date horaFin, Docente docenteEncargado) {
		this.id = id;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.salon = salon;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.docenteEncargado = docenteEncargado;
	}

	public Materia() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getSalon() {
		return salon;
	}

	public void setSalon(int salon) {
		this.salon = salon;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public Docente getDocenteEncargado() {
		return docenteEncargado;
	}

	public void setDocenteEncargado(Docente docenteEncargado) {
		this.docenteEncargado = docenteEncargado;
	}

	@ManyToOne
	@JoinColumn(name = "docente_id")
	private Docente docenteEncargado;

}

