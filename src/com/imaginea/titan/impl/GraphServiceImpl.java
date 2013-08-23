/**
 * 
 */

package com.imaginea.titan.impl;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.imaginea.titan.conf.BootStrapper;
import com.imaginea.titan.exception.SystemException;
import com.imaginea.titan.inf.GraphService;
import com.imaginea.titan.inf.SearchKeys;
import com.thinkaurelius.titan.core.TitanTransaction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

/**
 * @author kaushikr
 * 
 */
public class GraphServiceImpl implements GraphService
{
    /**
     * @see com.imaginea.titan.inf.GraphService#addVertex(org.json.JSONObject)
     */
    @Override
    public JSONObject addVertex(final JSONObject vertexJsonObj)
    {
        final TitanTransaction graph = BootStrapper.instance().getTransaction();
        com.tinkerpop.blueprints.Vertex vertex;
        try {
            vertex = GraphsUtils.createVertex(graph, vertexJsonObj);
        }
        catch (JSONException e) {
            throw new SystemException(e);
        }
        return GraphsUtils.wrap(vertex).getUidJSONObject();
    }

    /**
     * @see com.imaginea.titan.inf.GraphService#addEdge(org.json.JSONObject, java.lang.String, java.lang.String)
     */
    @Override
    public JSONObject addEdge(final JSONObject edge, final String outUID, final String inUID)
    {
        final TitanTransaction graph = BootStrapper.instance().getTransaction();
        com.tinkerpop.blueprints.Edge _edge;
        try {
            _edge = GraphsUtils.createEdge(graph, edge, outUID, inUID);
        }
        catch (JSONException e) {
            throw new SystemException(e);
        }

        return GraphsUtils.wrap(_edge).getUidJSONObject();
    }

    /**
     * @see com.imaginea.titan.inf.GraphService#getGraph()
     */
    @Override
    public JSONObject getGraph()
    {
        final TitanTransaction graph = BootStrapper.instance().getTransaction();

        JSONArray vertexArray = new JSONArray();
        Iterable<Vertex> vertices = graph.getVertices();
        for (Vertex v : vertices) {
            vertexArray.put(GraphsUtils.wrap(v).json());
        }

        JSONArray edgesArray = new JSONArray();
        Iterable<Edge> edges = graph.getEdges();
        for (Edge e : edges) {
            edgesArray.put(GraphsUtils.wrap(e).json());
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("vertex", vertexArray);
            jsonObject.put("edges", edgesArray);
            return jsonObject;
        }
        catch (JSONException e) {
            new SystemException(e);
        }
        return jsonObject;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public JSONObject doQuery(JSONObject jsonObject)
    {
        final TitanTransaction graph = BootStrapper.instance().getTransaction();
        try {
            return SearchKeys.parse(jsonObject).execute(graph).getJSON();
        }
        catch (JSONException e) {
            throw new SystemException(e);
        }
    }

}
