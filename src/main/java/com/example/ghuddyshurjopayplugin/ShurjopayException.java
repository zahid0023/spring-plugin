package com.example.ghuddyshurjopayplugin;

public class ShurjopayException extends Exception{
    private static final long serialVersionUID = 1886007201833873575L;
    public ShurjopayException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates ShurjopayException with message only
     *
     * @param message the shurjopay's exception message.
     */
    public ShurjopayException(String message) {
        super(message);
    }
}
