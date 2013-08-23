/**
 * 
 */

package com.imaginea.titan.test;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

/**
 * @author kaushikr
 * 
 */
public class Zeus
{
    // A{ machineip: "2.2.2.2", nodeid: "A", op: 23, ip: 34, pid: 9991 }
    // B{ machineip: "3.3.3.3", nodeid: "B", op: 24, ip: 35, pid: 9992 }
    // C{ machineip: "4.4.4.4", nodeid: "C", op: 25, ip: 36, pid: 9993 }
    // D{ machineip: "5.5.5.5", nodeid: "D", op: 26, ip: 37, pid: 9994 }

    // AB{ latency: 2.44 , id: AB_BA, traffic: 3.4 }
    // AC{ latency: 1.44 , id: AC_CA, traffic: 4.4 }
    // AD{ latency: 0.44 , id: AD_DA, traffic: 5.4 }
    // BD{ latency: 2.44 , id: BD_DB, traffic: 3.3 }
    // BC{ latency: 0.64 , id: BC_CB, traffic: 3.1 }
    // CD{ latency: 0.04 , id: CD_DC, traffic: 3.7 }

    /**
     * @param args
     */
    public static void createGraphData(String[] args)
    {
        Configuration configuration = new BaseConfiguration();
        configuration.setProperty("storage.directory", "F:/downloads/titan-all-0.3.2/data-folder1");
        configuration.setProperty("storage.backend", "berkeleyje");

        // TODO Auto-generated method stub
        final TitanGraph graph = TitanFactory.open(configuration);
        graph.createKeyIndex("node", Vertex.class);
        graph.createKeyIndex("op", Vertex.class);
        graph.createKeyIndex("pid", Vertex.class);

        // A{ machineip: "2.2.2.2", nodeid: "A", op: 23, ip: 34, pid: 9991 }
        final Vertex A = graph.addVertex(null);
        A.setProperty("machineip", "2.2.2.2");
        A.setProperty("nodeid", "A");
        A.setProperty("node", true);
        A.setProperty("op", 23);
        A.setProperty("ip", 34);
        A.setProperty("pid", 9991);

        System.out.println(A.getId());
        System.out.println(A.getProperty("id"));
        System.out.println(A.getProperty("ID"));
        // System.out.println(A.());

        // B{ machineip: "3.3.3.3", nodeid: "B", op: 24, ip: 35, pid: 9992 }
        final Vertex B = graph.addVertex(null);
        B.setProperty("machineip", "3.3.3.3");
        B.setProperty("nodeid", "B");
        B.setProperty("op", 24);
        B.setProperty("node", true);
        B.setProperty("ip", 35);
        B.setProperty("pid", 9992);

        // C{ machineip: "4.4.4.4", nodeid: "C", op: 25, ip: 36, pid: 9993 }
        final Vertex C = graph.addVertex(null);
        C.setProperty("machineip", "4.4.4.4");
        C.setProperty("nodeid", "C");
        C.setProperty("op", 25);
        C.setProperty("ip", 36);
        C.setProperty("node", true);
        C.setProperty("pid", 9993);

        // D{ machineip: "5.5.5.5", nodeid: "D", op: 26, ip: 37, pid: 9994 }
        final Vertex D = graph.addVertex(null);
        D.setProperty("machineip", "5.5.5.5");
        D.setProperty("nodeid", "D");
        D.setProperty("op", 26);
        D.setProperty("node", true);
        D.setProperty("ip", 37);
        D.setProperty("pid", 9994);

        // todo whether to add each property as an edge?
        // AB{ latency: 2.44 , id: AB_BA, traffic: 3.4 }
        // BD{ latency: 2.44 , id: BD_DB, traffic: 3.3 }
        // CD{ latency: 0.04 , id: CD_DC, traffic: 3.7 }
        // AC{ latency: 1.44 , id: AC_CA, traffic: 4.4 }
        Edge AB = graph.addEdge(null, A, B, "latency");
        // graph.createKeyIndex("value", Edge.class);
        // graph.createKeyIndex("edge", Edge.class);

        // Edge BA = graph.addEdge(null, B, A, "latency");
        AB.setProperty("value", 2.44);
        AB.setProperty("edge", true);
        // BA.setProperty("value", 2.44);

        Edge BD = graph.addEdge(null, B, D, "latency");
        // Edge DB = graph.addEdge(null, D, B, "latency");
        BD.setProperty("value", 2.44);
        BD.setProperty("edge", true);
        // DB.setProperty("value", 2.44);

        Edge CD = graph.addEdge(null, C, D, "latency");
        // Edge DC = graph.addEdge(null, D, C, "latency");
        CD.setProperty("value", 0.04);
        CD.setProperty("edge", true);
        // DC.setProperty("value", 0.04);

        Edge AC = graph.addEdge(null, A, C, "latency");
        // Edge CA = graph.addEdge(null, C, A, "latency");
        AC.setProperty("value", 1.44);
        AC.setProperty("edge", true);
        // CA.setProperty("value", 1.44);

        graph.commit();
    }

    public static void createGraphData2(String[] args)
    {
        Configuration configuration = new BaseConfiguration();
        configuration.setProperty("storage.directory", "G:/titan-all-0.3.2/data");
        configuration.setProperty("storage.backend", "berkeleyje");

        // TODO Auto-generated method stub
        final TitanGraph graph = TitanFactory.open(configuration);
        // A{ machineip: "2.2.2.2", nodeid: "A", op: 23, ip: 34, pid: 9991 }
        final Vertex A = graph.addVertex(null);
        A.setProperty("machineip", "2.2.2.2");
        A.setProperty("nodeid", "A");
        A.setProperty("node", true);
        A.setProperty("op", 23);
        A.setProperty("ip", 34);
        A.setProperty("pid", 9991);

        // B{ machineip: "3.3.3.3", nodeid: "B", op: 24, ip: 35, pid: 9992 }
        final Vertex B = graph.addVertex(null);
        B.setProperty("machineip", "3.3.3.3");
        B.setProperty("nodeid", "B");
        B.setProperty("op", 24);
        B.setProperty("node", true);
        B.setProperty("ip", 35);
        B.setProperty("pid", 9992);
    }

    public static void addEdges2(String[] args)
    {
        Configuration configuration = new BaseConfiguration();
        configuration.setProperty("storage.directory", "G:/titan-all-0.3.2/data");
        configuration.setProperty("storage.backend", "berkeleyje");

        // TODO Auto-generated method stub
        final TitanGraph graph = TitanFactory.open(configuration);

        // graph.query().has("", arg1)
    }

    public static void main(String[] a)
    {
        // JSOn
        // main22(a);
        createGraphData(a);
        //show(a);
    }

    public static void show(String[] args)
    {
        Configuration configuration = new BaseConfiguration();
        configuration.setProperty("storage.directory", "F:/downloads/titan-all-0.3.2/root");
        configuration.setProperty("storage.backend", "berkeleyje");
        // TODO Auto-generated method stub
        final TitanGraph graph = TitanFactory.open(configuration);
        // final Iterable<Vertex> vertices = graph.getVertices();

        // 1.How to query on vertices.
        final Iterable<Vertex> vertices = graph.getVertices();
        for (Vertex v : vertices) {
            System.out.println(v.getProperty("nodeid"));
            System.out.println(v.getProperty("ip"));
            System.out.println(v.getProperty("pid"));
        }

        // 2.How to query edges.
        final Iterable<Edge> edges = graph.getEdges();
        for (Edge e : edges) {
            System.out.println(e.getProperty("value"));
        }

        // 2.How to query edges.
        final Iterable<Edge> latency = graph.getEdges();
        for (Edge e : latency) {
            System.out.println(e.getProperty("value"));
        }

    }
}
