/**
 * 
 */

package com.imaginea.titan.impl;

import org.codehaus.jettison.json.JSONObject;

import com.imaginea.titan.inf.ElementType;
import com.imaginea.titan.inf.SystemKeys;
import com.imaginea.titan.inf.Vertex;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.VertexQuery;

/**
 * @author kaushikr
 *
 */
public class VertexImpl extends ElementImpl implements Vertex
{
    /**
     * @param v
     */
    public VertexImpl(final com.tinkerpop.blueprints.Vertex v)
    {
        this.element = v;
    }

    /**
     * @param v
     * @param key
     */
    public VertexImpl(final com.tinkerpop.blueprints.Vertex v, final String key)
    {
        this.element = v;
        this.comparisonKey = key;
    }

    /**
     * @param v
     * @param key
     */
    public VertexImpl(final com.tinkerpop.blueprints.Vertex v, final String key, final int ascOrder)
    {
        this.element = v;
        this.comparisonKey = key;
        this.asc = ascOrder;
    }

    /**
     * @see com.tinkerpop.blueprints.Vertex#addEdge(java.lang.String, com.tinkerpop.blueprints.Vertex)
     */
    @Override
    public Edge addEdge(String arg0, com.tinkerpop.blueprints.Vertex arg1)
    {
        return ((com.tinkerpop.blueprints.Vertex) this.element).addEdge(arg0, arg1);
    }

    /**
     * @see com.tinkerpop.blueprints.Vertex#getEdges(com.tinkerpop.blueprints.Direction, java.lang.String[])
     */
    @Override
    public Iterable<Edge> getEdges(Direction arg0, String... arg1)
    {
        return ((com.tinkerpop.blueprints.Vertex) this.element).getEdges(arg0, arg1);
    }

    /**
     * @see com.tinkerpop.blueprints.Vertex#getVertices(com.tinkerpop.blueprints.Direction, java.lang.String[])
     */
    @Override
    public Iterable<com.tinkerpop.blueprints.Vertex> getVertices(Direction arg0, String... arg1)
    {
        return ((com.tinkerpop.blueprints.Vertex) this.element).getVertices(arg0, arg1);
    }

    /**
     * @see com.tinkerpop.blueprints.Vertex#query()
     */
    @Override
    public VertexQuery query()
    {
        return ((com.tinkerpop.blueprints.Vertex) this.element).query();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public JSONObject getUidJSONObject()
    {
        return super.getUidJSONObject(SystemKeys.V_ID);
    }

    @Override
    public String getUID()
    {
        return super.getUID(SystemKeys.V_ID);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Vertex o)
    {
        return super.compareTo(o);
    }

    @Override
    public JSONObject json()
    {
        return super.json(ElementType.VERTEX);
    }

}
