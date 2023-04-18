package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;
import org.example.*;

public class MSThandlerclass implements EventHandler<ActionEvent> {

    private Graph graph;
    private List<CircleData> circleDataList;
    private Group root;
    public MSThandlerclass(Graph graph,List<CircleData> circleDataList,Group root){
        this.graph = graph;
        this.circleDataList = circleDataList;
        this.root = root;

    }
    @Override
    public void handle(ActionEvent event){

        ArrayList<Node> linesToRemove = new ArrayList<>();

        for (Node node : root.getChildren()) {
            if (node instanceof Line) {
                linesToRemove.add(node);
            }
        }
        if(linesToRemove!=null){
            root.getChildren().removeAll(linesToRemove);
        }


        PrimsMST mst=new PrimsMST();
        List<Edge> mstEdgeList = mst.primMST(graph);
        double startX = 0,startY=0 ,endX=0,endY=0;
        for(Edge eachedge : mstEdgeList){
            for(CircleData cdata: circleDataList){
                if(cdata.getName() == eachedge.getSource()){
                    startX = cdata.getX();
                    startY = cdata.getY();
                }
                if(cdata.getName() == eachedge.getDestination()){
                    endX = cdata.getX();
                    endY = cdata.getY();
                }

            }
            Line line = new Line(startX, startY, endX, endY);
            root.getChildren().add(line);
        }


    }
}
