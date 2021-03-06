package edu.stanford.protege.webprotege.change;

import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-27
 */
public interface OntologyImportChange extends OntologyChange {

    @Nonnull
    @Deprecated
    default OWLImportsDeclaration getImportsDeclaration() {
        return importsDeclaration();
    }

    public OWLImportsDeclaration importsDeclaration();

    @Nonnull
    @Override
    default Set<OWLEntity> getSignature() {
        return Collections.emptySet();
    }

    @Nonnull
    @Override
    default OWLImportsDeclaration getImportsDeclarationOrThrow() {
        return importsDeclaration();
    }
}
