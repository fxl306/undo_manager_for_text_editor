/**
 * This class represents the testing class for the UndoManagerRep class.
 * @auther Feng Long (fxl306@case.edu)
 * @Date 04/20/2023
 */

import undo.impl.UndoManagerRep;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UndoManagerRepTester {

    @Test
    public void makeUndoMangerTest(){
        UndoManagerRep undoManagerRep = new UndoManagerRep(new DocumentRep(new StringBuffer("Hello world!"),0),16);
        assertNotNull(undoManagerRep.getDocInput());
        assertEquals(16,undoManagerRep.getBufferSize());
        assertEquals(0,undoManagerRep.getOperations().size());
        assertEquals(-1,undoManagerRep.getLatestChangeIndex());
    }

    @Test
    public void makeUndoMangerBlankDocTest(){
        UndoManagerRep undoManagerRep = new UndoManagerRep(new DocumentRep(),0);
        assertNotNull(undoManagerRep.getDocInput());
        assertEquals(0,undoManagerRep.getBufferSize());
        assertEquals(0,undoManagerRep.getOperations().size());
        assertEquals(-1,undoManagerRep.getLatestChangeIndex());
    }

    @Test
    public void registerChangeTest(){
        // api Change needs to be implemented to test this method
    }

    @Test
    public void redoTest(){
        // api Change needs to be implemented to test this method
    }
    @Test
    public void undoTest(){
        // api Change needs to be implemented to test this method
    }


}
