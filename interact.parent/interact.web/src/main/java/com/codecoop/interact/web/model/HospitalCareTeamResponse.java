package com.codecoop.interact.web.model;

import java.io.Serializable;
import java.util.List;

import com.codecoop.interact.core.dto.HsptlPhyCareTeam;

public class HospitalCareTeamResponse implements Serializable {
	
	private List<HsptlPhyCareTeam> hsptlPhyCareTeam;

	public List<HsptlPhyCareTeam> getHsptlPhyCareTeam() {
		return hsptlPhyCareTeam;
	}

	public void setHsptlPhyCareTeam(List<HsptlPhyCareTeam> hsptlPhyCareTeam) {
		this.hsptlPhyCareTeam = hsptlPhyCareTeam;
	}
	
}
