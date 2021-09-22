package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public interface OntologyChange {

    @Nonnull
    @Deprecated
    default OWLOntologyID getOntologyId() {
        return ontologyId();
    }

    @Nonnull
    OWLOntologyID ontologyId();

    @Nonnull
    @JsonIgnore
    Set<OWLEntity> getSignature();

    @JsonIgnore
    default boolean isChangeFor(@Nonnull AxiomType<? extends OWLAxiom> axiomType) {
        return isAxiomChange() && getAxiomOrThrow().getAxiomType().equals(axiomType);
    }

    @JsonIgnore
    default boolean isAxiomChange() {
        return false;
    }

    @JsonIgnore
    default boolean isAddAxiom() {
        return false;
    }

    @JsonIgnore
    default boolean isRemoveAxiom() {
        return false;
    }

    /**
     * Gets the axiom that is the object of this ontology change.  This
     * method should only be called if isAxiomChange returns true.
     * @throws NoSuchElementException if this is not an axiom change
     */
    @JsonIgnore
    default OWLAxiom getAxiomOrThrow() throws NoSuchElementException {
        throw new NoSuchElementException();
    }

    @Nonnull
    @JsonIgnore
    OntologyChange replaceOntologyId(@Nonnull OWLOntologyID ontologyId);

    @Nonnull
    @JsonIgnore
    OWLOntologyChangeRecord toOwlOntologyChangeRecord();

    @JsonIgnore
    default boolean isRemoveOntologyAnnotation() {
        return false;
    }

    @JsonIgnore
    default boolean isAddOntologyAnnotation() {
        return false;
    }

    void accept(@Nonnull OntologyChangeVisitor visitor);

    <R> R accept(@Nonnull OntologyChangeVisitorEx<R> visitorEx);

    @Nonnull
    @JsonIgnore
    default OWLAnnotation getAnnotationOrThrow() {
        throw new NoSuchElementException();
    }

    @Nonnull
    @JsonIgnore
    default OWLImportsDeclaration getImportsDeclarationOrThrow() {
        throw new NoSuchElementException();
    }

    @Nonnull
    @JsonIgnore
    OntologyChange getInverseChange();
}
