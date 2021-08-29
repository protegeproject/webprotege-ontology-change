package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import edu.stanford.protege.webprotege.common.ValueObject;

import java.util.Objects;
import java.util.UUID;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-29
 */
public record OntologyDocumentId(@JsonValue String id) implements ValueObject {

    @JsonCreator
    OntologyDocumentId valueOf(String id) {
        Objects.requireNonNull(id);
        return new OntologyDocumentId(id);
    }

    OntologyDocumentId generate() {
        return valueOf(UUID.randomUUID().toString());
    }

    @Override
    public String value() {
        return id;
    }
}
