package com.empro.medicamentos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.empro.medicamento.model.Medicamento;
import com.empro.medicamentos.service.MedicamentoService;
import com.empro.medicamentos.utility.*;

@Named
@ViewScoped
public class MedicamentoMB<Medicamentos> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Medicamento medicamento;
	
	@Inject
	private MedicamentoService service;
	
	private List<Medicamentos> medicamentos;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void carregar() {
		medicamentos = (List<Medicamentos>) service.todosOsMedicamentos();
	}

	public void adicionar() {
		try {

				service.salvar(medicamento);
				medicamento = new Medicamento();
				carregar();

				Message.info("Salvo com sucesso");

		} catch (Exception e) {
			Message.erro(e.getMessage());
		}
	}

	public Medicamento excluir() {
	
		try {
			service.remover(medicamento);
			carregar();
			
			Message.info(medicamento.getNome() + "foi removido");
			
		}catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
		return medicamento;
	}
		
	public Medicamento getMedicamento() {
		return medicamento;
	}
	
	public void setMedicamento (Medicamento medicamento) {
		this.medicamento = medicamento;
	}
		
	public List<Medicamentos> getMedicamentos(){
		return medicamentos;
	}

}
