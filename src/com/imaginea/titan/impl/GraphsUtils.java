/**
 * 
 */

package com.imaginea.titan.impl;

import java.util.Collection;
import java.util.LinkedList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.imaginea.titan.exception.SystemException;
import com.imaginea.titan.inf.SystemKeys;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

/**
 * @author kaushikr
 * 
 */
public final class GraphsUtils
{
    private GraphsUtils()
    {
    }

    /**
     * @param graph
     * @param vertex
     * @return
     * @throws JSONException
     */
    public static com.tinkerpop.blueprints.Vertex createVertex(final Graph graph, final JSONObject vertex)
        throws JSONException
    {
        final Vertex coreVertex = graph.addVertex(null);
        coreVertex.setProperty(SystemKeys.V_ID.name(), getUID(coreVertex));

        final JSONArray names = vertex.names();
        final int length = names.length();

        for (int i = 0; i < length; i++) {
            final String key = names.getString(i);
            final Object value = vertex.get(key);
            final Vertex valueVertex = graph.addVertex(null);
            valueVertex.setProperty(SystemKeys.ELEMENT_VALUE.name(), value);
            valueVertex.setProperty(SystemKeys.V_ID.name(), getUID(valueVertex));
            final Edge keyEdge = graph.addEdge(null, coreVertex, valueVertex, key);
            keyEdge.setProperty(SystemKeys.E_ID.name(), getUID(keyEdge));
        }

        return coreVertex;
    }

    /**
     * @param _vertex
     * @return
     */
    public static com.imaginea.titan.inf.Vertex wrap(final com.tinkerpop.blueprints.Vertex _vertex)
    {
        return new VertexImpl(_vertex);
    }

    /**
     * @param graph
     * @param edge
     * @param outUID
     * @param inUID
     * @return
     * @throws JSONException 
     */
    public static com.tinkerpop.blueprints.Edge createEdge(
        final Graph graph,
        final JSONObject edge,
        final String outUID,
        final String inUID) throws JSONException
    {
        final Iterable<Vertex> outVertices = graph.getVertices(SystemKeys.V_ID.name(), outUID);
        final Iterable<Vertex> inVertices = graph.getVertices(SystemKeys.V_ID.name(), inUID);
        Vertex out = null, in = null;

        if (null != outVertices && outVertices.iterator().hasNext()) {
            out = outVertices.iterator().next();
        }
        else {
            throw new SystemException("Invalid out-id: " + outUID);
        }

        if (null != inVertices && inVertices.iterator().hasNext()) {
            in = inVertices.iterator().next();
        }
        else {
            throw new SystemException("Invalid in-id: " + inUID);
        }

        final JSONArray names = edge.names(); // ["latency","throughput","outUID","inUID"]
        final int length = names.length();
        final Collection<Edge> collection = new LinkedList<Edge>();

        for (int i = 0; i < length; i++) {
            final String key = names.getString(i);
            final Object value = edge.get(key);
            final Edge keyEdge = graph.addEdge(null, out, in, key);
            keyEdge.setProperty(SystemKeys.E_ID.name(), getUID(keyEdge));
            keyEdge.setProperty(SystemKeys.ELEMENT_VALUE.name(), value);
            collection.add(keyEdge);
        }

        if (0 != collection.size()) {
            return collection.iterator().next();
        }
        else {
            throw new SystemException("No edge added.");
        }
    }

    /**
     * @param _edge
     * @return
     */
    public static com.imaginea.titan.inf.Edge wrap(com.tinkerpop.blueprints.Edge _edge)
    {
        return new EdgeImpl(_edge);
    }

    /**
     * @param element
     * @return
     */
    private static String getUID(final Element element)
    {
        return "" + element.getId();
    }

    /**
     * @param _vrt
     * @param key
     * @return
     */
    public static com.imaginea.titan.inf.Vertex wrap(final Vertex _vrt, String key)
    {
        return new VertexImpl(_vrt, key);
    }

    /**
     * @param _edg
     * @param key
     * @return
     */
    public static com.imaginea.titan.inf.Edge wrap(final Edge _edg, String key)
    {
        return new EdgeImpl(_edg, key);
    }

    /**
     * @param _vrt
     * @param key
     * @return
     */
    public static com.imaginea.titan.inf.Vertex wrap(final Vertex _vrt, String key, final int order)
    {
        return new VertexImpl(_vrt, key, order);
    }

    /**
     * @param _edg
     * @param key
     * @return
     */
    public static com.imaginea.titan.inf.Edge wrap(final Edge _edg, String key, final int order)
    {
        return new EdgeImpl(_edg, key, order);
    }
}
