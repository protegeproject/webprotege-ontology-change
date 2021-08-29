package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.change.AddImportData;
import org.semanticweb.owlapi.change.OWLOntologyChangeRecord;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntologyID;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-27
 */
@JsonTypeName("AddImport")
public record AddImportChange(@Nonnull OWLOntologyID ontologyId,
                              @Nonnull OWLImportsDeclaration importsDeclaration) implements OntologyImportChange {

    public static AddImportChange of(@Nonnull OWLOntologyID ontologyID,
                                     @Nonnull OWLImportsDeclaration importsDeclaration) {
        return new AddImportChange(ontologyID, importsDeclaration);
    }

    @Nonnull
    @Override
    public OWLOntologyChangeRecord toOwlOntologyChangeRecord() {
        return new OWLOntologyChangeRecord(ontologyId(), new AddImportData(importsDeclaration()));
    }

    @Nonnull
    @Override
    public AddImportChange replaceOntologyId(@Nonnull OWLOntologyID ontologyId) {
        if(this.ontologyId.equals(ontologyId)) {
            return this;
        }
        else {
            return AddImportChange.of(ontologyId, importsDeclaration);
        }
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
    public RemoveImportChange getInverseChange() {
        return RemoveImportChange.of(ontologyId(), importsDeclaration);
    }
}
