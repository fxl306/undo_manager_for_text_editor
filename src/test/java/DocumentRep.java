/**
 * This class creates a document for a text editor.
 * Delete and insert can be operated based on its given string and position.
 * This class is implemented for testing the UndoManagerRep class and UndoMangerFactoryRep.
 * @auther Feng Long (fxl306@case.edu)
 * @Date 04/20/2023
 */

import undo.api.Document;


public class DocumentRep implements Document {

    private StringBuffer docText = new StringBuffer();
    private int curLocus;

    public DocumentRep (StringBuffer docText, int curLocus){
        this.docText = docText;
        this.curLocus = curLocus <= docText.length()? Math.max(0,curLocus):docText.length();
    }

    public DocumentRep() {
    }

    /**
     * Deletes a string from the document.
     *
     * @param pos The position to start deletion.
     * @param s   The string to delete.
     * @throws IllegalStateException If the document doesn't have <code>s</code>
     *                               as <code>pos</code>.
     */
    @Override
    public void delete(int pos, String s) {
        setCurLocus(pos);
        try {
            if (!docText.substring(0, pos).contains(s)){
                throw new IllegalStateException(s + "is not in the target Document!");
            }
            docText.delete(pos - s.length(), pos);
            setDot(-s.length());
        } catch (StringIndexOutOfBoundsException e){
            throw (new IllegalStateException("Dot position is illegal for deletion!"));
        }


    }

    /**
     * Inserts a string into the document.
     *
     * @param pos The position to insert the string at.
     * @param s   The string to insert.
     * @throws IllegalStateException If <code>pos</code> is an illegal position
     *                               (that is, if document is shorter than that).
     */
    @Override
    public void insert(int pos, String s) {
        setCurLocus(pos);
        try {
            docText.insert(pos,s);
            setDot(s.length());
        } catch (StringIndexOutOfBoundsException e){
            throw new IllegalStateException("Dot position is illegal for insertion!");
        }

    }

    /**
     * Sets the dot (cursor) position of the document.
     *
     * @param pos The dot position to set.
     * @throws IllegalStateException If <code>pos</code> is an illegal position
     *                               (that is, if document is shorter than that).
     */
    @Override
    public void setDot(int pos) {
        if (curLocus + pos > docText.length() || curLocus < 0) {
            throw new IllegalStateException("Dot position is illegal!");
        }
        this.curLocus += pos;

    }

    public void setCurLocus(int pos) {
        this.curLocus = (pos >= 0 && pos <= docText.length())? pos : this.curLocus;
    }

    public int getCurLocus() {
        return curLocus;
    }

    public StringBuffer getDocText() {
        return docText;
    }
}
