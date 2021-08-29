package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.change.AddAxiomData;
import org.semanticweb.owlapi.change.OWLOntologyChangeRecord;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntologyID;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-26
 */
@JsonTypeName("AddAxiom")
public record AddAxiomChange(@Nonnull OWLOntologyID ontologyId,
                             @Nonnull OWLAxiom axiom) implements AxiomChange {

    public static AddAxiomChange of(@Nonnull OWLOntologyID ontologyId,
                                    @Nonnull OWLAxiom axiom) {
        return new AddAxiomChange(ontologyId, axiom);
    }

    @Nonnull
    @Override
    public AddAxiomChange replaceOntologyId(@Nonnull OWLOntologyID ontologyId) {
        if(this.ontologyId.equals(ontologyId)) {
            return this;
        }
        else {
            return AddAxiomChange.of(ontologyId, axiom);
        }
    }

    @Nonnull
    @Override
    public OWLOntologyChangeRecord toOwlOntologyChangeRecord() {
        return new OWLOntologyChangeRecord(ontologyId, new AddAxiomData(axiom));
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
    public RemoveAxiomChange getInverseChange() {
        return RemoveAxiomChange.of(ontologyId, axiom);
    }
}
