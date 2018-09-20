package com.tvestergaard.rest.exceptions;

public class ErrorMessage
{

    private final String    description;
    private final int       code;
    private final boolean   isDebug;
    private final Exception cause;

    public ErrorMessage(String description, int code, boolean isDebug, Exception cause)
    {
        this.description = description;
        this.code = code;
        this.isDebug = isDebug;
        this.cause = cause;
    }

    public String getDescription()
    {
        return this.description;
    }

    public int getCode()
    {
        return this.code;
    }

    public boolean isDebug()
    {
        return this.isDebug;
    }

    public Exception getCause()
    {
        if (!isDebug)
            return null;

        return this.cause;
    }
}
