/**
 * 
 */

package com.imaginea.titan.exception;

/**
 * @author kaushikr
 *
 */
public final class SystemException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1691589784787482529L;

    /**
     * 
     */
    public SystemException()
    {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public SystemException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * @param message
     */
    public SystemException(final String message)
    {
        super(message);
    }

    /**
     * @param cause
     */
    public SystemException(final Throwable cause)
    {
        super(cause);
    }
}
