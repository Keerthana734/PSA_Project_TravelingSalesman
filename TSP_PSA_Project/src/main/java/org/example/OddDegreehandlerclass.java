package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.List;
import java.util.Set;
import org.example.*;
import org.example.CircleData;

public class OddDegreehandlerclass implements EventHandler<ActionEvent> {
    private Graph graph;
    private List<CircleData> circleDataList;
    private Group root;
    public OddDegreehandlerclass (Graph graph, List<CircleData> circleDataList,Group root){
        this.graph = graph;
        this.circleDataList = circleDataList;
        this.root = root;
    }
    @Override
    public void handle(ActionEvent event){
        PrimsMST mst=new PrimsMST();
        Set<String> oddDegreeVertices =  OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph));
        for(CircleData cdata: circleDataList){
            for(String oddDegreeVertex : oddDegreeVertices){
                if(cdata.getName() == oddDegreeVertex){
                    for(Node node : root.getChildren()){
                        if (node instanceof Circle) {
                            Circle circle = (Circle) node;
                            if(circle.getAccessibleText()==cdata.getName()){
                                circle.setStroke(Color.YELLOW);
                                circle.setStrokeWidth(5);
                            }
                        }
                    }
                }
            }
        }
        for(Node node : root.getChildren()){
            if (node instanceof Circle) {
                Circle circle = (Circle) node;
                CircleData circledata = new CircleData(circle.getAccessibleText(),circle.getCenterX(),circle.getCenterY());
                circleDataList.add(circledata);
            }
        }
    }
}
