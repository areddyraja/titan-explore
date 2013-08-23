/*
 * TopExecutor.java
 */

/*
 * Copyright © 2007-2008 , Agentrics LLC – All Rights Reserved.
 */

package com.imaginea.titan.execs;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.codehaus.jettison.json.JSONException;

import com.google.common.collect.MinMaxPriorityQueue;
import com.imaginea.titan.exception.SystemException;
import com.imaginea.titan.impl.GraphsUtils;
import com.imaginea.titan.impl.ResultImpl;
import com.imaginea.titan.inf.Query;
import com.imaginea.titan.inf.Result;
import com.imaginea.titan.inf.SystemKeys;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

/**
 * @author mpujari
 * @version $Id$ 
 */
public class BottomExecutor extends AbstractExecutor
{

    /** 
     * {@inheritDoc}
     */
    @Override
    public Result execute(final Graph graph, final Query query)
    {
        final Collection<com.imaginea.titan.inf.Edge> edges = new LinkedHashSet<com.imaginea.titan.inf.Edge>();
        final Collection<com.imaginea.titan.inf.Vertex> vertexs = new LinkedHashSet<com.imaginea.titan.inf.Vertex>();

        if (null == query.getValues() || 0 == query.getValues().length()) {
            com.imaginea.titan.inf.Vertex vertex = null;
            com.imaginea.titan.inf.Edge edge = null;

            for (final Vertex _vrt : graph.getVertices()) {
                final com.imaginea.titan.inf.Vertex _vertex = GraphsUtils.wrap(_vrt, query.getKey());
                if (null == vertex || _vertex.compareTo(vertex) <= 0) {
                    vertex = _vertex;
                }

                final Iterable<Edge> targetEdges = _vertex.query().labels(query.getKey()).edges();

                for (final Edge _edg : targetEdges) {
                    final com.imaginea.titan.inf.Edge _edge = GraphsUtils.wrap(_edg, SystemKeys.ELEMENT_VALUE.name());
                    if (null == edge || _edge.compareTo(edge) <= 0) {
                        edge = _edge;
                    }
                }
            }

            edges.add(edge);
            vertexs.add(vertex);
            return new ResultImpl(edges, vertexs);
        }
        else {
            try {
                int top = query.getValues().getInt(0);
                final MinMaxPriorityQueue<com.imaginea.titan.inf.Vertex> maxVertices = MinMaxPriorityQueue
                    .maximumSize(top).create();
                final MinMaxPriorityQueue<com.imaginea.titan.inf.Edge> maxEdges = MinMaxPriorityQueue
                    .maximumSize(top).create();

                for (final Vertex _vertex : graph.getVertices()) {
                    maxVertices.add(GraphsUtils.wrap(_vertex, query.getKey(), -1));
                }

                for (final Edge _edge : graph.getEdges()) {
                    maxEdges.add(GraphsUtils.wrap(_edge, query.getKey(), -1));
                }
                return new ResultImpl(maxEdges, maxVertices);
            }
            catch (JSONException e) {
                throw new SystemException(e);
            }
        }
    }

}
