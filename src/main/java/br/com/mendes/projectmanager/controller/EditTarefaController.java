package br.com.mendes.projectmanager.controller;

import java.io.Serializable;

import br.com.mendes.projectmanager.model.Tarefa;
import br.com.mendes.projectmanager.repository.TarefaRepository;
import br.com.mendes.projectmanager.service.TarefaService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class EditTarefaController implements Serializable {

	private static final long serialVersionUID = -3416840278403571207L;

	@Inject
	private TarefaService tarefaService;
	
	private Tarefa tarefa;	
	private Tarefa tarefaId;
	private String prioridade;
	
	public void init() {
		System.out.println("Entrou inicializando o EditTarefaController.");
		 // Obtém o parâmetro da URL e carrega o objeto
        String idParam = FacesContext.getCurrentInstance().getExternalContext()
        		.getRequestParameterMap().get("id");
        
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            tarefaId = tarefaService.buscarTarefaPorId(id);
            this.prioridade = tarefaId.getEstimativa().toString();
        }
	}
	
	public String edit() {
        // Salvar no banco de dados
		System.out.println("Salvando: " + tarefaId.getId() + " - " + tarefaId.getTitulo());
		tarefaId.setEstimativa(Integer.valueOf(this.prioridade));
		tarefaService.atualizarTarefa(tarefaId);
		
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