package edu.stanford.protege.webprotege.change;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class AddAxiomChange_Tests {

    @Autowired
    private JacksonTester<AddAxiomChange> tester;

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

    @Test
    void shouldSerializeEvent() throws IOException {
        var change = new AddAxiomChange(new OWLOntologyID(IRI.create("http://example.org/ont")),
                                       new OWLDeclarationAxiomImpl(new OWLClassImpl(IRI.create("http://example.org/A")),
                                                                   Collections.emptySet()));
        var json = tester.write(change);
        var parsed = tester.parse(json.getJson());
        var parsedChange = parsed.getObject();
        assertThat(parsedChange).isEqualTo(change);
    }
}