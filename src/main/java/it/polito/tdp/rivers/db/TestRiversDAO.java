package it.polito.tdp.rivers.db;

import it.polito.tdp.rivers.model.River;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		River r= new River(0,"Jokulsa Eystri River");
		System.out.println(dao.getAllDate(r));
	}

}
