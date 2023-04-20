/**
 * This class creates an undo-manager for a document, with a given limit of operations can be performed.
 * @auther Feng Long (fxl306@case.edu)
 * @Date 04/20/2023
 */

package undo.impl;

import undo.api.Change;
import undo.api.UndoManager;
import undo.api.UndoManagerFactory;
import undo.api.Document;

import java.util.Objects;


/**
 * Creates an undo manager for a {@link Document}.
 *
 * @param doc The document to create the {@link UndoManager} for.
 * @param bufferSize The number of {@link Change}es stored.
 * @return The {@link UndoManager} created.
 */
public class UndoManagerFactoryRep implements UndoManagerFactory{
    @Override
    public UndoManager createUndoManager(Document doc, int bufferSize){
        if (bufferSize < 0){
            throw new IllegalStateException("Buffer size must be positive!");
        }
        return new UndoManagerRep(Objects.requireNonNull(doc, "Document cannot be null!"), bufferSize);
    };
}
