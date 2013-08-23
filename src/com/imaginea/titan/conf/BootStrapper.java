/**
 * 
 */

package com.imaginea.titan.conf;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.imaginea.titan.exception.SystemException;
import com.imaginea.titan.inf.SystemKeys;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanTransaction;
import com.tinkerpop.blueprints.Graph;

/**
 * @author kaushikr
 *
 */
public final class BootStrapper
{
    private static final String ROOT_GRAPH = SystemKeys.ROOT.name();
    private static final BootStrapper INSTANCE = new BootStrapper();
    private String rootDirectory;
    private String storageBackEnd;
    private String searchBackEnd;
    private String indexStore;
    private final Map<String, TitanGraph> graphs = new LinkedHashMap<String, TitanGraph>();
    private final Map<String, ThreadLocal<TitanTransaction>> transactions = new LinkedHashMap<String, ThreadLocal<TitanTransaction>>();

    /**
     * 
     */
    private BootStrapper()
    {
    }

    public void start()
    {
        final Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties"));
        }
        catch (IOException e) {
            throw new SystemException(e);
        }

        rootDirectory = properties.getProperty(ConfKeys.STORAGE_DIRECTORY.getKey());

        if (null == rootDirectory) {
            throw new SystemException("Need root directory for the graph storage");
        }

        storageBackEnd = properties.getProperty(ConfKeys.STORAGE_BACKEND.getKey(), "berkeleyje");
        searchBackEnd = properties.getProperty(ConfKeys.SEARCH_BACKEND.getKey(), "lucene");
        indexStore = properties.getProperty(ConfKeys.INDEX_STORAGE.getKey());

        Configuration configuration = new BaseConfiguration();
        configuration.setProperty(ConfKeys.STORAGE_DIRECTORY.getKey(), rootDirectory);
        configuration.setProperty(ConfKeys.STORAGE_BACKEND.getKey(), storageBackEnd);
        configuration.setProperty(ConfKeys.SEARCH_BACKEND.getKey(), searchBackEnd);
        configuration.setProperty(ConfKeys.INDEX_STORAGE.getKey(), indexStore);

        final TitanGraph graph = TitanFactory.open(configuration);

        /* final Set<String> vertexIndexedKeys = graph.getIndexedKeys(Vertex.class);
        final Set<String> edgeIndexedKeys = graph.getIndexedKeys(Edge.class);

        if (!vertexIndexedKeys.contains(SystemKeys.V_ID.name())) {
            graph.createKeyIndex(SystemKeys.V_ID.name(), Vertex.class);
        }

        if (!edgeIndexedKeys.contains(SystemKeys.E_ID.name())) {
            graph.createKeyIndex(SystemKeys.E_ID.name(), Edge.class);
        } */

        graphs.put(ROOT_GRAPH, graph);
    }

    /**
     * @return
     */
    public static final BootStrapper instance()
    {
        return INSTANCE;
    }

    /**
     * @return
     */
    public String getRootDirectory()
    {
        return this.rootDirectory;
    }

    /**
     * @return
     */
    public String getStorageBackEnd()
    {
        return this.storageBackEnd;
    }

    /**
     * @return
     */
    public Map<String, TitanGraph> getGraphs()
    {
        return new LinkedHashMap<String, TitanGraph>(this.graphs);
    }

    /**
     * @return
     */
    public TitanTransaction getTransaction()
    {
        return this.transactions.get(ROOT_GRAPH).get();
    }

    /**
     * 
     */
    public void commit()
    {
        this.transactions.get(ROOT_GRAPH).get().commit();
        this.transactions.get(ROOT_GRAPH).remove();
    }

    /**
     * 
     */
    public void rollback()
    {
        this.transactions.get(ROOT_GRAPH).get().rollback();
        this.transactions.get(ROOT_GRAPH).remove();
    }

    /**
     * @param namespace
     */
    public TitanTransaction begin()
    {
        final TitanTransaction newTransaction = this.graphs.get(ROOT_GRAPH).newTransaction();
        final ThreadLocal<TitanTransaction> threadLocal = this.transactions.get(ROOT_GRAPH);

        if (null == threadLocal) {
            this.transactions.put(ROOT_GRAPH, new ThreadLocal<TitanTransaction>());
        }

        this.transactions.get(ROOT_GRAPH).set(newTransaction);

        return newTransaction;
    }

    /**
     * 
     */
    public void shutdown()
    {
        for (final ThreadLocal<TitanTransaction> tx : transactions.values()) {
            tx.remove();
        }

        for (final Graph g : graphs.values()) {
            g.shutdown();
        }

        this.transactions.clear();
        this.graphs.clear();
    }
}
