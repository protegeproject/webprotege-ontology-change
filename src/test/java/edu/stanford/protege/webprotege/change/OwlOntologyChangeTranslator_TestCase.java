package edu.stanford.protege.webprotege.change;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-29
 */
@SpringBootTest
public class OwlOntologyChangeTranslator_TestCase {

    private OwlOntologyChangeTranslator translator;

    @Mock
    private OwlOntologyChangeTranslatorVisitor visitor;

    @Mock
    private OWLOntologyChange change;

    @Mock
    private OntologyChange ontologyChange;

    @BeforeEach
    public void setUp() {
        translator = new OwlOntologyChangeTranslator(visitor);
        when(change.accept(visitor))
                .thenReturn(ontologyChange);
    }

    @Test
    public void shouldGetChange() {
        var result = translator.toOntologyChange(change);
        assertThat(result, is(ontologyChange));
    }
}
