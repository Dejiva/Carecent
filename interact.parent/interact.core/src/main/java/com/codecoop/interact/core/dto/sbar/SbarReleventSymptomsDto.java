
package com.codecoop.interact.core.dto.sbar;

import java.util.ArrayList;
import java.util.List;

public class SbarReleventSymptomsDto {

    protected List<SectionDto> section;
    protected Long patientEpisodeId;

    public Long getPatientEpisodeId() {
		return patientEpisodeId;
	}

	public void setPatientEpisodeId(Long patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}

	public void setSection(List<SectionDto> section) {
		this.section = section;
	}

	public List<SectionDto> getSection() {
        if (section == null) {
            section = new ArrayList<SectionDto>();
        }
        return this.section;
    }

}
