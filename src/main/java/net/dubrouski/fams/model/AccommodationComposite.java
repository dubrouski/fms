package net.dubrouski.fams.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import net.dubrouski.fams.exception.FmsException;
/**
 * 
 * @author ondrej.prazak
 *
 */
@Entity
public abstract class AccommodationComposite extends AccommodationUnit {
	
	private static final long serialVersionUID = 1L;	
	
	@OneToMany
	@JoinTable(name = "UNIT_UNIT", 
			joinColumns = {
				@JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
			},
			inverseJoinColumns = { 
				@JoinColumn(name = "CHILD_ID", referencedColumnName = "ID", unique = true)
			}
	)
	private List<AccommodationUnit> children = new ArrayList<AccommodationUnit>();
	
	
	protected abstract void unitTypeCheck(AccommodationUnit unit);
	
	protected abstract void addressCheck(AccommodationUnit unit);
	
	
	public void add(AccommodationUnit unit) {
		unitTypeCheck(unit);
		addressCheck(unit);
		if(hasChild(unit)){
			throw new FmsException("Unit already among children");
		}
		children.add(unit);
	}
	
	public void remove(AccommodationUnit unit) {
		unitTypeCheck(unit);
		addressCheck(unit);
		if(!hasChild(unit)){
			throw new FmsException("Unit not among the children");
		}
		children.remove(unit);
	}
	
	public boolean hasChild(AccommodationUnit unit) {
		return children.contains(unit);
	}
	
	public int childrenCount(){
		return children.size();
	}

	public List<AccommodationUnit> getChildren() {
		return children;
	}
	
	public void setChildren(List<AccommodationUnit> children) {
		this.children = children;
	}
	
}
