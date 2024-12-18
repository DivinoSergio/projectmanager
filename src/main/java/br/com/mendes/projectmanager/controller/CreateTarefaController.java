package br.com.mendes.projectmanager.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.mendes.projectmanager.model.Projeto;
import br.com.mendes.projectmanager.model.Tarefa;
import br.com.mendes.projectmanager.repository.ProjetoRepository;
import br.com.mendes.projectmanager.repository.TarefaRepository;
import br.com.mendes.projectmanager.service.ProjetoService;
import br.com.mendes.projectmanager.service.TarefaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CreateTarefaController implements Serializable {
	
	private static final long serialVersionUID = -7264627396423955289L;
	
	@Inject
	private TarefaService tarefaService;
	@Inject
	private ProjetoService projetoService;
	
	private Tarefa tarefa;
	private Projeto projetoSelecionado;
	private Integer projetoId;
	private String prioridade;
	
	public CreateTarefaController() {
		System.out.println("Entrou CreateTarefaController()");
		tarefa = new Tarefa();
		tarefa.setProjeto(new Projeto());
	}

	private String sProjeto;
    private Map<String, Integer> mapProjetos = new HashMap<>();
    
	@PostConstruct
    public void init() {
		setMapProjetos(new HashMap<>());
		List<Projeto> projetos = ProjetoRepository.getInstance().getTodosProjetos();
		setMapProjetos(projetos.stream().collect(Collectors.toMap(
                        Projeto::getTitulo,
                        Projeto::getId
                )));
	}
	
    public List<Projeto> completeProjeto(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Projeto> projetos = projetoService.buscarPorTitulo(queryLowerCase);
        return projetos;
    }
    
	public String save() {
        // Salvar no banco de dados
        System.out.println("Salvando: " + tarefa.getId() + " - " + tarefa.getTitulo());
        Projeto p = projetoService.buscarProjetoPorId(this.projetoId);
        tarefa.setProjeto(p);
        tarefa.setPrioridade(Integer.valueOf(this.prioridade));
        
		tarefaService.salvarTarefa(tarefa);
		
        // Redirecionar de volta para a página de listagem
        return "/pages/tarefaList.xhtml?faces-redirect=true";
    }
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}

	public Integer getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Integer projetoId) {
		this.projetoId = projetoId;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getsProjeto() {
		return sProjeto;
	}

	public void setsProjeto(String sProjeto) {
		this.sProjeto = sProjeto;
	}

	public Map<String, Integer> getMapProjetos() {
		return mapProjetos;
	}

	public void setMapProjetos(Map<String, Integer> mapProjetos) {
		this.mapProjetos = mapProjetos;
	}

}