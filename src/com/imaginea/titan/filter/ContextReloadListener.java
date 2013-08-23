/*
 * ContextReloadListener.java
 */

/*
 * Copyright © 2007-2008 , Agentrics LLC – All Rights Reserved.
 */

package com.imaginea.titan.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.imaginea.titan.conf.BootStrapper;

/**
 * @author kaushikr
 * @version $Id$ 
 */
public class ContextReloadListener implements ServletContextListener
{

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        BootStrapper.instance().shutdown();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        BootStrapper.instance().start();
    }

}
