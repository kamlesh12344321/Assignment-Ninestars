package com.ninestar.ninestartask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NDAResponse  implements Serializable {

	@SerializedName("response")
	private NDAResponse response;

	@SerializedName("docs")
	private List<DocsItem> docs;

	@SerializedName("numFound")
	private int numFound;

	@SerializedName("start")
	private int start;

	@SerializedName("maxScore")
	private double maxScore;

	public NDAResponse getResponse(){
		return response;
	}

	public List<DocsItem> getDocs(){
		return docs;
	}

	public int getNumFound(){
		return numFound;
	}

	public int getStart(){
		return start;
	}

	public double getMaxScore(){
		return maxScore;
	}
}