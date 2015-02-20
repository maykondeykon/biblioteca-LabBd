package biblioteca;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import utils.JPAUtil;
import views.ObraView;
import DAO.ObraDAO;
import modelo.Obra;
import modelo.ObraTipo;


public class main {

	public static void main(String[] args) throws SQLException{
		
//		Editora editora = new Editora();
//		editora.setId(7);
//		
//		ObraTipo tipo = new ObraTipo();
//		tipo.setId(1);
//		
//		Obra obra = new Obra();
//		obra.setNome("Mahatma");
//		obra.setAno("1976");
//		obra.setCodigo("176");
//		obra.setEditora(editora);
//		obra.setTipo(tipo);
		
//		ObraDAO obraDao = new ObraDAO();
		
//		obraDao.insert(obra);
		
//		List<Obra> obras = obraDao.findAll();
//		ObraView Obraview = new ObraView();
//		Obraview.listAll(obras);
//		
//		Obra lasNoches = obraDao.find(30);
//		System.out.println(lasNoches.getNome());
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ObraTipo obraTipo = em.find(ObraTipo.class, 4);
		obraTipo.setNome("GIBI");
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(obraTipo);
		tx.commit();
		
		
		
		
//		ObraTipo obraTipo = em.find(ObraTipo.class, 1);
		
//		System.out.println(obraTipo.getNome());
		
		em.close();
		
	}

}
