package modelo;

/**
 * @author maykon
 *
 */
public class Obra 
{

	private Integer id;
	private String codigo;
	private String nome;
	private String ano;
	private Editora editora;
	private ObraTipo tipo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public Editora getEditora() {
		return editora;
	}
	public ObraTipo getTipo() {
		return tipo;
	}
	public void setTipo(ObraTipo tipo) {
		this.tipo = tipo;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	
}
