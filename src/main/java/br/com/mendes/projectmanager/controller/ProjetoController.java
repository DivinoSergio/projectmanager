package br.com.mendes.projectmanager.controller;

import java.util.List;

import br.com.mendes.projectmanager.model.Projeto;
import br.com.mendes.projectmanager.service.ProjetoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ProjetoController {
	
	 @Inject 
	 private ProjetoService projetoService;
	
	private List<Projeto> todosProjetos;
	private Projeto projeto = new Projeto();
	private Projeto projetoId;

	public void setTodosProjetos(List<Projeto> todosProjetos) {
		this.todosProjetos = todosProjetos;
	}
	
	public Projeto getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Projeto projetoId) {
		this.projetoId = projetoId;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public List<Projeto> getTodosProjetos() {
		return projetoService.buscarTodosProjetos(); 
	}

    public String editarProjeto() {
        return "projetoEdit.xhtml?faces-redirect=true&id=" + projetoId.getId();
    }

    public void removeProjetoById(int id) {
    	System.out.println("Valor " + id);
    	
    	try {
    		projetoService.deletarProjeto(id);
	    	
	    } catch (IllegalStateException e) {
	        // Captura a exceção e adiciona uma mensagem ao contexto
	        addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir o projeto", 
	        		"Não é possível excluir o Projeto, possui Tarefas associadas.");
	        
	    } catch (Exception e) {
	        // Tratamento genérico para outras exceções
	        addMessage(FacesMessage.SEVERITY_FATAL, "Erro ao excluir o projeto", 
	        		"Não é possível excluir o Projeto, possui Tarefas associadas.");
	    }
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
     
}