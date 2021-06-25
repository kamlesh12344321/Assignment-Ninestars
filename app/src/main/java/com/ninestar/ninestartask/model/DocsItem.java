package com.ninestar.ninestartask.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "DocsItem")
public class DocsItem implements Serializable {

	@PrimaryKey(autoGenerate = true)
	private int index;

	@SerializedName("id")
	private String id;

	@SerializedName("score")
	private double score;

	@SerializedName("journal")
	private String journal;

	@SerializedName("article_type")
	private String articleType;

	@SerializedName("title_display")
	private String titleDisplay;

	@SerializedName("author_display")
	private List<String> authorDisplay;

	@SerializedName("publication_date")
	private String publicationDate;

	@SerializedName("eissn")
	private String eissn;

	@SerializedName("abstract")
	private List<String> jsonMemberAbstract;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getTitleDisplay() {
		return titleDisplay;
	}

	public void setTitleDisplay(String titleDisplay) {
		this.titleDisplay = titleDisplay;
	}

	public List<String> getAuthorDisplay() {
		return authorDisplay;
	}

	public void setAuthorDisplay(List<String> authorDisplay) {
		this.authorDisplay = authorDisplay;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getEissn() {
		return eissn;
	}

	public void setEissn(String eissn) {
		this.eissn = eissn;
	}

	public List<String> getJsonMemberAbstract() {
		return jsonMemberAbstract;
	}

	public void setJsonMemberAbstract(List<String> jsonMemberAbstract) {
		this.jsonMemberAbstract = jsonMemberAbstract;
	}
}