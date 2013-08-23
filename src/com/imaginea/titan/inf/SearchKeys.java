/**
 * 
 */

package com.imaginea.titan.inf;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.imaginea.titan.impl.QueryImpl;

/**
 * @author maheshp
 *
 */
public enum SearchKeys
{
    KEY, OPERATION, ELEMENT, VALUES;

    /**
     * @param object
     * @return
     * @throws JSONException
     */
    public static Query parse(final JSONObject object) throws JSONException
    {
        final String key = object.getString(KEY.name().toLowerCase());
        final Operation op = Operation.parse(object.getString(OPERATION.name().toLowerCase()).toUpperCase());
        final ElementType type = ElementType.parse(object.optString(ELEMENT.name().toLowerCase()).toUpperCase());
        final JSONArray values = object.optJSONArray(VALUES.name().toLowerCase());

        return new QueryImpl(key, op, values, type);
    }
}
