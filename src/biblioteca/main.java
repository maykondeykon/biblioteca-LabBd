package biblioteca;

import java.sql.SQLException;
import java.util.List;

import views.ObraView;
import DAO.ObraDAO;
import modelo.Obra;


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
		
		ObraDAO obraDao = new ObraDAO();
		
//		obraDao.insert(obra);
		
		List<Obra> obras = obraDao.findAll();
		ObraView Obraview = new ObraView();
		Obraview.listAll(obras);
		
		Obra lasNoches = obraDao.find(30);
		System.out.println(lasNoches.getNome());
		
	}

}
