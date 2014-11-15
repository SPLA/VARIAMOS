package com.variamos.rq.concepts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalModel implements Serializable{
	private static final long serialVersionUID = 5153432059224220508L;
	
	private Map<String, Goal> goals;
	

	public GoalModel(){
		goals = new HashMap<>();
	}
	
	public void addGoal(Goal asset){
		if( asset.getIdentifier() == null )
			asset.setIdentifier(getNextGoalId());
		
		if( asset.getName() == null )
			asset.setName(asset.getIdentifier());
		
		goals.put(asset.getIdentifier(), asset);
	}
	
	public Goal findAsset(String identifier){
		return goals.get(identifier);
	}
	
	private String getNextGoalId(){
		int id = goals.size();
		while( goals.containsKey("G" + id) ){
			id ++;
		}
		return "G" + id;
	}

	public Map<String, Goal> getAssets() {
		return goals;
	}

	public void setAssets(Map<String, Goal> assets) {
		this.goals = assets;
	}
}
