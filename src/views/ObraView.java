package views;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Obra;

public class ObraView extends JFrame{

	JTable tabela;
	DefaultTableModel modelo;
	JScrollPane tabPainel;
	
		
	public void listAll(List<Obra> obras)
	{
		
		setTitle("Obras");
		setSize(800,200);
		
		this.modelo = new DefaultTableModel(null, new String[] {"ID","Código","Nome","Ano","Editora","Tipo"});
		this.tabela = new JTable(this.modelo);
		this.tabela.setAutoCreateRowSorter(true);
		
		for (Obra obra : obras) {
			String[] dados = new String[6];
			dados[0] = obra.getId().toString();
			dados[1] = obra.getCodigo();
			dados[2] = obra.getNome();
			dados[3] = obra.getAno();
			dados[4] = obra.getEditora().getNome();
			dados[5] = obra.getTipo().getNome();
			this.modelo.addRow(dados);
		}
		
		this.tabPainel =new JScrollPane(this.tabela);
		add(this.tabPainel, BorderLayout.CENTER);
		setVisible(true);
	}


}
