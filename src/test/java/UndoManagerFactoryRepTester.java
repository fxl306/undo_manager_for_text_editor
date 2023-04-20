/**
 * This class represents the testing class for the UndoMangerFactoryRep.
 * @auther Feng Long (fxl306@case.edu)
 * @Date 04/20/2023
 */
import undo.api.UndoManager;
import undo.impl.UndoManagerFactoryRep;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class UndoManagerFactoryRepTester {

    @Test
    public void makeUndoMangerFactoryTest() {
        DocumentRep doc = new DocumentRep(new StringBuffer("Hello world"), 0);
        UndoManager undoManager = new UndoManagerFactoryRep().createUndoManager(doc, 12);
        assertNotNull(undoManager);
    }

    @Test
    public void makeUndoMangerFactoryZeroBufferSizeTest() {
        DocumentRep doc = new DocumentRep(new StringBuffer("Hello world"), 0);
        UndoManager undoManager = new UndoManagerFactoryRep().createUndoManager(doc, 0);
        assertNotNull(undoManager);
    }

    @Test
    public void makeUndoMangerFactoryNegBufferSizeTest() {
        DocumentRep doc = new DocumentRep(new StringBuffer("Hello world"), 0);
        Exception exception = assertThrows(IllegalStateException.class, () -> new UndoManagerFactoryRep().createUndoManager(doc, -5));
        assertEquals("Buffer size must be positive!", exception.getMessage());
    }


}
