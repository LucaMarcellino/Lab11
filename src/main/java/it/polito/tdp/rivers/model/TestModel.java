package it.polito.tdp.rivers.model;

public class TestModel {

	public static void main(String[] args) {
		Model m =new Model();
		River r = new River(1,"Jokulsa Eystri River");
		System.out.println(m.getRichiesto(r));

	}

}
