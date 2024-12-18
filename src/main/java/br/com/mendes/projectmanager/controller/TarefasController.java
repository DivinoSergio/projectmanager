package br.com.mendes.projectmanager.controller;

import java.util.List;

import br.com.mendes.projectmanager.model.Tarefa;
import br.com.mendes.projectmanager.service.TarefaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class TarefasController {

	@Inject
	private TarefaService tarefaService;
	
	private List<Tarefa> todosTarefas;
	private Tarefa tarefa = new Tarefa();
	private Tarefa tarefaId;

	@PostConstruct
	public void init() { 
		todosTarefas = tarefaService.buscarTodosTarefas();
	}

	public Tarefa getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(Tarefa tarefaId) {
		this.tarefaId = tarefaId;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTodosTarefas() {
		return this.todosTarefas;
	}

	public void setTodosTarefas(List<Tarefa> todosTarefas) {
		this.todosTarefas = todosTarefas;
	}

	public String editarTarefa() {
		return "tarefaEdit.xhtml?faces-redirect=true&id=" + tarefaId.getId();
	}

	public void removeTarefaById(int idTarefa) {
		System.out.println("Valor " + idTarefa);

		tarefaService.deletarTarefa(idTarefa);
	}
}