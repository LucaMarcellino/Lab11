package it.polito.tdp.rivers.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {
	
	//Parametri simulazione
	private River selezionato;
	private double c;
	private double k;
	private double q;
	private double fMedio;
	private double fMin;
	private Duration cambio=Duration.of(24,ChronoUnit.HOURS);
	private LocalDate gionoInizio;
	private int mese;
	
	//Output da calcolare
	private double cMedio;
	private double cSomma;
	private int giorni;
	
	//Stato del sistema
	private double fIn;
	private double fOut;
	
	//Coda degli eventi
	private PriorityQueue<Event> coda;
	
	
	public void init(River r, double k) {
	
		
		this.fIn=0;
		this.fOut=0;
		this.selezionato=r;
		this.fMedio=r.getFlowAvg()*(3600*24);
		this.k=k;
		this.fMin=fMedio*0.8;
		this.q=k*fMedio*30;
		this.c=q/2;
		this.gionoInizio=r.getFlows().get(0).getDay();
		this.mese=0;
		this.cSomma=c;
		this.coda= new PriorityQueue<Event>();
		
		for (int i=0 ;i<r.getFlows().size();i++) {
			Flow f = new Flow(gionoInizio, r.getFlows().get(i).getFlow(), r);
			Event e= new Event(gionoInizio, EventType.GIORNO,f);
			coda.add(e);
			gionoInizio=gionoInizio.plusDays(1);
			
		}

	}
	
	public String run() {
		
		while(!coda.isEmpty()) {
			Event e= coda.poll();
			System.out.println(e);
			this.process(e);
		}
		return "Giorni senza acqua: "+giorni+"\nCapacita media nel periodo: "+(cSomma/(selezionato.getFlows().size()+1))+"         "+fMedio  ;
	}

	private void process(Event e) {
		fIn=e.getFlow().getFlow()*(3600*24);
		this.fMin=fMedio*0.8;
		switch(e.getType()) {
		case GIORNO:
			c+=fIn;
			if(Math.random()<0.05) {
				fMin=fMin*10;
			}
			if(c>q) {
				fOut=fMin+(c-q);
				c=c-fOut;
				cSomma+=c;
				break;
			}
			if(c>=fMin) {
				c=c-fMin;
				cSomma+=c;
				break;
			}else {
				fOut=c;
				c=c-fOut;
				giorni++;
			}
			
			
			
			break;

		
		}
		
	}
	

	

}
