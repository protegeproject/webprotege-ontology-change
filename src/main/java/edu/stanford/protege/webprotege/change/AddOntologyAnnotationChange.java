package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.auto.value.AutoValue;
import org.semanticweb.owlapi.change.AddOntologyAnnotationData;
import org.semanticweb.owlapi.change.OWLOntologyChangeRecord;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLOntologyID;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-26
 */
@JsonTypeName("AddOntologyAnnotation")
public record AddOntologyAnnotationChange(@Nonnull OWLOntologyID ontologyId,
                                          @Nonnull OWLAnnotation annotation) implements OntologyAnnotationChange {

    @Nonnull
    public static AddOntologyAnnotationChange of(@Nonnull OWLOntologyID ontologyId,
                                                 @Nonnull OWLAnnotation annotation) {
        return new AddOntologyAnnotationChange(ontologyId, annotation);
    }

    @Nonnull
    @Override
    public AddOntologyAnnotationChange replaceOntologyId(@Nonnull OWLOntologyID ontologyId) {
        if(this.ontologyId.equals(ontologyId)) {
            return this;
        }
        else {
            return AddOntologyAnnotationChange.of(ontologyId, this.annotation);
        }
    }

    @Nonnull
    @Override
    public OWLOntologyChangeRecord toOwlOntologyChangeRecord() {
        return new OWLOntologyChangeRecord(this.ontologyId, new AddOntologyAnnotationData(this.annotation));
    }

    @Override
    public boolean isAddOntologyAnnotation() {
        return true;
    }

    @Override
    public void accept(@Nonnull OntologyChangeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <R> R accept(@Nonnull OntologyChangeVisitorEx<R> visitorEx) {
        return visitorEx.visit(this);
    }

    @Nonnull
    @Override
    public RemoveOntologyAnnotationChange getInverseChange() {
        return RemoveOntologyAnnotationChange.of(ontologyId, annotation);
    }
}
