
package com.test.jersey;

import java.net.URI;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestTitanServiceExe
{

    @Test
    public void getGraph()
    {
        Builder postBuilder = getRequestBuilder("getGraph");
        JSONObject result = postBuilder.get(JSONObject.class);
        System.out.println(result);
    }

    @Test
    public void queryForTop() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "latency");
        jsonObject.put("operation", "top");
        Builder postBuilder = getRequestBuilder("query");
        JSONObject rest = postBuilder.post(JSONObject.class, jsonObject);
        System.out.println(rest);
    }

    @Test
    public void queryForTopTwo() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "latency");
        jsonObject.put("operation", "top");
        jsonObject.put("values", Arrays.asList(new String[] { "2" }));
        Builder postBuilder = getRequestBuilder("query");
        JSONObject rest = postBuilder.post(JSONObject.class, jsonObject);
        System.out.println("queryForTopTwo:" + rest);
    }

    @Test
    public void queryBottom() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "latency");
        jsonObject.put("operation", "bottom");
        Builder postBuilder = getRequestBuilder("query");
        JSONObject rest = postBuilder.post(JSONObject.class, jsonObject);
        System.out.println(rest);
    }

    @Test
    public void queryBottomThree() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "throughput");
        jsonObject.put("operation", "bottom");
        jsonObject.put("values", Arrays.asList(new String[] { "2" }));
        Builder postBuilder = getRequestBuilder("query");
        JSONObject rest = postBuilder.post(JSONObject.class, jsonObject);
        System.out.println("queryBottomThree" + rest);
    }

    private static URI getBaseURI()
    {
        return UriBuilder.fromUri("http://localhost:8181/Titan_Rest").build();
    }

    private static Builder getRequestBuilder(String path)
    {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(getBaseURI());
        return service.path("rest").path("titanService").path(path).type(MediaType.APPLICATION_JSON);
    }

}
