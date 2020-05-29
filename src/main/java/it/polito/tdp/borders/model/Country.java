package it.polito.tdp.borders.model;

public class Country implements Comparable<Country>{
	
	private String stateAbb;
	private String stateName;
	private int cCode;
	
	/**
	 * @param stateAbb : 3 caratteri, nome dello stato abbreviato
	 * @param stateName : nome completo dello stato
	 * @param cCode : codice numerico dello stato
	 */
	public Country(int cCode, String stateAbb, String stateName) {
		super();
		this.stateAbb = stateAbb;
		this.stateName = stateName;
		this.cCode = cCode;
	}

	public String getStateAbb() {
		return stateAbb;
	}

	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public int getcCode() {
		return cCode;
	}

	public void setcCode(int cCode) {
		this.cCode = cCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (cCode != other.cCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country: " + cCode + " - " + stateName;
	}

	@Override
	public int compareTo(Country other) {
		return this.getStateName().compareTo(other.getStateName());
	}
	
	
	
	
}
