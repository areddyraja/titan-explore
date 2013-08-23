/*
 * ElementType.java
 */

/*
 * Copyright © 2007-2008 , Agentrics LLC – All Rights Reserved.
 */

package com.imaginea.titan.inf;

/**
 * @author kaushikr
 * @version $Id$ 
 */
public enum ElementType
{
    VERTEX, EDGE;

    public static ElementType parse(final String in)
    {
        for (final ElementType e : values()) {
            if (e.name().equalsIgnoreCase(in)) {
                return e;
            }
        }

        return null;
    }
}
