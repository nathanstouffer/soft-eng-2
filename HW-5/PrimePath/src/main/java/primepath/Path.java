/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primepath;

import java.util.ArrayList;

/**
 *
 * @author Nathan
 */
public class Path implements Comparable<Path> {
    
    private int ARRLENGTH = 10;    // length of the array
    private int pos;
    private int[] nodes;    // array to store the nodes in the path
    
    Path(int first) {
        // initial position is 0
        this.pos = 0;
        // initialize nodes array
        this.nodes = new int[ARRLENGTH];
        // init all nodes to -1
        for (int i = 0; i < ARRLENGTH; i++) { this.nodes[i] = -1; }
        // init first node
        this.nodes[0] = first;
        this.pos++;
    }
    
    /* method to add a node to the end of the path */
    public void addNode(int n) {
        if (2 * this.pos > ARRLENGTH) { this.doubleSize(); }
        // insert node
        this.nodes[this.pos] = n;
        // increment pos
        this.pos++;
    }
    
    /* method to return a boolean if the path visits the query node */
    public boolean visits(int n) {
        boolean visits = false;
        for (int i = 0; i < this.pos && !visits; i++) {
            if (this.nodes[i] == n) { visits = true; }
        }
        return visits;
    }
    
    /* method to return a boolean if the path tours the query path */
    public boolean tours(Path p) {
        int max_start = this.pos - p.getLength();
        if (max_start < 0) { return false; }
        else {
            for (int i = 0; i <= max_start; i++) {
                // test if subpath
                boolean subpath = true;
                for (int j = 0; j < p.getLength(); j++) {
                    if (this.get(j+i) != p.get(j)) { subpath = false; }                    
                }
                if (subpath) { return subpath; }
            }
        }
        // if we reach here, we can return false
        return false;
    }
    
    /* method to return if the path is simple */
    public boolean isSimplePath() {
        ArrayList<Integer> seen = new ArrayList<Integer>();
        seen.add(this.get(0));
        
        // check if there is a repetition
        for (int s = 1; s < this.pos - 1; s++) {
            int node = this.get(s);
            if (seen.contains(node)) { return false; }
            else { seen.add(node); }
        }
        
        // check last item in the path
        if (seen.contains(this.getLast())) {
            // last node has been seen
            if (this.get(0) != this.getLast()) {
                // last node is not the first node
                return false;
            }
        }
        
        return true;
    }
    
    public String toString() {
        String out = "[ " + Integer.toString(this.nodes[0]);
        for (int t = 1; t < this.pos; t++) {
            out += ", " + Integer.toString(this.nodes[t]);
        }
        out += " ]";
        return out;
    }
    
    /* method to double the size of the array containing the path */
    private void doubleSize() {
        int[] tmp = new int[2*ARRLENGTH];
        for (int t = 0; t < this.pos; t++) { tmp[t] = this.nodes[t]; }
        this.nodes = tmp;
        ARRLENGTH = 2 * ARRLENGTH;
    }
    
    @Override
    protected Path clone() {
        // create new object
        Path p = new Path(this.get(0)); 
        // populate clone
        for (int i = 1; i < this.pos; i++) { p.addNode(this.get(i)); }
        // return clone
        return p;
    }
    
    @Override
    public int compareTo(Path p) {
        if (this.getLength() > p.getLength()) { return 1; }
        else if (this.getLength() == p.getLength()) { return 0; }
        else { return -1; }
    }
    
    public int getLength() { return this.pos; }
    public int getLast() { return this.nodes[this.pos-1]; }
    public int get(int i) {
        if (i < 0) { System.err.println("index is not positive"); }
        else if (i >= this.pos) { System.err.println("index is too large"); }
        return this.nodes[i];
    }
    
}
