/**
 * 
 */

package com.imaginea.titan.impl;

import java.util.Collection;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.imaginea.titan.exception.SystemException;
import com.imaginea.titan.inf.Edge;
import com.imaginea.titan.inf.Result;
import com.imaginea.titan.inf.Vertex;

/**
 * @author kaushikr
 *
 */
public class ResultImpl implements Result
{
    private final Collection<Edge> edges;
    private final Collection<Vertex> vertexs;

    /**
     * @param edges
     * @param vertexs
     */
    public ResultImpl(final Collection<Edge> edges, final Collection<Vertex> vertexs)
    {
        this.edges = edges;
        this.vertexs = vertexs;
    }

    /**
     * @see com.imaginea.titan.inf.Result#getEdges()
     */
    @Override
    public Collection<Edge> getEdges()
    {
        return this.edges;
    }

    /* (non-Javadoc)
     * @see com.imaginea.titan.inf.Result#getVertexs()
     */
    @Override
    public Collection<Vertex> getVertexs()
    {
        return this.vertexs;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public JSONObject getJSON()
    {
        final JSONObject jsonObject = new JSONObject();

        if (null != edges) {
            JSONArray jsonArray = new JSONArray();
            for (Edge e : edges) {
                jsonArray.put(e.getId());
            }

            try {
                jsonObject.append("edges", jsonArray);
            }
            catch (JSONException e) {
                throw new SystemException(e);
            }
        }

        if (null != vertexs) {
            JSONArray jsonArray = new JSONArray();
            for (Vertex v : vertexs) {
                jsonArray.put(v.getId());
            }

            try {
                jsonObject.append("vertices", jsonArray);
            }
            catch (JSONException e) {
                throw new SystemException(e);
            }
        }

        return jsonObject;
    }
}
