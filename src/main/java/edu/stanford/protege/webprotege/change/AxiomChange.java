package edu.stanford.protege.webprotege.change;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLEntity;

import javax.annotation.Nonnull;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-26
 */
public interface AxiomChange extends OntologyChange {

    @Nonnull
    @Deprecated
    default OWLAxiom getAxiom() {
        return axiom();
    }

    OWLAxiom axiom();

    @Override
    default OWLAxiom getAxiomOrThrow() throws NoSuchElementException {
        return axiom();
    }

    @Override
    default boolean isAxiomChange() {
        return true;
    }

    @Nonnull
    @Override
    default Set<OWLEntity> getSignature() {
        return axiom().getSignature();
    }
}
