/**
 * 
 */

package com.test.jersey.titan;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author maheshp
 *
 */
@XmlRootElement
public class VerticesInfo
{
    private String name;
    private String value;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}
