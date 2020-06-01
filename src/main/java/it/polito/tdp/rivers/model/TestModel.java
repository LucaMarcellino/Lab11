package it.polito.tdp.rivers.model;

public class TestModel {

	public static void main(String[] args) {
		Model m =new Model();
		Simulator sim = new Simulator();
		River r = new River(1,"Jokulsa Eystri River");
		
		sim.init(m.getRichiesto(r), 5);
		System.out.println(sim.run());

	}

}
