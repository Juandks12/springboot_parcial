package co.edu.usco.pw.springboot_crud01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usco.pw.springboot_crud01.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long>{
	List<Materia> findByUserName(String user);
}
