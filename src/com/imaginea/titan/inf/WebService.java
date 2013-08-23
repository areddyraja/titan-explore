/**
 * 
 */

package com.imaginea.titan.inf;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

/**
 * @author mpujari
 * @version $Id$ 
 */
public interface WebService
{

    @POST
    @Path("/addVertex")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    JSONObject addVertex(JSONObject jsonObject);

    @POST
    @Path("/addEdge")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    JSONObject addEdge(JSONObject jsonObject);

    @GET
    @Path("/getGraph")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    JSONObject getGraph();

    // {vertices: JSONArray, edges: JSONArray}
    @POST
    @Path("/query")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    JSONObject doQuery(JSONObject jsonObject);

}
