/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primepaths;

/**
 *
 * @author Nathan
 */
public class Client {
    
    public static void main(String[] args) {
        // check if there are command line arguments
        if (args.length > 1) {
            for (int a = 1; a < args.length; a++) {
                computePrimePaths(args[a]);
            }
        }
        else {
            computePrimePaths("graph1.txt");       
            //computePrimePaths("graph2.txt");
        }
    }
    
    /* method to compute the prime paths for the graph in file_name */ 
    public static void computePrimePaths(String file_name) {
        // create graph
        Graph graph = new Graph(file_name);
        
        // output info to user
        System.out.println("\n\n----- " + file_name + " -----\n");
        
        // output the graph
        System.out.println("------ Graph ------");
        System.out.println(graph);
        
        // compute simple paths
        Path[] simple_paths = graph.simplePaths();
        // output the simple paths
        System.out.println("---- Simple Paths ----");
        for (int s = 0; s < simple_paths.length; s++) {
            System.out.println("Path " + (s+1) + ": " + simple_paths[s].toString());
        }
        
        Path[] primes = graph.primePaths();
        
        System.out.println("\n---- Prime Paths ----");
        for (int p = 0; p < primes.length; p++) {
            System.out.println("Path " + (p+1) + ": " + primes[p].toString());
        }
    }
    
    /* unit testing method */
    public static void testGraph() {
        Graph g = new Graph("test-graph.txt");
        System.out.println(g);
        
        Path[] sp = g.simplePaths();
        for (int s = 0; s < sp.length; s++) {
            System.out.println("Path " + (s+1) + ": " + sp[s].toString());
        }
    }
    
    /* unit testing method */
    public static void testPath() {
        Path p = new Path(1);
        Path q = new Path(1);
        q.addNode(0);
        q.addNode(1);
        
        for (int i = 2; i < 5; i++) { p.addNode(i); q.addNode(i); }
        
        System.out.println("p: " + p.toString());
        System.out.println("q: " + q.toString());
        
        boolean p_visits_4 = p.visits(4);
        if (p_visits_4) { System.out.println("p visits 4"); }
        else { System.out.println("p does not visit 4"); }
        
        boolean p_sub_q = q.tours(p);
        if (p_sub_q) { System.out.println("q tours p"); }
        else { System.out.println("q does not tour p"); }
    }
    
}
