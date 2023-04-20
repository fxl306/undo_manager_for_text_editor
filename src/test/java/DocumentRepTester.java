/**
 * This class represents the testing class for class DocumentRep
 * This class is implemented for testing the UndoManagerRep class and UndoMangerFactoryRep.
 * @auther Feng Long (fxl306@case.edu)
 * @Date 04/20/2023
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DocumentRepTester {

    @Test
    public void DocumentRepWithTextTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        assertEquals("Hello world!",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());
    }

    @Test
    public void makeDocumentRepWithNoTextTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer(),8);
        assertEquals("",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());
    }

    @Test
    public void makeDocumentRepWithNegCurPositionTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("12345bye"),-5);
        assertEquals("12345bye",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());
    }

    @Test
    public void deleteDocumentRepWithTextTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        docRep.delete(6, "llo ");
        assertEquals("Heworld!",docRep.getDocText().toString());
        assertEquals(2,docRep.getCurLocus());
    }
    @Test
    public void deleteDocumentRepWithAllTextTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        docRep.delete(12, "Hello world!");
        assertEquals("",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());
    }

    @Test
    public void deleteDocumentRepWithAbsentPosTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        Exception exception = assertThrows(IllegalStateException.class, () -> docRep.delete(18, "llo "));
        assertEquals("Dot position is illegal for deletion!", exception.getMessage());
        assertEquals("Hello world!",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());
    }

    @Test
    public void deleteDocumentRepWithNegPosTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        Exception exception = assertThrows(IllegalStateException.class, () -> docRep.delete(-3, "llo "));
        assertEquals("Dot position is illegal for deletion!", exception.getMessage());
        assertEquals("Hello world!",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());
    }

    @Test
    public void deleteDocumentRepWithAbsentTextTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        Exception exception = assertThrows(IllegalStateException.class, () -> docRep.delete(4, "llo "));
        assertEquals("llo is not in the target Document!", exception.getMessage());
        assertEquals("Hello world!",docRep.getDocText().toString());
        assertEquals(4,docRep.getCurLocus());
    }

    @Test
    public void deleteDocumentRepWithNoTextTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer(),0);
        Exception exception = assertThrows(IllegalStateException.class, () -> docRep.delete(0, "llo "));
        assertEquals("llo is not in the target Document!", exception.getMessage());
        assertEquals("",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());
    }

    @Test
    public void insertTestToDocumentRepTextFrontTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        docRep.insert(0, "Head! ");
        assertEquals("Head! Hello world!",docRep.getDocText().toString());
        assertEquals(6,docRep.getCurLocus());
    }

    @Test
    public void insertTestIntoDocumentRepTextTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        docRep.insert(6, "new ");
        assertEquals("Hello new world!",docRep.getDocText().toString());
        assertEquals(10,docRep.getCurLocus());
    }

    @Test
    public void insertTestToDocumentRepTextEndTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        docRep.insert(12, "tail! ");
        assertEquals("Hello world!tail! ",docRep.getDocText().toString());
        assertEquals(18,docRep.getCurLocus());
    }

    @Test
    public void insertTestToDocumentRepWithNoTextTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer(),0);
        docRep.insert(0, "New addition! ");
        assertEquals("New addition! ",docRep.getDocText().toString());
        assertEquals(14,docRep.getCurLocus());
    }


    @Test
    public void insertTestIntoDocumentRepTextAbsentPosTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        Exception exception = assertThrows(IllegalStateException.class, () -> docRep.insert(15, "new "));
        assertEquals("Dot position is illegal for insertion!", exception.getMessage());
        assertEquals("Hello world!",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());
    }

    @Test
    public void insertTestIntoDocumentRepTextNegPosTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("Hello world!"),0);
        Exception exception = assertThrows(IllegalStateException.class, () -> docRep.insert(-5, "new "));
        assertEquals("Dot position is illegal for insertion!", exception.getMessage());
        assertEquals("Hello world!",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());
    }

    @Test
    public void deleteInsertFrontTextDocumentRepTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("First Second!"),0);
        docRep.delete(6, "First ");
        assertEquals("Second!",docRep.getDocText().toString());
        assertEquals(0,docRep.getCurLocus());

        docRep.insert(0, "NewFirst ");
        assertEquals("NewFirst Second!",docRep.getDocText().toString());
        assertEquals(9,docRep.getCurLocus());
    }

    @Test
    public void insertDeleteMidDocumentRepTest(){
        DocumentRep docRep = new DocumentRep(new StringBuffer("First Second!"),0);
        docRep.insert(6, "newAdd ");
        assertEquals("First newAdd Second!",docRep.getDocText().toString());
        assertEquals(13,docRep.getCurLocus());

        docRep.delete(13, "newAdd ");
        assertEquals("First Second!",docRep.getDocText().toString());
        assertEquals(6,docRep.getCurLocus());
    }


}
