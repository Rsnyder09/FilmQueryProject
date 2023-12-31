package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

 

public class Film {
	
	private List<Actor> actors;
	private int filmId;
	private String title;
	private String desc;
	private short releaseYear; 
	private int langId; 
	private int rentDur; 
	private double rate; 
	private int length; 
	private double repCost; 
	private String rating; 
	private String features;
	private String langName;
	
	public Film() {
		
	}
	

	
	public Film( int filmId, String title, String desc, short releaseYear, int langId, int rentDur,
			double rate, int length, double repCost, String rating, String features) {
		
		
		this.filmId = filmId;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
	}



	public Film(String title,short releaseYear, String rating, String desc,String langName, List<Actor> actors) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.desc = desc;
		this.langName = langName;
		this.actors = actors;
	}


	public List<Actor> getActors() {
		return actors;
	}


	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}


	public int getFilmId() {
		return filmId;
	}


	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public short getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}


	public int getLangId() {
		return langId;
	}


	public void setLangId(int langId) {
		this.langId = langId;
	}


	public int getRentDur() {
		return rentDur;
	}


	public void setRentDur(int rentDur) {
		this.rentDur = rentDur;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public double getRepCost() {
		return repCost;
	}


	public void setRepCost(double repCost) {
		this.repCost = repCost;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getFeatures() {
		return features;
	}


	public void setFeatures(String features) {
		this.features = features;
	}

	

	public String getLangName() {
		return langName;
	}



	public void setLangName(String langName) {
		this.langName = langName;
	}



	@Override
	public String toString() {
		return "Title: " + title + ", Year: " + releaseYear + ", Rating: " + rating + ", Description: " + desc + ", Language: " + langName + ", Actors" + actors;
	}


	@Override
	public int hashCode() {
		return Objects.hash(actors, desc, features, filmId, langId, length, rate, rating, releaseYear, rentDur, repCost,
				title);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(desc, other.desc)
				&& Objects.equals(features, other.features) && filmId == other.filmId && langId == other.langId
				&& length == other.length && Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear && rentDur == other.rentDur
				&& Double.doubleToLongBits(repCost) == Double.doubleToLongBits(other.repCost)
				&& Objects.equals(title, other.title);
	}
	
	
	
}
