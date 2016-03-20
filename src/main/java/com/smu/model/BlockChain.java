package com.smu.model;

import com.smu.StateStorage;
import org.jgrapht.DirectedGraph;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class BlockChain {
    private static Block endBlock;


    public static void parseBlocks(){
        //retrieve blocks
        List<Block> blocks = StateStorage.getInstance().getBlocks();

        //create linked list with root as genesis block, remove genesis block
        ListenableDirectedGraph<Block, DefaultEdge> g = new ListenableDirectedGraph<>(DefaultEdge.class);
        Block evilBlock = null;
        for(Block b : blocks) {
            //TODO: if in evil mode, check if the block is the evil block
            if(StateStorage.getInstance().isEvilMode()){
                if(b.containsEvilTransaction()){
                    evilBlock = b;
                    continue;
                }
                //if it is then drop it, i.e. remove it from the list of blocks after the for loop
            }

            g.addVertex(b);
        }
        if(evilBlock != null) {
            blocks.remove(evilBlock);
        }

        //i need to add the edges
        for(Block b: blocks) {
            //look at the previous hash
            String prevHash = b.getPreviousHash();

            //loop through blocks to find a block with current hash == previous hash
            for(Block cb : blocks){
                if(cb.getCurrentHash().equals(prevHash)){
                    //once you find the block, add a directed edge
                    g.addEdge(cb, b);
                }

            }
        }

        //find the root block
        Block startingVertex = null;
        for(Block b:blocks){
            if(b.getCurrentHash().equals("00000000000000000")){
                startingVertex = b;
                break;
            }
        }


        DepthFirstIterator<Block, DefaultEdge> iterator = new DepthFirstIterator<>(g, startingVertex);
        //create the listener to listen for connected components finishing
        BlockChainTraversalListener  tl = new BlockChainTraversalListener();
        iterator.addTraversalListener(tl);

        while(iterator.hasNext()){
            iterator.next();
        }

        //check if last block is clarence's block


        endBlock = tl.longestChain.get(tl.longestChain.size() - 1);

        StateStorage.getInstance().setVerifiedBlockChain(tl.longestChain);
    }

    public static Block getEndBlock(){
        return endBlock;
    }



}
