package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public River getAllDate (River r){
		
		List<Flow> flows =new ArrayList<Flow>();
		
		 String sql = "SELECT DAY,flow,r.id from flow AS f,river AS r WHERE f.river=r.id AND r.name=? ORDER BY DAY asc";
		River rAdd =null;
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, r.getName());
			ResultSet res = st.executeQuery();
			int id=0;
			while (res.next()) {
				id=res.getInt("r.id");
				flows.add(new Flow(res.getDate("DAY").toLocalDate(), res.getDouble("flow"), new River(res.getInt("id"),r.getName())));

			}
			rAdd=new River(id,r.getName());
			rAdd.setFlows(flows);
			rAdd.setFlowAvg(this.getMedia(flows));

			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return rAdd;
		
	
	}
	
	public int getSize(River r) {
		int size=0;

		final String sql = "SELECT COUNT(*) AS tot from flow AS f,river AS r WHERE f.river=r.id AND r.name=?";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, r.getName());
			ResultSet res = st.executeQuery();
			res.next();
			size=res.getInt("tot");
			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return size;
		
	
	}

	private double getMedia(List<Flow> flow) {
		double media=0.0;
		double somma=0;
		for(Flow f: flow) {
			somma+=f.getFlow();
		}
		media=somma/flow.size();
		
		return media;
	}
	
	
	
}
