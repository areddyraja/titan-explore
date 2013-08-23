
package com.imaginea.titan.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.imaginea.titan.exception.SystemException;
import com.imaginea.titan.impl.GraphServiceImpl;
import com.imaginea.titan.inf.GraphService;
import com.imaginea.titan.inf.SystemKeys;
import com.imaginea.titan.inf.WebService;

/**
 * 
 * @author mpujari
 *
 */
@Path("/titanService")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class TitanService implements WebService
{
    private GraphService graphService = new GraphServiceImpl();

    /** 
     * {@inheritDoc}
     */
    @Override
    @POST
    @Path("/addVertex")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public JSONObject addVertex(JSONObject jsonObject)
    {
        return graphService.addVertex(jsonObject);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    @POST
    @Path("/addEdge")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public JSONObject addEdge(JSONObject jsonObject)
    {
        String outUID;
        String inUID;
        try {
            outUID = jsonObject.getJSONObject(SystemKeys.outUID.name()).getString(SystemKeys.V_ID.name());
            inUID = jsonObject.getJSONObject(SystemKeys.inUID.name()).getString(SystemKeys.V_ID.name());
        }
        catch (JSONException e) {
            throw new SystemException(e);
        }

        System.out.println("----------called");
        jsonObject.remove(SystemKeys.outUID.name());
        jsonObject.remove(SystemKeys.inUID.name());

        return graphService.addEdge(jsonObject, outUID, inUID);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    @GET
    @Path("/getGraph")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public JSONObject getGraph()
    {
        return graphService.getGraph();
    }

    @Override
    @POST
    // TODO change it to get
    @Path("/query")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public JSONObject doQuery(JSONObject jsonObject)
    {
        return graphService.doQuery(jsonObject);
    }

}
