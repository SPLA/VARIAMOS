package com.cfm.productline;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AssetModel implements Serializable{

	private static final long serialVersionUID = 5153432059224220508L;
	
	private Map<String, Asset> assets;

	public AssetModel(){
		assets = new HashMap<>();
	}
	
	public void addAsset(Asset asset){
		if( asset.getIdentifier() == null )
			asset.setIdentifier(nextAssetId());
		
		if( asset.getName() == null )
			asset.setName(asset.getIdentifier());
		
		assets.put(asset.getIdentifier(), asset);
	}
	
	public Asset findAsset(String identifier){
		return assets.get(identifier);
	}
	
	private String nextAssetId(){
		int id = assets.size();
		while( assets.containsKey("Asset" + id) ){
			id ++;
		}
		return "Asset" + id;
	}

	public Map<String, Asset> getAssets() {
		return assets;
	}

	public void setAssets(Map<String, Asset> assets) {
		this.assets = assets;
	}
}
