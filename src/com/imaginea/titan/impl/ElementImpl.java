/**
 * 
 */

package com.imaginea.titan.impl;

import java.util.Set;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.imaginea.titan.exception.SystemException;
import com.imaginea.titan.inf.Element;
import com.imaginea.titan.inf.ElementType;
import com.imaginea.titan.inf.SystemKeys;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;

/**
 * @author kaushikr
 *
 */
abstract class ElementImpl implements Element
{
    protected com.tinkerpop.blueprints.Element element;
    protected String comparisonKey;
    protected int asc = 1;

    /**
     * @see com.tinkerpop.blueprints.Element#getId()
     */
    @Override
    public Object getId()
    {
        return element.getId();
    }

    /**
     * @see com.tinkerpop.blueprints.Element#getProperty(java.lang.String)
     */
    @Override
    public <T> T getProperty(String arg0)
    {
        try {
            return element.getProperty(arg0);
        }
        catch (Exception e) {
            // Do nothing
        }

        return null;
    }

    /**
     * @see com.tinkerpop.blueprints.Element#getPropertyKeys()
     */
    @Override
    public Set<String> getPropertyKeys()
    {
        return element.getPropertyKeys();
    }

    /**
     * @see com.tinkerpop.blueprints.Element#remove()
     */
    @Override
    public void remove()
    {
        try {
            element.remove();
        }
        catch (Exception e) {
        }
    }

    /**
     * @see com.tinkerpop.blueprints.Element#removeProperty(java.lang.String)
     */
    @Override
    public <T> T removeProperty(String arg0)
    {
        try {
            return element.removeProperty(arg0);
        }
        catch (Exception e) {
        }
        return null;
    }

    /**
     * @see com.tinkerpop.blueprints.Element#setProperty(java.lang.String, java.lang.Object)
     */
    @Override
    public void setProperty(String arg0, Object arg1)
    {
        element.setProperty(arg0, arg1);
    }

    /**
     * @see com.imaginea.titan.inf.Element#getUidJSONObject()
     */
    public JSONObject getUidJSONObject(final SystemKeys key)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(key.name(), this.element.getProperty(key.name()));
        }
        catch (JSONException e) {
            throw new SystemException(e);
        }
        return jsonObject;
    }

    public String getUID(final SystemKeys key)
    {
        return this.element.getProperty(key.name());
    }

    /**
     * @param o
     * @return
     */
    protected int compareIds(Element o)
    {
        return asc * this.getId().toString().compareTo(o.getId().toString());
    }

    /**
     * @param that
     * @return
     */
    protected int compareValues(final Element that)
    {
        Object thisValue = this.getProperty(comparisonKey);
        Object thatValue = that.getProperty(comparisonKey);

        return compare(that, thisValue, thatValue);
    }

    /**
     * @param that
     * @param thisValue
     * @param thatValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public int compare(Element that, Object thisValue, Object thatValue)
    {
        if (null != thisValue && null != thatValue && thisValue.getClass().equals(thatValue.getClass())) {
            if (Comparable.class.isAssignableFrom(thisValue.getClass())) {
                return asc * ((Comparable<Object>) thisValue).compareTo(thatValue);
            }
            else {
                return compareIds(that);
            }
        }
        else {
            return compareIds(that);
        }
    }

    /**
     * @param that
     * @return
     */
    public boolean areEdges(Element that)
    {
        return Edge.class.isAssignableFrom(this.getClass()) && Edge.class.isAssignableFrom(that.getClass());
    }

    /** 
     * {@inheritDoc}
     */
    public int compareTo(Element o)
    {
        if (null == comparisonKey || "".equals(comparisonKey)) {
            return compareIds(o);
        }
        else {
            return compareValues(o);
        }
    }

    public JSONObject json(ElementType elementType)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("id", getUID());
            if (ElementType.EDGE == elementType) {
                json.put("label", ((Edge) this).getLabel());
                json.put(SystemKeys.inUID.name(), GraphsUtils.wrap(((Edge) this).getVertex(Direction.IN)).getUID());
                json.put(SystemKeys.outUID.name(), GraphsUtils.wrap(((Edge) this).getVertex(Direction.OUT)).getUID());
            }
            for (String key : this.getPropertyKeys()) {
                json.put(key, this.getProperty(key));
            }
        }
        catch (JSONException e) {
            new SystemException(e);
        }
        return json;
    }
}
