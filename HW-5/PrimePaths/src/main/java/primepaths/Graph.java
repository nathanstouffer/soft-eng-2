/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primepaths;

// import libraries
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

// import exceptions
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to represent a graph. This class uses an adjacency matrix to represent
 * the graph. Operations include the ability to compute all simple and prime paths
 * found in the graph.
 * 
 * @author Nathan
 */
public class Graph {
    
    // matrix to store the graph
    // "rows" are the source node
    // "columns" are the dest node
    private int[][] mtx;
    
    private int NODES;  // variable describing the number of nodes in the graph
    
    ArrayList<Path> paths;  // variable referenced when computing simple paths
     
    /* constructor to read in the graph in file_name */
    Graph(String file_name) {
        this.paths = new ArrayList<Path>();
        
        try { this.readFile(file_name); }
        catch (IOException e) { System.err.println("File not found"); }
    }
    
    /* method to compute the simple paths of this graph */
    public Path[] simplePaths() {   
        // clear paths variable
        this.paths = new ArrayList<Path>();
        // compute recursive path searching for each starting node
        for (int i = 0; i < NODES; i++) {
            Path p = new Path(i);
            this.dfsSP(p);
        }
        
        // copy paths to array
        Path[] ret = new Path[this.paths.size()];
        for (int r = 0; r < ret.length; r++) { ret[r] = this.paths.get(r); }
        // clear paths variable
        this.paths = new ArrayList<Path>();
        // sort array
        Arrays.sort(ret);
        return ret;
    }
    
    /* method to compute the prime paths of this graph */
    public Path[] primePaths() {
        // get simple paths
        Path[] simple_paths = this.simplePaths();
        // array list to store prime paths
        ArrayList<Path> primes = new ArrayList<Path>();
        // the largest path is certainly prime, so add it
        primes.add(simple_paths[simple_paths.length-1]);
        // check if other paths are prime
        for (int s = simple_paths.length - 2; s >= 0; s--) {
            // current path
            Path curr = simple_paths[s];
            // assume current path is prime
            boolean subpath = false;
            // iterate through all prime paths
            for (int p = 0; p < primes.size() && !subpath; p++) {
                Path tmp = primes.get(p);
                // check if curr is a subpath of any prime path
                if (tmp.tours(curr)) { subpath = true; }
            }
            // if curr is not a subpath, add it to primes
            if (!subpath) { primes.add(curr); }
        }
        
        // copy primes to an array
        Path[] ret = new Path[primes.size()];
        for (int r = 0; r < ret.length; r++) { ret[r] = primes.get(r); }
        return ret;
    }
    
    /* method to recursively add simple paths to the list of simple paths */
    private void dfsSP(Path curr) {
        this.paths.add(curr);
        int[] neighbors = this.neighbors(curr.getLast());
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] == 1) {
                // create clone
                Path next = curr.clone();
                // add new node
                next.addNode(i);
                // make recursive call if the path is simple
                if (next.isSimplePath()) { this.dfsSP(next); }
            }
        }
        
    }
    
    /* method to return the array representing the neighbors of a given node */
    private int[] neighbors(int n) {
        if (n < 0) { System.err.println("index is less than 0"); }
        if (n >= NODES) { System.err.println("index is greater than number of nodes"); }
        return this.mtx[n].clone();
    }
    
    public String toString() {
        String out = "";
        for (int s = 0; s < NODES; s++) {
            for (int d = 0; d < NODES; d++) {
                out += this.mtx[s][d] + " ";
            }
            out += "\n";
        }
        return out;
    }
    
    private void readFile(String file_name) throws IOException {
        // input file
        File fin = new File(file_name);
        // buffered reader
        BufferedReader br = new BufferedReader(new FileReader(fin));
        
        // read dummy line
        String line = br.readLine();
        line = br.readLine();
        String[] split_line = line.split(" ");
        
        // compute nodes and edges
        NODES = Integer.parseInt(split_line[0]);
        int edges = Integer.parseInt(split_line[1]);
        
        // initialize adjacency matrix
        this.mtx = new int[NODES][NODES];
        
        // populate matrix with 0
        for (int s = 0; s < NODES; s++) {
            for (int d = 0; d < NODES; d++) {
                this.mtx[s][d] = 0;
            }
        }
        
        for (int e = 0; e < edges; e++) {
            // read and split line
            line = br.readLine();
            split_line = line.split(" ");
            int src = Integer.parseInt(split_line[1]);
            int dst = Integer.parseInt(split_line[2]);
            this.mtx[src][dst] = 1;
        }
        
        br.close();
    }
    
}
