package net.dubrouski.fams.filter;

public class GeneralSearchFilter implements SearchFilter {
	
	private String searchTerm;
	
	public GeneralSearchFilter(String term) {
		if (term == null || term.isEmpty()){
			throw new IllegalArgumentException("term cannot be null or empty");
		}
		this.searchTerm = term;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}	
}
