package br.com.mendes.projectmanager.repository;

import java.util.List;

import br.com.mendes.projectmanager.model.Projeto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ProjetoRepository extends GenericRepository<Projeto, Integer> {

	private static ProjetoRepository uniqueInstance = new ProjetoRepository();

	private ProjetoRepository() {
		super(Projeto.class);
	}

	public static ProjetoRepository getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ProjetoRepository();
        }
		return uniqueInstance;
	}

	public List<Projeto> getTodosProjetos() {
		EntityManager em = getEntityManager();
		return em.createQuery("From Projeto").getResultList();
	}
	
	public List<Projeto> buscarProjetosPorTitulo(String query) {
		EntityManager em = getEntityManager();
        return em.createQuery("SELECT p FROM Projeto p WHERE LOWER(p.titulo) LIKE :query", Projeto.class)
                 .setParameter("query", "%" + query.toLowerCase() + "%")
                 .getResultList();
    }
	
	public Projeto buscarProjetoPorId(Integer id) {
		EntityManager em = getEntityManager();

        Projeto projeto = null;

        try {
            // Criar consulta HQL
            String hql = "SELECT p FROM Projeto p WHERE p.id = :id";
            TypedQuery<Projeto> query = em.createQuery(hql, Projeto.class);
            query.setParameter("id", id);

            // Executar a consulta e obter resultado
            projeto = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechar EntityManager
            em.close();
        }

        return projeto;
    }

	public void saveProjetos(Projeto projeto) {
		System.out.println("[Entrou] saveProjetos" );
		EntityManager em = getEntityManager();
        
        try {
			em.getTransaction().begin();
			
			em.persist(projeto);
			
			em.getTransaction().commit();
			em.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
		System.out.println("Projeto cadastrado");
	}

	public void editProjetos(Projeto projeto) {
		System.out.println("[Entrou] editProjetos" );
		EntityManager em = getEntityManager();
        
        try {
			em.getTransaction().begin();
			
			em.merge(projeto);
			
			em.getTransaction().commit();
			em.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechar EntityManager
            em.close();
        }
		System.out.println("Projeto alterado");
	}
	
	public void removeProjetos(int idProjeto)  {
		System.out.println("[Entrou] removeProjetos, ID: " + idProjeto);
		EntityManager em = getEntityManager();
        
        try {
			em.getTransaction().begin();
			// Carregar o objeto gerenciado
		    Projeto projetoGerenciado = em.find(Projeto.class, idProjeto);
		    
		    if (projetoGerenciado != null) {
		        em.remove(projetoGerenciado);
		    }
		    
			em.getTransaction().commit();

        } catch (IllegalStateException e) {
        	em.getTransaction().rollback();
        	throw e;
        	
        } catch (Exception e) {
        	em.getTransaction().rollback();
        	throw e;
        
        } finally {
            // Fechar EntityManager
            em.close();
        }
		System.out.println("Projeto excluido");
	}	
}