/**
 * 
 */

package com.imaginea.titan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.imaginea.titan.conf.BootStrapper;

/**
 * @author mpujari
 * @version $Id$ 
 */
public class TitanTransactionFilter implements Filter
{

    @Override
    public void destroy()
    {
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
        throws IOException,
        ServletException
    {
        final BootStrapper instance = BootStrapper.instance();

        instance.begin();
        boolean success = false;
        try {
            filterChain.doFilter(request, response);
            success = true;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if (success) {
                instance.commit();
            }
            else {
                instance.rollback();
            }
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

}
