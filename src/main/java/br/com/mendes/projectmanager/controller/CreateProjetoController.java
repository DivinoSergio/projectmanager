package br.com.mendes.projectmanager.controller;

import java.io.Serializable;

import br.com.mendes.projectmanager.model.Projeto;
import br.com.mendes.projectmanager.service.ProjetoService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CreateProjetoController implements Serializable {

	private static final long serialVersionUID = -7264627396423955289L;

	@Inject
	private ProjetoService projetoService;
	private Projeto projeto;

	public CreateProjetoController() {
		System.out.println("Entrou CreateProjetoController()");
		projeto = new Projeto();
	}

	public String save() {
		// Salvar no banco de dados
		System.out.println("Salvando: " + projeto.getId() + " - " + projeto.getTitulo());

		projetoService.salvarProjeto(projeto);

		// Redirecionar de volta para a página de listagem
		return "/pages/projetoList.xhtml?faces-redirect=true";
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
}