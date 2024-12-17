package br.com.mendes.projectmanager.controller;

import java.util.List;

import br.com.mendes.projectmanager.model.Tarefa;
import br.com.mendes.projectmanager.repository.TarefaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class TarefasController {

	private List<Tarefa> todosTarefas;
	private Tarefa tarefa = new Tarefa();
	private Tarefa tarefaId;

	@PostConstruct
	public void init() {
		List<Tarefa> lst = TarefaRepository.getInstance().getTodosTarefas();
		todosTarefas = lst;
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

//    public String loadTarefaById(int id) {
//    	System.out.println("Valor " + id);
//    	tarefaId = TarefaRepository.getInstance().buscarTarefaPorId(Long.valueOf(id));
//    	System.out.println("tarefaId " + this.tarefaId.getTitulo());
//
//        // Adicionar selectedItem ao Flash Scope para usar na próxima página
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("tarefaId", tarefaId);
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//
//        return "/pages/tarefaEdit?faces-redirect=true";
//    }
//
//    public void removeTarefaById(int idTarefa ) {
//    	System.out.println("Valor " + idTarefa);
//    	
//    	TarefaRepository.getInstance().removeTarefas(Long.valueOf(idTarefa));
//
//        // return "/pages/tarefaEdit?faces-redirect=true";
//    }
}