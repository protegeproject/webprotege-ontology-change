package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.common.*;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-29
 *
 * An {@link OntologyChangedEvent} that records a low level axiom change to a project
 */
@JsonTypeName("webprotege.events.projects.OntologyChanged")
public record OntologyChangedEvent(EventId eventId,
                                   ProjectId projectId,
                                   UserId userId,
                                   OntologyChange ontologyChange) implements ProjectEvent {

    public static final String CHANNEL = "webprotege.events.projects.OntologyChanged";

    @Override
    public String getChannel() {
        return CHANNEL;
    }
}
