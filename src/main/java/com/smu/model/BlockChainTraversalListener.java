package com.smu.model;

import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WaiTuck on 18/03/2016.
 */
public class BlockChainTraversalListener implements TraversalListener<Block, DefaultEdge> {
    public int greatestDepth = 0;
    private int currentDepth = 0;
    private boolean componentStarted = false;
    public List<Block> longestChain = new ArrayList<>();
    public List<Block> currentChain = new ArrayList<>();


    public int getGreatestDepth(){
        return greatestDepth;
    }

    @Override
    public void connectedComponentFinished(ConnectedComponentTraversalEvent connectedComponentTraversalEvent) {
    }

    @Override
    public void connectedComponentStarted(ConnectedComponentTraversalEvent connectedComponentTraversalEvent) {

    }

    @Override
    public void edgeTraversed(EdgeTraversalEvent<Block, DefaultEdge> edgeTraversalEvent) {

    }

    @Override
    public void vertexTraversed(VertexTraversalEvent<Block> vertexTraversalEvent) {
        Block b = vertexTraversalEvent.getVertex();
        currentDepth++;
        currentChain.add(b);
    }

    @Override
    public void vertexFinished(VertexTraversalEvent<Block> vertexTraversalEvent) {
        if(currentDepth > greatestDepth){
            greatestDepth = currentDepth;
            longestChain = new ArrayList(currentChain);
        }
        currentDepth--;
        Block b = vertexTraversalEvent.getVertex();
        currentChain.remove(b);

    }
}
