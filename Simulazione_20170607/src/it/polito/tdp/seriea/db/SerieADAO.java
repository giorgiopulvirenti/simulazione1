package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {
	Map<Integer,Season> seasons=new HashMap<Integer,Season>();
	Map<Integer,Match> matches=new HashMap<Integer,Match>();
	Map<String,Team> teams=new HashMap<String,Team>();
	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons" ;
		
		List<Season> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Season s = new Season(res.getInt("season"), res.getString("description"));
				seasons.put(res.getInt("season"),s );
				result.add( s) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams" ;
		
		List<Team> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Team t= new Team(res.getString("team"));
				teams.put(res.getString("team"), t);
				result.add(t) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public Map<Integer,Match> listMatches(int season) {
		String sql = "SELECT * FROM matches "
				+ "WHERE Season=? " ;
		System.out.println(season+" #####");
		List<Match> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, season);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Match m=new Match(res.getInt("match_id"),
						seasons.get(res.getInt("Season")),
						res.getString("Div"),
						res.getDate("Date").toLocalDate(),
						teams.get(res.getString("HomeTeam")),
						teams.get(res.getString("AwayTeam")),
						res.getInt("FTHG"),
						res.getInt("FTAG"),
						res.getString("FTR") );
				matches.put(res.getInt("match_id"), m);
				result.add(m);
			}
			
			conn.close();
			System.out.println(matches.values()+" hollaaaa @@@@");
			return matches ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(" hollaaaa @@@@");
			return null ;
			
		}
	}
}
