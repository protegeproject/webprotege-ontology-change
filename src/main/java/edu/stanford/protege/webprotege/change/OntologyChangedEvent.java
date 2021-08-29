package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.Event;
import edu.stanford.protege.webprotege.common.ProjectId;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-29
 *
 * An {@link OntologyChangedEvent} that records a low level axiom change to a project
 */
@JsonTypeName("OntologyChangeEvent")
public record OntologyChangedEvent(ProjectId projectId,
                                   OntologyChange ontologyChange) implements Event {

    public static final String CHANNEL = "webprotege.projects.events.OntologyChange";

    @Override
    public String getChannel() {
        return CHANNEL;
    }
}
