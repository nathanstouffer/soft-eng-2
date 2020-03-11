/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primepath;

// import libraries
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

// import exceptions
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Nathan
 */
public class Graph {
    
    // matrix to store the graph
    // "rows" are the source node
    // "columns" are the dest node
    private int[][] mtx;
    
    private int NODES;  // variable describing the number of nodes in the graph
    
    ArrayList<Path> paths;
    
    Graph(String file_name) {
        this.paths = new ArrayList<Path>();
        
        try { this.readFile(file_name); }
        catch (IOException e) { System.err.println("File not found"); }
    }
    
    public Path[] simplePaths() {        
        for (int i = 0; i < NODES; i++) {
            Path p = new Path(i);
            this.dfsSP(p);
        }
        
        Path[] ret = new Path[this.paths.size()];
        for (int r = 0; r < ret.length; r++) { ret[r] = this.paths.get(r); }
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
