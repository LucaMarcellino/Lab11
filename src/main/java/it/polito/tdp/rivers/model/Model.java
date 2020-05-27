package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private List<River> fiumi;
	private River richiesto;
	
	public Model() {
		this.dao=new RiversDAO();
		this.fiumi=new ArrayList<River>(dao.getAllRivers());
	}

	public List<River> getFiumi() {
		return fiumi;
	}
	
	public River getRichiesto(River r) {
		richiesto=dao.getAllDate(r);
		return richiesto;
	}
	
	

}
