/**
 * 
 */

package com.imaginea.titan.inf;

import org.codehaus.jettison.json.JSONObject;

/**
 * @author kaushikr
 * 
 */
public interface GraphService
{
    /**
     * @param vertex
     * @return
     */
    JSONObject addVertex(JSONObject vertex);

    /**
     * @param edge
     * @param in
     * @param out
     * @return
     */
    JSONObject addEdge(JSONObject edge, String outUID, String inUID);

    /**
     * 
     * @return
     */
    JSONObject getGraph();

    /**
     * @param jsonObject
     * @return
     */
    JSONObject doQuery(JSONObject jsonObject);

}
