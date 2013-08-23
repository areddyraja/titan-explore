/**
 * 
 */

package com.imaginea.titan.inf;

import org.codehaus.jettison.json.JSONArray;

import com.tinkerpop.blueprints.Graph;

/**
 * @author kaushikr
 *
 */
public interface Query
{
    /**
     * @param graph
     * @return
     */
    Result execute(final Graph graph);

    /**
     * @return
     */
    public String getKey();

    /**
     * @return
     */
    public Operation getOperation();

    /**
     * @return
     */
    public JSONArray getValues();

    /**
     * @return
     */
    public ElementType getType();
}
