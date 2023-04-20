This application is an simple application of an undo-manager for a text editor.

- A set of interfaces of this undo-manager is included in the **undo.api** pacakage.

  

- Only interfaces **UndoManager** and **UndoManagerFactory** are implemented in the **undo.impl pacakage. The other interfaces are used "as is" - i. e.
  the implementation of UndoManager and UndoManagerFactory should not
  rely on any specific implementation of the other interfaces. 

  

- The **test class** for above two api is included in the test folder. To acheive this, the interface Docment is implemented in this folder and its test class is also made.

  

- For the **thread-safety** concerns:

  - If the Document only allows one text-editor to edit at a time, then synchronization can be used to lock on Object or Class to make sure only one thread is executing the synchronized code.

    In this case, in the UndoManger, method registerChange, redo, and undo shoule be synchronized. 

  - If the Document functions like a GoogleDoc, which allows users to change a document at the same time without any conflict, that is another story of a complex system.  

    1. To allow mutiple users to edito on the same documents,  algorithms such as Operational Transformation (OT) and Conflict-free Replicated Data Types (CRDTs) can be used for collabratiing editing.
    2. To allow simultaneous edits to merge efficently wiouth conflicts, complex system such as GoogleDoc uses collaborative protocols on the communications between the users (client's web browser) and the Google servers (stores the Document).