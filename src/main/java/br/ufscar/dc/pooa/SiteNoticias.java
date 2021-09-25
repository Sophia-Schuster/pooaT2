package br.ufscar.dc.pooa;

public class SiteNoticias {
	private String link;
	private String classe;
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	public SiteNoticias(String link, String classe) {
		setLink(link);
		setClasse(classe);
	}
	
	public SiteNoticias(String link) {
		setLink(link);
	}
	
	
}
