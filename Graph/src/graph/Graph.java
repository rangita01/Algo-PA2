/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author Rangita
 */
public class Graph {

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) throws IOException
    {
 
        int V = 9;  
        int E = 18;  
        Graph graph = new Graph(V, E);
        
       File file = new File("C:\\Users\\Rangita\\Documents\\NetBeansProjects\\Graph\\src\\graph\\kruskal.txt");
       BufferedReader reader = new BufferedReader(new FileReader(file));
       String text = null;
       int count=0;
       while ((text = reader.readLine()) != null) {
       String[] strings = text.split(" ");
       //System.out.println(strings.length);
       for(int i=0;i<1;i++)
       {
           // System.out.println(Integer.parseInt(strings[0]));
            //System.out.println(Integer.parseInt(strings[1]));
            //System.out.println(Integer.parseInt(strings[2]));
        graph.edge[count].src = Integer.parseInt(strings[0]);
        graph.edge[count].dest = Integer.parseInt(strings[1]);
        graph.edge[count].weight = Integer.parseInt(strings[2]); 
        /*System.out.println(graph.edge[count].src);
        System.out.println(graph.edge[count].dest);
        System.out.println(graph.edge[count].weight);*/
        count++;
        //System.out.println("i is"+i+"count is"+count);
           
        }
        
    }

graph.KruskalMST();

        if (reader != null) {
            reader.close();
        }
    



    }
    
     class Edge implements Comparable<Edge>
    {
        int src, dest, weight;
 
        
        public int compTo(Edge compE)
        {
            return this.weight-compE.weight;
        }

        @Override
        public int compareTo(Edge o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
 
 
    class subset
    {
        int parent, rank;
    };
 
    int V, E;    
    Edge edge[]; 
 
    
    Graph(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
 
    
    int find(subset subSets[], int i)
    {
       
        if (subSets[i].parent != i)
            subSets[i].parent = find(subSets, subSets[i].parent);
 
        return subSets[i].parent;
    }
 
    void Union(subset subSets[], int x, int y)
    {
        int xrt = find(subSets, x);
        int yrt = find(subSets, y);
 
        if (subSets[xrt].rank < subSets[yrt].rank)
            subSets[xrt].parent = yrt;
        else if (subSets[xrt].rank > subSets[yrt].rank)
            subSets[yrt].parent = xrt;
 
        else
        {
            subSets[yrt].parent = xrt;
            subSets[xrt].rank++;
        }
    }
 
    
    void KruskalMST()
    {
        Edge result[] = new Edge[V];  
        int e = 0;  
        int i = 0;  
        for (i=0; i<V; ++i)
            result[i] = new Edge();
 
       
        Arrays.sort(edge);
 
        
        subset subSets[] = new subset[V];
        for(i=0; i<V; ++i)
            subSets[i]=new subset();
 
      
        for (int v = 0; v < V; ++v)
        {
            subSets[v].parent = v;
            subSets[v].rank = 0;
        }
 
        i = 0;  
        while (e < V - 1)
        {
            Edge nE = new Edge();
            nE = edge[i++];
 
            int x = find(subSets, nE.src);
            int y = find(subSets, nE.dest);
 
            
            if (x != y)
            {
                result[e++] = nE;
                Union(subSets, x, y);
            }
            
        }
 
       
        /*System.out.println("Following are the edges in " + 
                                     "the constructed MST");
        for (i = 0; i < e; ++i)
        {System.out.println(result[i].src+" -- " + 
                   result[i].dest+" == " + result[i].weight);
                   System.out.println(result[i].src+1);}
        /*Minimum Spanning Tree: Total weights on MST edges = 74
Node Set = {A, C, E}, Edge Set = { A-C, C-E}*/
        for (i = 0; i < e; ++i)
        {/*System.out.println(result[i].src+" -- " + 
                   result[i].dest+" == " + result[i].weight);
                       System.out.println(result[i].src+1);*/
            if(result[i].dest-result[i].src>1)
            {   
                System.out.println("Minimum Spanning Tree: Total weights on MST edges =" +result[i].weight);
                System.out.println("Node Set = ");
                System.out.print(result[i].src+",");
                for(int j=1;j<result[i].dest-result[i].src;j++)
                {   
                    System.out.print(result[i].src+j+",");
                    //System.out.println(j);
                }
                System.out.println(result[i].dest);
                System.out.println("Edge set: ");
                System.out.print(result[i].src+"-");
                for(int j=1;j<result[i].dest-result[i].src;j++)
                {   
                    System.out.print(result[i].src+j+"-");
                }
                System.out.println(result[i].dest);
                
            }
            else 
            {
                System.out.println("Minimum Spanning Tree: Total weights on MST edges =" +result[i].weight);
                System.out.println("Node Set = "+result[i].src+","+result[i].dest);
                System.out.println("Edge Set = "+result[i].src+"-"+result[i].dest);
                
            }
            System.out.println("    ");
        }
    }
 
      
}
