public class InvalidCommandException extends Exception {
    protected String errorCode;
    /**
     * Error Code list:
     * 0:
     * 1:
     * 2:
     * 3:
     * 4:
     * 5:
     */
    public InvalidCommandException(String errorCode) {
        this.errorCode = errorCode;
    }
}
