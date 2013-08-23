/**
 * 
 */

package com.imaginea.titan.impl;

import org.codehaus.jettison.json.JSONObject;

import com.imaginea.titan.inf.Edge;
import com.imaginea.titan.inf.ElementType;
import com.imaginea.titan.inf.SystemKeys;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;

/**
 * @author kaushikr
 *
 */
public class EdgeImpl extends ElementImpl implements Edge
{

    /**
     * @param element
     */
    public EdgeImpl(final com.tinkerpop.blueprints.Edge element)
    {
        this.element = element;
    }

    /**
     * @param element
     */
    public EdgeImpl(final com.tinkerpop.blueprints.Edge element, final String key)
    {
        this.element = element;
        this.comparisonKey = key;
    }

    /**
     * @param element
     */
    public EdgeImpl(final com.tinkerpop.blueprints.Edge element, final String key, final int ascOrder)
    {
        this.element = element;
        this.comparisonKey = key;
        this.asc = ascOrder;
    }

    /**
     * @see com.tinkerpop.blueprints.Edge#getLabel()
     */
    @Override
    public String getLabel()
    {
        return ((com.tinkerpop.blueprints.Edge) this.element).getLabel();
    }

    /**
     * @see com.tinkerpop.blueprints.Edge#getVertex(com.tinkerpop.blueprints.Direction)
     */
    @Override
    public Vertex getVertex(Direction arg0) throws IllegalArgumentException
    {
        return ((com.tinkerpop.blueprints.Edge) this.element).getVertex(arg0);
    }

    @Override
    public JSONObject getUidJSONObject()
    {
        return super.getUidJSONObject(SystemKeys.E_ID);
    }

    @Override
    public String getUID()
    {
        return super.getUID(SystemKeys.E_ID);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Edge o)
    {
        return super.compareTo(o);
    }

    @Override
    public JSONObject json()
    {
        return super.json(ElementType.EDGE);
    }
}
