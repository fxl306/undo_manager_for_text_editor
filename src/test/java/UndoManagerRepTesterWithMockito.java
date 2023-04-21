
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This class represents the testing class for the UndoManagerRep class.
 * Mockito is used here to mock the Change api
 * @auther Feng Long (fxl306@case.edu)
 * @Date 04/21/2023
 */


import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import undo.impl.*;
import undo.api.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class UndoManagerRepTesterWithMockito {
    @Mock
    Change mockChange;

    DocumentRep doc;

    UndoManagerRep undoManagerRep;

    @BeforeEach
    public void setup() {
        doc = new DocumentRep(new StringBuffer("Hello world"), 0);
        undoManagerRep = new UndoManagerRep(doc, 16);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerChangeTest(){

        // mock a virtual Change operation as the ChangeImpl.apply() is not implemented.
        doNothing().when(mockChange).apply(doc);

        undoManagerRep.registerChange(mockChange);

        assertTrue(undoManagerRep.getOperations().size() == 1);
        assertTrue(undoManagerRep.getBufferSize() == 16);
        assertTrue(undoManagerRep.getLatestChangeIndex() == 0);

        verify(mockChange).apply(doc);
    }

    @Test
    public void undoInsertionTest() {

        // mock a virtual insertionChange operation as the ChangeImpl.apply() is not implemented.
        doAnswer(new Answer<Document>() {
            public Document answer(InvocationOnMock invocation) {
                doc.insert(0, "NewAdd");
                return doc;
            }
        }).when(mockChange).apply(doc);
        undoManagerRep.registerChange(mockChange); // mock the insertionChange operation
        verify(mockChange).apply(doc);
        assertEquals("NewAddHello world", doc.getDocText().toString()); // verify successful insertionChange

        // mock a virtual undo operation as the ChangeImp.revert() is not implemented.
        doAnswer(new Answer<Document>() {
            public Document answer(InvocationOnMock invocation) {
                doc.delete(6, "NewAdd");
                return doc;
            }
        }).when(undoManagerRep.getOperations().get(undoManagerRep.getLatestChangeIndex())).revert(doc);

        // Perform undo previous operation(insertionChange)
        undoManagerRep.undo();
        assertEquals("Hello world", doc.getDocText().toString());
    }


}