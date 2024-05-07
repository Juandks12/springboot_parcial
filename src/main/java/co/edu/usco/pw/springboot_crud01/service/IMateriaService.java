package co.edu.usco.pw.springboot_crud01.service;

import co.edu.usco.pw.springboot_crud01.model.Materia;

import java.util.Optional;

public interface IMateriaService {
    void saveMateria(Materia materia);

    void deleteMateria(long id);

    void updateMateria(Materia materia);

    Object getMateriasByUser(String user);

    Optional<Materia> getMateriaById(long id);
}
