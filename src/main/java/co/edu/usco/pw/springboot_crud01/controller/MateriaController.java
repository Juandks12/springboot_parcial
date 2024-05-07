package co.edu.usco.pw.springboot_crud01.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.edu.usco.pw.springboot_crud01.model.Materia;
import co.edu.usco.pw.springboot_crud01.service.IMateriaService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MateriaController {

	@Autowired
	private IMateriaService materiaService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-materias", method = RequestMethod.GET)
	public String showMaterias(ModelMap model) {
		String user = getLoggedInUserName(model);
		model.put("materias", materiaService.getMateriasByUser(user));
		return "list-materias";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	@RequestMapping(value = "/add-materia", method = RequestMethod.GET)
	public String showAddMateriaPage(ModelMap model) {
		model.addAttribute("materia", new Materia());
		return "materia";
	}

	@RequestMapping(value = "/add-materia", method = RequestMethod.POST)
	public String addMateria(ModelMap model, @Valid Materia materia, BindingResult result) {

		if (result.hasErrors()) {
			return "materia";
		}

		materiaService.saveMateria(materia);
		return "redirect:/list-materias";
	}

	@RequestMapping(value = "/delete-materia", method = RequestMethod.GET)
	public String deleteMateria(@RequestParam long id) {
		materiaService.deleteMateria(id);
		return "redirect:/list-materias";
	}

	@RequestMapping(value = "/update-materia", method = RequestMethod.GET)
	public String showUpdateMateriaPage(@RequestParam long id, ModelMap model) {
		Materia materia = materiaService.getMateriaById(id).orElseThrow(() -> new IllegalArgumentException("Invalid materia Id:" + id));
		model.put("materia", materia);
		return "materia";
	}

	@RequestMapping(value = "/update-materia", method = RequestMethod.POST)
	public String updateMateria(ModelMap model, @Valid Materia materia, BindingResult result) {

		if (result.hasErrors()) {
			return "materia";
		}

		materiaService.updateMateria(materia);
		return "redirect:/list-materias";
	}
}