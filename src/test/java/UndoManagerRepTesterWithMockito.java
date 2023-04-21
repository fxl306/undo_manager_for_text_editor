import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import undo.impl.*;
import undo.api.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class UndoManagerRepTesterWithMockito {
    @Mock
    Change mockChange;

     //@Mock
     //ChangeFactory mockChangeFactory;

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
        // when(mockChangeFactory.createInsertion(0, "NewAdd", 0, 5)).return(mockChange);
        doNothing().when(mockChange).apply(doc);

        undoManagerRep.registerChange(mockChange);

        assertTrue(undoManagerRep.getOperations().size() == 1);
        assertTrue(undoManagerRep.getBufferSize() == 16);
        assertTrue(undoManagerRep.getLatestChangeIndex() == 0);

        verify(mockChange).apply(doc);
    }


}