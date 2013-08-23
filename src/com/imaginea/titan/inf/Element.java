/**
 * 
 */

package com.imaginea.titan.inf;

import org.codehaus.jettison.json.JSONObject;

/**
 * @author kaushikr
 * 
 */
public interface Element extends com.tinkerpop.blueprints.Element
{
    String getUID();

    /**
     * @return
     */
    JSONObject getUidJSONObject();

    /**
     * @return
     */
    JSONObject json();
}
