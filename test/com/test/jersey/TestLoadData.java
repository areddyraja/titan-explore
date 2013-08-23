
package com.test.jersey;

import java.net.URI;

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

public class TestLoadData
{
    @Test
    public void test() throws Exception
    {
        seed();
    }

    @SuppressWarnings("unused")
    private void seed() throws JSONException
    {
        // JSON

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "A");
        jsonObject.put("ip", 1);
        Builder postBuilder = getRequestBuilder("addVertex");
        JSONObject a = postBuilder.post(JSONObject.class, jsonObject);

        jsonObject.put("name", "B");
        jsonObject.put("ip", 2);
        postBuilder = getRequestBuilder("addVertex");
        JSONObject b = postBuilder.post(JSONObject.class, jsonObject);

        jsonObject.put("name", "C");
        jsonObject.put("ip", 3);
        postBuilder = getRequestBuilder("addVertex");
        JSONObject c = postBuilder.post(JSONObject.class, jsonObject);

        jsonObject.put("name", "D");
        jsonObject.put("ip", 4);
        postBuilder = getRequestBuilder("addVertex");
        JSONObject d = postBuilder.post(JSONObject.class, jsonObject);

        jsonObject.put("name", "E");
        jsonObject.put("ip", 5);
        postBuilder = getRequestBuilder("addVertex");
        JSONObject e = postBuilder.post(JSONObject.class, jsonObject);

        postBuilder = getRequestBuilder("addEdge");
        jsonObject = new JSONObject();
        jsonObject.put("latency", 1.6);
        jsonObject.put("throughput", 6.01);
        jsonObject.put("outUID", a);
        jsonObject.put("inUID", b);

        JSONObject ab = postBuilder.post(JSONObject.class, jsonObject);

        postBuilder = getRequestBuilder("addEdge");
        jsonObject = new JSONObject();
        jsonObject.put("latency", 1.2);
        jsonObject.put("throughput", 2.4);
        jsonObject.put("outUID", a);
        jsonObject.put("inUID", c);

        JSONObject ac = postBuilder.post(JSONObject.class, jsonObject);

        postBuilder = getRequestBuilder("addEdge");
        jsonObject = new JSONObject();
        jsonObject.put("latency", 1.8);
        jsonObject.put("throughput", 3.0);
        jsonObject.put("outUID", b);
        jsonObject.put("inUID", d);

        JSONObject bd = postBuilder.post(JSONObject.class, jsonObject);

        postBuilder = getRequestBuilder("addEdge");
        jsonObject = new JSONObject();
        jsonObject.put("latency", 1.1);
        jsonObject.put("throughput", 2.3);
        jsonObject.put("outUID", c);
        jsonObject.put("inUID", d);

        JSONObject cd = postBuilder.post(JSONObject.class, jsonObject);

        postBuilder = getRequestBuilder("addEdge");
        jsonObject = new JSONObject();
        jsonObject.put("latency", 1.1);
        jsonObject.put("throughput", 2.3);
        jsonObject.put("outUID", d);
        jsonObject.put("inUID", e);

        JSONObject de = postBuilder.post(JSONObject.class, jsonObject);
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
