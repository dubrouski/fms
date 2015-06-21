package net.dubrouski.fams.filter;

public class PersonNamesFilter implements SearchFilter {
	
	private String searchTerm;
	
	public PersonNamesFilter(String term) {
		this.searchTerm = term==null? "" : term;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}	
}
