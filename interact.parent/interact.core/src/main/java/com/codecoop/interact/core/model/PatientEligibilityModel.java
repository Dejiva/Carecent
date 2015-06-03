package com.codecoop.interact.core.model;

import java.io.Serializable;

public class PatientEligibilityModel implements Serializable {
	private Boolean stopAndWatchEligible;
	private Boolean sbarRised;

	public Boolean getStopAndWatchEligible() {
		if (stopAndWatchEligible == null) {
			return false;
		}
		return stopAndWatchEligible;
	}

	public void setStopAndWatchEligible(Boolean stopAndWatchEligible) {
		this.stopAndWatchEligible = stopAndWatchEligible;
	}

	public Boolean getSbarRised() {
		if (sbarRised == null) {
			return false;
		}
		return sbarRised;
	}

	public void setSbarRised(Boolean sbarRised) {
		this.sbarRised = sbarRised;
	}

}
