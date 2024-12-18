package br.com.mendes.projectmanager.service;

import java.util.List;

import br.com.mendes.projectmanager.model.Projeto;
import br.com.mendes.projectmanager.repository.ProjetoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped 
public class ProjetoService {

	private final ProjetoRepository projetoRepository;

	public ProjetoService() {
        this.projetoRepository = ProjetoRepository.getInstance();
    }

	public Projeto buscarProjetoPorId(Integer id) {
		return projetoRepository.findById(id);
	}

	public List<Projeto> buscarTodosProjetos() {
		return projetoRepository.findAll("SELECT p FROM Projeto p");
	}

	public void salvarProjeto(Projeto projeto) {
		projetoRepository.save(projeto);
	}

	public void atualizarProjeto(Projeto projeto) {
		projetoRepository.update(projeto);
	}

	public void deletarProjeto(Integer id) {
		projetoRepository.deleteById(id);
	}

	public List<Projeto> buscarPorTitulo(String nome) {
        return projetoRepository.buscarProjetosPorTitulo(nome);
    }
}