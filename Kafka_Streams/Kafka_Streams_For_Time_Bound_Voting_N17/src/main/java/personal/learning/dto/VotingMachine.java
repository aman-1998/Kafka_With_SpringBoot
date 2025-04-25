package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VotingMachine {
	
	@JsonProperty("aadharNo")
	private String aadharNo;
	
	@JsonProperty("party")
	private String party;
	
	public VotingMachine() {}
	
	public VotingMachine(String aadharNo, String party) {
		this.aadharNo = aadharNo;
		this.party = party;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	@Override
	public String toString() {
		return "VotingMachine [aadharNo=" + aadharNo + ", party=" + party + "]";
	}
	
}
