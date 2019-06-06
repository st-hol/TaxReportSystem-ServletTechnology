package ua.training.model.exception;

public class AlreadyExistingDBRecordException extends Exception{
    public AlreadyExistingDBRecordException() {}
    public AlreadyExistingDBRecordException(String m) {
        super(m);
    }
}
