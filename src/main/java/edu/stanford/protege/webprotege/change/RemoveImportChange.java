package edu.stanford.protege.webprotege.change;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.change.OWLOntologyChangeRecord;
import org.semanticweb.owlapi.change.RemoveImportData;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntologyID;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-27
 */
@JsonTypeName("RemoveImport")
public record RemoveImportChange(@Nonnull OWLOntologyID ontologyId,
                                 @Nonnull OWLImportsDeclaration importsDeclaration) implements OntologyImportChange {

    public static RemoveImportChange of(@Nonnull OWLOntologyID ontologyId,
                                        @Nonnull OWLImportsDeclaration importsDeclaration) {
        return new RemoveImportChange(ontologyId, importsDeclaration);
    }

    @Nonnull
    @Override
    public OWLOntologyChangeRecord toOwlOntologyChangeRecord() {
        return new OWLOntologyChangeRecord(ontologyId, new RemoveImportData(importsDeclaration));
    }

    @Nonnull
    @Override
    public RemoveImportChange replaceOntologyId(@Nonnull OWLOntologyID ontologyId) {
        if(ontologyId.equals(ontologyId)) {
            return this;
        }
        else {
            return RemoveImportChange.of(ontologyId, importsDeclaration);
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
    public AddImportChange getInverseChange() {
        return AddImportChange.of(ontologyId, importsDeclaration);
    }
}
