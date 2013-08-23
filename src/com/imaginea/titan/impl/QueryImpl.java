/**
 * 
 */

package com.imaginea.titan.impl;

import org.codehaus.jettison.json.JSONArray;

import com.imaginea.titan.exception.SystemException;
import com.imaginea.titan.inf.ElementType;
import com.imaginea.titan.inf.Operation;
import com.imaginea.titan.inf.Query;
import com.imaginea.titan.inf.Result;
import com.tinkerpop.blueprints.Graph;

/**
 * @author kaushikr
 *
 */
public class QueryImpl implements Query
{
    final String key;
    final Operation op;
    final JSONArray values;
    final ElementType type;

    /**
     * @param key
     * @param op
     * @param values
     * @param type
     */
    public QueryImpl(final String key, final Operation op, final JSONArray values, final ElementType type)
    {
        this.key = key;
        this.op = op;
        this.values = values;
        this.type = type;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Result execute(final Graph graph)
    {
        if (null == key || "".equals(key)) {
            throw new SystemException("Query with out key not allowed");
        }

        if (null == op) {
            throw new SystemException("Query with out operation not allowed");
        }

        try {
            return op.getExecutor().newInstance().execute(graph, this);
        }
        catch (InstantiationException e) {
            throw new SystemException(e);
        }
        catch (IllegalAccessException e) {
            throw new SystemException(e);
        }
    }

    /**
     * @return
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @return
     */
    public Operation getOperation()
    {
        return op;
    }

    /**
     * @return
     */
    public JSONArray getValues()
    {
        return values;
    }

    /**
     * @return
     */
    public ElementType getType()
    {
        return type;
    }
}
