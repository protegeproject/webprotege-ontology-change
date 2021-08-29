package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import org.semanticweb.owlapi.change.OWLOntologyChangeRecord;
import org.semanticweb.owlapi.model.*;

import javax.annotation.Nonnull;
import java.util.NoSuchElementException;
import java.util.Set;


/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-26
 */
@JsonSubTypes({
        @Type(AddAxiomChange.class),
        @Type(RemoveAxiomChange.class),
        @Type(AddOntologyAnnotationChange.class),
        @Type(RemoveOntologyAnnotationChange.class),
        @Type(AddImportChange.class),
        @Type(RemoveImportChange.class)
})
public interface OntologyChange {

    @Nonnull
    @Deprecated
    default OWLOntologyID getOntologyId() {
        return ontologyId();
    }

    @Nonnull
    OWLOntologyID ontologyId();

    @Nonnull
    Set<OWLEntity> getSignature();

    default boolean isChangeFor(@Nonnull AxiomType<? extends OWLAxiom> axiomType) {
        return isAxiomChange() && getAxiomOrThrow().getAxiomType().equals(axiomType);
    }

    default boolean isAxiomChange() {
        return false;
    }

    default boolean isAddAxiom() {
        return false;
    }

    default boolean isRemoveAxiom() {
        return false;
    }

    /**
     * Gets the axiom that is the object of this ontology change.  This
     * method should only be called if isAxiomChange returns true.
     * @throws NoSuchElementException if this is not an axiom change
     */
    default OWLAxiom getAxiomOrThrow() throws NoSuchElementException {
        throw new NoSuchElementException();
    }

    @Nonnull
    OntologyChange replaceOntologyId(@Nonnull OWLOntologyID ontologyId);

    @Nonnull
    OWLOntologyChangeRecord toOwlOntologyChangeRecord();

    default boolean isRemoveOntologyAnnotation() {
        return false;
    }

    default boolean isAddOntologyAnnotation() {
        return false;
    }

    void accept(@Nonnull OntologyChangeVisitor visitor);

    <R> R accept(@Nonnull OntologyChangeVisitorEx<R> visitorEx);

    @Nonnull
    default OWLAnnotation getAnnotationOrThrow() {
        throw new NoSuchElementException();
    }

    @Nonnull
    default OWLImportsDeclaration getImportsDeclarationOrThrow() {
        throw new NoSuchElementException();
    }

    @Nonnull
    OntologyChange getInverseChange();
}
