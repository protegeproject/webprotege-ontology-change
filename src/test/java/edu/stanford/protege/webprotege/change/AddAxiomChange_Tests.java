package edu.stanford.protege.webprotege.change;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddAxiomChange_Tests {

    private AddAxiomChange change;

    @Mock
    private OWLOntologyID ontologyId;

    @Mock
    private OWLAxiom axiom;

    @BeforeEach
    void setUp() {
        change = AddAxiomChange.of(ontologyId, axiom);
    }

    @Test
    void isAddAxiom() {
        assertThat(change.isAddAxiom()).isTrue();
    }

    @Test
    void isRemoveAxiom() {
        assertThat(change.isRemoveAxiom()).isFalse();
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