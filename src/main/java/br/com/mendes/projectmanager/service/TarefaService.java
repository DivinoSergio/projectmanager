package br.com.mendes.projectmanager.service;

import java.util.List;

import br.com.mendes.projectmanager.model.Tarefa;
import br.com.mendes.projectmanager.repository.TarefaRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarefaService {
	private final TarefaRepository tarefaRepository;

	public TarefaService() {
        this.tarefaRepository = TarefaRepository.getInstance();
    }

	public Tarefa buscarTarefaPorId(Integer id) {
		return tarefaRepository.findById(id);
	}

	public List<Tarefa> buscarTodosTarefas() {
		return tarefaRepository.findAll("SELECT p FROM Tarefa p");
	}

	public void salvarTarefa(Tarefa tarefa) {
		tarefaRepository.save(tarefa);
	}

	public void atualizarTarefa(Tarefa tarefa) {
		tarefaRepository.update(tarefa);
	}

	public void deletarTarefa(Integer id) {
		tarefaRepository.deleteById(id);
	}
}
