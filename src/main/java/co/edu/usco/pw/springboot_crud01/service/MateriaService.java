package co.edu.usco.pw.springboot_crud01.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import co.edu.usco.pw.springboot_crud01.model.Docente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usco.pw.springboot_crud01.model.Materia;
import co.edu.usco.pw.springboot_crud01.repository.MateriaRepository;

@Service
public class MateriaService implements IMateriaService {

	@Autowired
	private MateriaRepository materiaRepository;


	public List<Materia> getMateriasByDocente(String nombreDocente) {
		return materiaRepository.findByUserName(nombreDocente);
	}


	public Optional<Materia> getMateriaById(Long id) {
		return materiaRepository.findById(id);
	}

	@Override
	public List<Materia> getMateriasByUser(String user) {
		return List.of();
	}

	@Override
	public Optional<Materia> getMateriaById(long id) {
		return Optional.empty();
	}

	@Override
	public void updateMateria(Materia materia) {
		materiaRepository.save(materia);
	}


	public void addMateria(String nombre, String descripcion, int salon, Date horaInicio, Date horaFin) {

	}

	@Override
	public void deleteMateria(long id) {

	}


	public void addMateria(String nombre, String descripcion, int salon, Date horaInicio,
						   Date horaFin, Docente docenteEncargado) {
		Materia materia = new Materia();
		materia.setNombre(nombre);
		materia.setDescripcion(descripcion);
		materia.setSalon(salon);
		materia.setHoraInicio(horaInicio);
		materia.setHoraFin(horaFin);
		materia.setDocenteEncargado(docenteEncargado);
		materiaRepository.save(materia);
	}


	public void deleteMateria(Long id) {
		Optional<Materia> materia = materiaRepository.findById(id);
		materia.ifPresent(materiaRepository::delete);
	}

	@Override
	public void saveMateria(Materia materia) {
		materiaRepository.save(materia);
	}
}
