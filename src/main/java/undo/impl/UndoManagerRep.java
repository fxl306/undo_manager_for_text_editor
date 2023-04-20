/**
 * This class represents an undo-manager for a text editor.
 * Undo and redo can be operated on the given document based on its operation history.
 * @auther Feng Long (fxl306@case.edu)
 * @Date 04/20/2023
 */
package undo.impl;
import undo.api.UndoManager;
import undo.api.Change;
import undo.api.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

/**
 * A manager for undo and redo operations to {@link Document}s, based
 * on {@link Change} objects.
 */
@Getter
public class UndoManagerRep implements UndoManager {
    private final Document docInput;
    private final int bufferSize;
    private int latestChangeIndex = -1; // record the index of the latest change operation
    private final List<Change> operations; // stores each operation made, up to the latest buffersize times


    /**
     * Constructor to create an undoManager that has implemented the api UndoManager.
     * @param doc The document to be operated.
     * @param bufferSize The max number of change operations to store.
     */
    public UndoManagerRep(Document doc, int bufferSize){
        this.docInput = Objects.requireNonNull(doc, "Document cannot be null!");
        this.bufferSize = bufferSize >= 0? bufferSize : 20; // set default to 20 if bufferSize is not illegal
        this.operations = new ArrayList<>(this.bufferSize);
    }

    /**
     * Registers a new change in this undo manager. If the buffer
     * size of the undo manager is filled, replace the oldest change
     * with the one provided to this method.
     *
     * @param change The change to register.
     */
    @Override
    public void registerChange(Change change) {
       Objects.requireNonNull(change, "The change cannot be null!");
       
       if(this.latestChangeIndex + 1 < operations.size()){ // check undo(es) is performed previously
           operations.subList(this.latestChangeIndex +1, operations.size()).clear(); // update the operation via remove the operation(s) after undo
       }

       if (operations.size() == this.bufferSize){ // when reach to the biggest memory of operation
           operations.remove(0);
           latestChangeIndex--;
       }

       operations.add(change);
       latestChangeIndex++;
       change.apply(Objects.requireNonNull(docInput, "Document cannot be null!"));
    }

    /**
     * Returns <code>true</code> if there is currently a change that 
     * can be undone, and <code>false</code> otherwise. 
     */
    @Override
    public boolean canUndo(){
        return latestChangeIndex >= 0;
    }

    /**
     * Performs the undo operation of the current change.
     *
     * @throws IllegalStateException If the manager is in a state that 
     * 			does not allow an undo (that is if either {@link #canUndo()}
     * 			would have returned <code>false</code>, or the application
     * 			of the change failed).
     */
    @Override
    public void undo(){
        if (!canUndo())
            return;
        operations.get(latestChangeIndex).revert(Objects.requireNonNull(docInput, "Document cannot be null!"));
    }

    /**
     * Returns <code>true</code> if there is currently a change that 
     * can be redone, and <code>false</code> otherwise. 
     */
    @Override
    public boolean canRedo(){
        return latestChangeIndex + 1 < operations.size() && operations.size() > 0;
    }

    /**
     * Performs the redo operation of the current change.
     *
     * @throws IllegalStateException If the manager is in a state that 
     *  		does not allow an redo (that is if either {@link #canRedo()}
     *  		would have returned <code>false</code>, or the application
     *  		of the change failed).
     */
    @Override
    public void redo(){
        if (!canRedo())
            return;
        latestChangeIndex++;
        operations.get(latestChangeIndex).apply(Objects.requireNonNull(docInput, "Document cannot be null!"));
    }



}
