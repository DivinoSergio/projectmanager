package br.com.mendes.projectmanager.controller;

import java.io.Serializable;

import br.com.mendes.projectmanager.model.Projeto;
import br.com.mendes.projectmanager.repository.ProjetoRepository;
import br.com.mendes.projectmanager.service.ProjetoService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class EditProjetoController implements Serializable {

	private static final long serialVersionUID = -4354208142461711818L;

	@Inject
	private ProjetoService projetoService;
	private Projeto projeto;	
	private Projeto projetoId;

	public void init() {
		System.out.println("Entrou inicializando o EditProjetoController.");
		 // Obtém o parâmetro da URL e carrega o objeto
        String idParam = FacesContext.getCurrentInstance().getExternalContext()
        		.getRequestParameterMap().get("id");
        
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            projetoId = projetoService.buscarProjetoPorId(id);
        }
	}
	
	public String edit() {
        // Salvar no banco de dados
        System.out.println("Salvando: " + projetoId.getId() + " - " + projetoId.getTitulo());

        projetoService.atualizarProjeto(projetoId);
		
        // Redirecionar de volta para a página de listagem
        return "/pages/projetoList.xhtml?faces-redirect=true";
    }
	
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public Projeto getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Projeto projetoId) {
		this.projetoId = projetoId;
	}
}