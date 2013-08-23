/**
 * 
 */

package com.imaginea.titan.inf;

import java.util.Collection;

import org.codehaus.jettison.json.JSONObject;

/**
 * @author kaushikr
 *
 */
public interface Result
{
    Collection<Edge> getEdges();

    Collection<Vertex> getVertexs();

    JSONObject getJSON();
}
