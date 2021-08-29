package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.change.OWLOntologyChangeRecord;
import org.semanticweb.owlapi.change.RemoveOntologyAnnotationData;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLOntologyID;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-26
 */
@JsonTypeName("RemoveOntologyAnnotation")
public record RemoveOntologyAnnotationChange(@Nonnull OWLOntologyID ontologyId,
                                             @Nonnull OWLAnnotation annotation) implements OntologyAnnotationChange {

    public static RemoveOntologyAnnotationChange of(@Nonnull OWLOntologyID ontologyId,
                                                    @Nonnull OWLAnnotation annotation) {
        return new RemoveOntologyAnnotationChange(ontologyId, annotation);
    }

    @Nonnull
    @Override
    public RemoveOntologyAnnotationChange replaceOntologyId(@Nonnull OWLOntologyID ontologyId) {
        if(this.ontologyId.equals(ontologyId)) {
            return this;
        }
        else {
            return RemoveOntologyAnnotationChange.of(ontologyId, annotation);
        }
    }

    @Nonnull
    @Override
    public OWLOntologyChangeRecord toOwlOntologyChangeRecord() {
        return new OWLOntologyChangeRecord(ontologyId, new RemoveOntologyAnnotationData(annotation));
    }

    @Override
    public boolean isRemoveOntologyAnnotation() {
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
    public AddOntologyAnnotationChange getInverseChange() {
        return AddOntologyAnnotationChange.of(ontologyId, annotation);
    }
}
