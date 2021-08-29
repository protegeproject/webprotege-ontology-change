package edu.stanford.protege.webprotege.change;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-29
 */
@JsonTest
public class RemoveAxiomChange_Tests {

    private RemoveAxiomChange change;

    @Mock
    private OWLOntologyID ontologyId;

    @Mock
    private OWLAxiom axiom;

    @BeforeEach
    void setUp() {
        change = RemoveAxiomChange.of(ontologyId, axiom);
    }

    @Test
    void isAddAxiom() {
        assertThat(change.isAddAxiom()).isFalse();
    }

    @Test
    void isRemoveAxiom() {
        assertThat(change.isRemoveAxiom()).isTrue();
    }

    @Test
    void isAxiomChange() {
        assertThat(change.isAxiomChange()).isTrue();
    }

    @Test
    void getInverseChange() {
        var inverse = change.getInverseChange();
        assertThat(inverse.axiom()).isEqualTo(axiom);
    }
}
