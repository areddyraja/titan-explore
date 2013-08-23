/*
 * OperationExec.java
 */

/*
 * Copyright © 2007-2008 , Agentrics LLC – All Rights Reserved.
 */

package com.imaginea.titan.inf;

import com.tinkerpop.blueprints.Graph;

/**
 * @author kaushikr
 * @version $Id$ 
 */
public interface OperationExec
{
    /**
     * @param graph
     * @param column
     * @param onElement
     * @param values
     * @return
     */
    Result execute(Graph graph, Query query);
}
