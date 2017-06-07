package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	SerieADAO dao;
	Map<Integer,Match> matches;
	DirectedWeightedMultigraph<Team,DefaultWeightedEdge> graph;
	List<Team> teams;
	List<Punti> punti=new ArrayList<Punti>();
	

	public Model() {
		super();
		dao =new SerieADAO();
	}


	public List<Season> getSeason() {
		// TODO Auto-generated method stub
		return dao.listSeasons();
	}


	public void carica(int i) {
		if(graph==null){
			teams=dao.listTeams();
		matches=dao.listMatches(i);
		System.out.println(matches);
		graph=new DirectedWeightedMultigraph<Team,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(graph, teams);
		System.out.println("ciaooooooo  "+graph);
		for (Match m:matches.values()){
			System.out.println("CIAO555");
			if(m.getFtr().equals("H")){
				DefaultWeightedEdge link1 = graph.addEdge(m.getHomeTeam(), m.getAwayTeam());
		//		DefaultWeightedEdge link2 = graph.addEdge(m.getAwayTeam(), m.getHomeTeam());
				if (link1 != null) {
					graph.setEdgeWeight(link1, 1);
			//		graph.setEdgeWeight(link2, -1);
				}	
			}
			if(m.getFtr().equals("A")){
				DefaultWeightedEdge link1 = graph.addEdge(m.getHomeTeam(), m.getAwayTeam());
			//	DefaultWeightedEdge link2 = graph.addEdge(m.getAwayTeam(), m.getHomeTeam());
				if (link1 != null) {
					graph.setEdgeWeight(link1, -1);
		//			graph.setEdgeWeight(link2, 1);
				}	
			}
			if(m.getFtr().equals("D")){
				DefaultWeightedEdge link1 = graph.addEdge(m.getHomeTeam(), m.getAwayTeam());
			//	DefaultWeightedEdge link2 = graph.addEdge(m.getAwayTeam(), m.getHomeTeam());
				if (link1 != null) {
					graph.setEdgeWeight(link1, 0);
		//			graph.setEdgeWeight(link2, 0);
				}	
			}	
		}
		}
		
	}

	public List<Punti> run() {
		
		for(Team t:graph.vertexSet()){
			int h =0;
			for(DefaultWeightedEdge i:graph.outgoingEdgesOf(t)){
			if(graph.getEdgeWeight(i)==1)
				h+=3;
			if(graph.getEdgeWeight(i)==0)
				h+=1;
			}
			
			punti.add(new Punti(t,h));
		}
		java.util.Collections.sort(punti);
		
		return punti;
	}

	
}
