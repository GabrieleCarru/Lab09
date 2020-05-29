package it.polito.tdp.borders.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	BordersDAO dao;
	CountryIdMap idMap;
	List<Country> countries;
	private SimpleGraph<Country, DefaultEdge> graph;
	
	
	public Model() {
		
		dao = new BordersDAO();
		
	}
	
	public void creaGrafo(int anno) {
		
		idMap = new CountryIdMap();
		countries = dao.loadAllCountries(idMap);
		
		List<Border> confini = dao.getCountryPairs(anno, idMap);
		
		if(confini.isEmpty()) {
			throw new RuntimeException("No country pairs for " + anno + ".");
		}
		
		graph = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		
		for (Border b : confini) {
			graph.addVertex(b.getC1());
			graph.addVertex(b.getC2());
			graph.addEdge(b.getC1(), b.getC2());
		}
		
		Collections.sort(countries);
		
	}
	
	public List<Country> getCountries() {
		return countries;
	}
	
	public int countVertex() {
		return graph.vertexSet().size();
	}
	
	public int countEdge() {
		return graph.edgeSet().size();
	}
	
	public Map<Country, Integer> getCountryCounts() {
		if(graph == null) {
			throw new RuntimeException("Graph doesn't exists.");
		}
		
		Map<Country, Integer> result = new HashMap<>();
		for(Country c : graph.vertexSet()) {
			result.put(c, graph.degreeOf(c));
		}
		
		return result;
	}
	
	public int getNumberOfConnectedComponents() {
		if(graph == null) {
			throw new RuntimeException("Graph doesn't exists.");
		}
		
		ConnectivityInspector<Country, DefaultEdge> ci;
		ci = new ConnectivityInspector<Country, DefaultEdge>(graph);
		return ci.connectedSets().size();
	}

}
