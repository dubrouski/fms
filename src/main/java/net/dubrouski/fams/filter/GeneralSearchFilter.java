package net.dubrouski.fams.filter;

public class GeneralSearchFilter implements SearchFilter {
	
	private String searchTerm;
	
	public GeneralSearchFilter(String term) {
		this.searchTerm = term==null? "" : term;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}	
}
