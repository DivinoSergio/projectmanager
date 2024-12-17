package br.com.mendes.projectmanager.controller;

import java.io.Serializable;

import br.com.mendes.projectmanager.model.Tarefa;
import br.com.mendes.projectmanager.repository.ProjetoRepository;
import br.com.mendes.projectmanager.repository.TarefaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@RequestScoped
public class EditTarefaController implements Serializable {

	private static final long serialVersionUID = -3416840278403571207L;
	private Tarefa tarefa;	
	private Tarefa tarefaId;
	private String prioridade;
	
	@PostConstruct
	public void init() {
		System.out.println("Entrou inicializando o EditTarefaController.");
		 // Obtém o parâmetro da URL e carrega o objeto
        String idParam = FacesContext.getCurrentInstance().getExternalContext()
        		.getRequestParameterMap().get("id");
        
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            tarefaId = TarefaRepository.getInstance().findById(id);
        }
	}
	
	public String edit() {
        // Salvar no banco de dados
        System.out.println("Salvando: " + tarefaId.getId() + " - " + tarefaId.getTitulo());

		TarefaRepository.getInstance().editTarefas(tarefaId);
		
        // Redirecionar de volta para a página de listagem
        return "/pages/tarefaList.xhtml?faces-redirect=true";
    }
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	public Tarefa getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(Tarefa tarefaId) {
		this.tarefaId = tarefaId;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
}