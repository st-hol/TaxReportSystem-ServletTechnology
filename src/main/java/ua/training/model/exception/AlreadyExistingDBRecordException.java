package ua.training.model.exception;

public class AlreadyExistingDBRecordException extends Exception{
    public AlreadyExistingDBRecordException() {}
    public AlreadyExistingDBRecordException(String m) {
        super(m);
    }
    public AlreadyExistingDBRecordException(Throwable th) {
        super(th);
    }
    public AlreadyExistingDBRecordException(String m, Throwable th) {
        super(m, th);
    }
    public AlreadyExistingDBRecordException(String m, Throwable th, boolean suppression, boolean stackTrace) {
        super(m, th, suppression, stackTrace);
    }
}
