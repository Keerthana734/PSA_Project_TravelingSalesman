
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import org.example.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.ArrayList;
import org.example.Edge;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class HelloFX extends Application {

    private Map<String, CircleData> circleMap;

    public HelloFX(){
        circleMap = new HashMap<>();
    }

    public void addCircle(String name, double x, double y) {
        CircleData circleData = new CircleData(name, x, y);
        circleMap.put(name, circleData);
    }

    public CircleData getCircleData(String name) {
        return circleMap.get(name);
    }
    @Override
    public void start(Stage stage) {
        Random rand = new Random();

        Graph graph = GraphFromCSV.constructGraphFromCSV("crimeSample.csv");
        int count = graph.getNumVertices();
        //Drawing a Circle
        Group root = new Group();

        StackPane p = new StackPane();
        //String numstr;
        List<String> verticesList = graph.getVertices();
        for(String eachvertex: verticesList){
            Circle circle = new Circle(10,Color.RED);
            circle.setCenterX(rand.nextInt(110,1400));
            circle.setCenterY(rand.nextInt(50,700));
            circle.setAccessibleText(eachvertex);

            root.getChildren().add(circle);

        }


        List<CircleData> circleDataList = new ArrayList<CircleData>();
        for(Node node : root.getChildren()){
            if (node instanceof Circle) {
                Circle circle = (Circle) node;
                CircleData circledata = new CircleData(circle.getAccessibleText(),circle.getCenterX(),circle.getCenterY());
                circleDataList.add(circledata);
            }
        }

        Button btMST = new Button("MST");
        Button btOddDegree = new Button("Odd Degree Vertices");
        btOddDegree.setLayoutX(50);
        btOddDegree.setLayoutY(0);




        MSThandlerclass mstHandler = new MSThandlerclass(graph,circleDataList,root);
        btMST.setOnAction(mstHandler);
        root.getChildren().add(btMST);

        OddDegreehandlerclass OddDegreeHandler = new OddDegreehandlerclass(graph,circleDataList,root);
        btOddDegree.setOnAction(OddDegreeHandler);
        root.getChildren().add(btOddDegree);


        //Creating a scene object
        Scene scene = new Scene(root, 2000, 800);
        //Setting title to the Stage
        stage.setTitle("Christofides Algorithm");
        //Adding scene to the stage
        stage.setScene(scene);
        //Displaying the contents of the stage
        stage.show();
    }

    class MSThandlerclass implements EventHandler<ActionEvent>{

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

    class OddDegreehandlerclass implements EventHandler<ActionEvent> {
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



    public static void main(String args[]){
        launch(args);
    }
}


