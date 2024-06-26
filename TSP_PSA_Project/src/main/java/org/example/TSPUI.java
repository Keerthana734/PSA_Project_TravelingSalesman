package org.example;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.*;

import static org.example.SimulatedAnnealing.simulatedAnnealing;
import static org.example.ThreeOpt.threeOpt;
import static org.example.TwoOpt.twoOpt;


public class TSPUI extends Application {

    private Map<String, CircleData> circleMap;

    public TSPUI(){
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

        Graph graph = GraphFromCSV.constructGraphFromCSV("info6205.spring2023.teamproject.csv");
        int count = graph.getNumVertices();
        //Drawing a Circle
        Group root = new Group();

        StackPane p = new StackPane();
        //String numstr;
        List<String> verticesList = graph.getVertices();
        for(String eachvertex: verticesList){
            Circle circle = new Circle(10,Color.RED);
            circle.setCenterX(rand.nextInt(110,1400));
            circle.setCenterY(rand.nextInt(60,700));
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
        //MST UI
        Button btMST = new Button("MST");
        btMST.setLayoutX(0);
        btMST.setLayoutY(10);
        btMST.setStyle("-fx-background-color: #DEB887");
        MSThandlerclass mstHandler = new MSThandlerclass(graph,circleDataList,root);
        btMST.setOnAction(mstHandler);
        root.getChildren().add(btMST);

        //Odd Degree UI
        Button btOddDegree = new Button("Odd Degree Vertices");
        btOddDegree.setLayoutX(50);
        btOddDegree.setLayoutY(10);
        btOddDegree.setStyle("-fx-background-color: #DEB887");
        OddDegreehandlerclass OddDegreeHandler = new OddDegreehandlerclass(graph,circleDataList,root);
        btOddDegree.setOnAction(OddDegreeHandler);
        root.getChildren().add(btOddDegree);

        //Eulerian Tour UI
        Button btEulerianTour = new Button("Eulerian Tour");
        btEulerianTour.setLayoutX(190);
        btEulerianTour.setLayoutY(10);
        btEulerianTour.setStyle("-fx-background-color: #DEB887");
        EulerianTourhandlerclass EulerianTourHandler = new EulerianTourhandlerclass(graph,circleDataList,root);
        btEulerianTour.setOnAction(EulerianTourHandler);
        root.getChildren().add(btEulerianTour);


        //Christofide UI
        Button btHamiltonian = new Button("Christofides Algorithm");
        btHamiltonian.setLayoutX(285);
        btHamiltonian.setLayoutY(10);
        btHamiltonian.setStyle("-fx-background-color: #DEB887");
        Hamiltonianhandlerclass HamiltonianHandler = new Hamiltonianhandlerclass(graph,circleDataList,root);
        btHamiltonian.setOnAction(HamiltonianHandler);
        root.getChildren().add(btHamiltonian);

        //2-opt UI
        Button btTwoOpt = new Button("2-Opt");
        btTwoOpt.setLayoutX(430);
        btTwoOpt.setLayoutY(10);
        btTwoOpt.setStyle("-fx-background-color: #DEB887");
        TwoOpthandlerclass TwoOptHandler = new TwoOpthandlerclass(graph,circleDataList,root);
        btTwoOpt.setOnAction(TwoOptHandler);
        root.getChildren().add(btTwoOpt);

        //3-opt UI
        Button btThreeOpt = new Button("3-Opt");
        btThreeOpt.setLayoutX(490);
        btThreeOpt.setLayoutY(10);
        btThreeOpt.setStyle("-fx-background-color: #DEB887");
        ThreeOpthandlerclass ThreeOptHandler = new ThreeOpthandlerclass(graph,circleDataList,root);
        btThreeOpt.setOnAction(ThreeOptHandler);
        root.getChildren().add(btThreeOpt);

        //Simulated Annealing UI
        Button btsimulatedAnnealing = new Button("Simulated Annealing");
        btsimulatedAnnealing.setLayoutX(550);
        btsimulatedAnnealing.setLayoutY(10);
        btsimulatedAnnealing.setStyle("-fx-background-color: #DEB887");
        simulatedAnnealinghandlerclass simulatedAnnealingHandler = new simulatedAnnealinghandlerclass(graph,circleDataList,root);
        btsimulatedAnnealing.setOnAction(simulatedAnnealingHandler);
        root.getChildren().add(btsimulatedAnnealing);

        //Ant Colony Optimization UI
        Button btAntColony = new Button("Ant Colony Optimization");
        btAntColony.setLayoutX(685);
        btAntColony.setLayoutY(10);
        btAntColony.setStyle("-fx-background-color: #DEB887");
        antcolonyhandlerclass antcolonyHandler = new antcolonyhandlerclass(graph,circleDataList,root);
        btAntColony.setOnAction(antcolonyHandler);
        root.getChildren().add(btAntColony);

        //Creating a scene object
        Scene scene = new Scene(root, 2000, 800);
        //Setting title to the Stage
        stage.setTitle("Travelling Salesman");
        //Adding scene to the stage
        stage.setScene(scene);
        //Displaying the contents of the stage
        stage.show();

    }

    class EulerianTourhandlerclass implements EventHandler<ActionEvent> {

        private Graph graph;
        private List<CircleData> circleDataList;
        private Group root;

        EulerianTourhandlerclass(Graph graph,List<CircleData> circleDataList,Group root){
            this.graph = graph;
            this.circleDataList = circleDataList;
            this.root = root;
        }

        @Override
        public void handle(ActionEvent event){

            for (Node node : root.getChildren()) {

                if(node instanceof Label){
                    Label label = (Label) node;
                    label.setText("Eurelian Tour");


                }
            }

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
            Graph H = MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
            Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
            List<String> tour = HierholzerAlgorithm.findEulerTour(H);
            List<String> eulerTour = Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);




            //List<String> hamiltonianCycle = MinimumWeightPerfectMatching.minimumWeightMatching((PrimsMST.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(PrimsMST.primMST(graph)));

            double startX ,startY,endX,endY;
            for(int i=0;i< eulerTour.size()-1;i++){
                startX = 0;startY=0 ;endX=0;endY=0;

                for(CircleData eachCircleData : circleDataList){
                    if(eulerTour.get(i)==eachCircleData.getName()) {
                        startX = eachCircleData.getX();
                        startY = eachCircleData.getY();
                    }
                    if(eulerTour.get(i+1)==eachCircleData.getName()){
                        endX= eachCircleData.getX();
                        endY=eachCircleData.getY();

                    }
                }
                Line line = new Line(startX, startY, endX, endY);
                line.setStroke(Color.DARKMAGENTA);
                root.getChildren().add(line);
            }

        }

    }

    class Hamiltonianhandlerclass implements EventHandler<ActionEvent> {

        private Graph graph;
        private List<CircleData> circleDataList;
        private Group root;

        Hamiltonianhandlerclass(Graph graph,List<CircleData> circleDataList,Group root){
            this.graph = graph;
            this.circleDataList = circleDataList;
            this.root = root;
        }

        @Override
        public void handle(ActionEvent event){

            for (Node node : root.getChildren()) {

                if(node instanceof Label){
                    Label label = (Label) node;
                    label.setText("Christofide's Algorithm");


                }
            }

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
            Graph H = MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
            Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
            List<String> tour = HierholzerAlgorithm.findEulerTour(H);
            List<String> eulerTour = Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);
            List<String> hamiltonianCycle = Hamiltonian.shortcutEulerianCycle(eulerTour,edgeWeight);



            //List<String> hamiltonianCycle = MinimumWeightPerfectMatching.minimumWeightMatching((PrimsMST.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(PrimsMST.primMST(graph)));

            double startX ,startY,endX,endY;
            for(int i=0;i< hamiltonianCycle.size()-1;i++){
                startX = 0;startY=0 ;endX=0;endY=0;

                for(CircleData eachCircleData : circleDataList){
                    if(hamiltonianCycle.get(i)==eachCircleData.getName()) {
                        startX = eachCircleData.getX();
                        startY = eachCircleData.getY();
                    }
                    if(hamiltonianCycle.get(i+1)==eachCircleData.getName()){
                        endX= eachCircleData.getX();
                        endY=eachCircleData.getY();

                    }
                }
                Line line = new Line(startX, startY, endX, endY);
                line.setStroke(Color.BLUE);
                root.getChildren().add(line);
            }

        }

    }

    class TwoOpthandlerclass implements EventHandler<ActionEvent> {

        private Graph graph;
        private List<CircleData> circleDataList;
        private Group root;

        TwoOpthandlerclass(Graph graph,List<CircleData> circleDataList,Group root){
            this.graph = graph;
            this.circleDataList = circleDataList;
            this.root = root;
        }

        @Override
        public void handle(ActionEvent event){

            for (Node node : root.getChildren()) {

                if(node instanceof Label){
                    Label label = (Label) node;
                    label.setText("2-Opt");


                }
            }

            ArrayList<Node> linesToRemove = new ArrayList<>();
            for (Node node : root.getChildren()) {
                if (node instanceof Line) {
                    linesToRemove.add(node);
                }
            }
            if(linesToRemove!=null){
                root.getChildren().removeAll(linesToRemove);
            }

            //List<String> hamiltonianCycle = MinimumWeightPerfectMatching.minimumWeightMatching((PrimsMST.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(PrimsMST.primMST(graph)));
            //Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
            //List<String> twoOptRoute = Hamiltonian.twoOpt(hamiltonianCycle, edgeWeight);
            PrimsMST mst=new PrimsMST();
            Graph H = MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
            Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
            List<String> tour = HierholzerAlgorithm.findEulerTour(H);
            List<String> eulerTour = Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);
            List<String> hamiltonianCycle = Hamiltonian.shortcutEulerianCycle(eulerTour,edgeWeight);
            List<String> twoOptRoute = TwoOpt.twoOpt(hamiltonianCycle, edgeWeight);

            double startX ,startY,endX,endY;
            for(int i=0;i< twoOptRoute.size()-1;i++){
                startX = 0;startY=0 ;endX=0;endY=0;

                for(CircleData eachCircleData : circleDataList){
                    if(twoOptRoute.get(i)==eachCircleData.getName()) {
                        startX = eachCircleData.getX();
                        startY = eachCircleData.getY();
                    }
                    if(twoOptRoute.get(i+1)==eachCircleData.getName()){
                        endX= eachCircleData.getX();
                        endY=eachCircleData.getY();

                    }
                }
                Line line = new Line(startX, startY, endX, endY);
                line.setStroke(Color.GREEN);
                root.getChildren().add(line);
            }

        }

    }


    class ThreeOpthandlerclass implements EventHandler<ActionEvent> {

        private Graph graph;
        private List<CircleData> circleDataList;
        private Group root;

        ThreeOpthandlerclass(Graph graph,List<CircleData> circleDataList,Group root){
            this.graph = graph;
            this.circleDataList = circleDataList;
            this.root = root;
        }

        @Override
        public void handle(ActionEvent event){

            for (Node node : root.getChildren()) {

                if(node instanceof Label){
                    Label label = (Label) node;
                    label.setText("3-Opt");


                }
            }

            ArrayList<Node> linesToRemove = new ArrayList<>();
            for (Node node : root.getChildren()) {
                if (node instanceof Line) {
                    linesToRemove.add(node);
                }
            }
            if(linesToRemove!=null){
                root.getChildren().removeAll(linesToRemove);
            }

            //List<String> hamiltonianCycle = MinimumWeightPerfectMatching.minimumWeightMatching((PrimsMST.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(PrimsMST.primMST(graph)));
            //Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
            //List<String> threeOptRoute = Hamiltonian.threeOpt(hamiltonianCycle, edgeWeight);

            PrimsMST mst=new PrimsMST();
            Graph H = MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
            Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
            List<String> tour = HierholzerAlgorithm.findEulerTour(H);
            List<String> eulerTour = Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);
            List<String> hamiltonianCycle = Hamiltonian.shortcutEulerianCycle(eulerTour,edgeWeight);
            List<String> threeOptRoute = threeOpt(hamiltonianCycle,edgeWeight );

            double startX ,startY,endX,endY;
            for(int i=0;i< threeOptRoute.size()-1;i++){
                startX = 0;startY=0 ;endX=0;endY=0;

                for(CircleData eachCircleData : circleDataList){
                    if(threeOptRoute.get(i)==eachCircleData.getName()) {
                        startX = eachCircleData.getX();
                        startY = eachCircleData.getY();
                    }
                    if(threeOptRoute.get(i+1)==eachCircleData.getName()){
                        endX= eachCircleData.getX();
                        endY=eachCircleData.getY();

                    }
                }
                Line line = new Line(startX, startY, endX, endY);
                line.setStroke(Color.DARKGOLDENROD);
                root.getChildren().add(line);
            }

        }

    }


    class simulatedAnnealinghandlerclass implements EventHandler<ActionEvent> {

        private Graph graph;
        private List<CircleData> circleDataList;
        private Group root;

        simulatedAnnealinghandlerclass(Graph graph,List<CircleData> circleDataList,Group root){
            this.graph = graph;
            this.circleDataList = circleDataList;
            this.root = root;
        }

        @Override
        public void handle(ActionEvent event){

            for (Node node : root.getChildren()) {

                if(node instanceof Label){
                    Label label = (Label) node;
                    label.setText("Simulated Annealing");


                }
            }

            ArrayList<Node> linesToRemove = new ArrayList<>();
            for (Node node : root.getChildren()) {
                if (node instanceof Line) {
                    linesToRemove.add(node);
                }
            }
            if(linesToRemove!=null){
                root.getChildren().removeAll(linesToRemove);
            }

           // List<String> hamiltonianCycle = MinimumWeightPerfectMatching.minimumWeightMatching((PrimsMST.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(PrimsMST.primMST(graph)));
           // Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
           // List<String> threeOptRoute = Hamiltonian.threeOpt(hamiltonianCycle, edgeWeight);

            PrimsMST mst=new PrimsMST();
            Graph H = MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
            Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
            List<String> tour = HierholzerAlgorithm.findEulerTour(H);
            List<String> eulerTour = Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);
            List<String> hamiltonianCycle = Hamiltonian.shortcutEulerianCycle(eulerTour,edgeWeight);
            List<String> threeOptRoute = simulatedAnnealing(hamiltonianCycle,edgeWeight);

            double startX ,startY,endX,endY;
            for(int i=0;i< threeOptRoute.size()-1;i++){
                startX = 0;startY=0 ;endX=0;endY=0;

                for(CircleData eachCircleData : circleDataList){
                    if(threeOptRoute.get(i)==eachCircleData.getName()) {
                        startX = eachCircleData.getX();
                        startY = eachCircleData.getY();
                    }
                    if(threeOptRoute.get(i+1)==eachCircleData.getName()){
                        endX= eachCircleData.getX();
                        endY=eachCircleData.getY();

                    }
                }
                Line line = new Line(startX, startY, endX, endY);
                line.setStroke(Color.ORANGE);
                root.getChildren().add(line);
            }

        }

    }

    class antcolonyhandlerclass implements EventHandler<ActionEvent> {

        private Graph graph;
        private List<CircleData> circleDataList;
        private Group root;

        antcolonyhandlerclass(Graph graph,List<CircleData> circleDataList,Group root){
            this.graph = graph;
            this.circleDataList = circleDataList;
            this.root = root;
        }

        @Override
        public void handle(ActionEvent event){

            for (Node node : root.getChildren()) {

                if(node instanceof Label){
                    Label label = (Label) node;
                    label.setText("Ant Colony Optimization");


                }
            }

            ArrayList<Node> linesToRemove = new ArrayList<>();
            for (Node node : root.getChildren()) {
                if (node instanceof Line) {
                    linesToRemove.add(node);
                }
            }
            if(linesToRemove!=null){
                root.getChildren().removeAll(linesToRemove);
            }

            // List<String> hamiltonianCycle = MinimumWeightPerfectMatching.minimumWeightMatching((PrimsMST.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(PrimsMST.primMST(graph)));
            // Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
            // List<String> threeOptRoute = Hamiltonian.threeOpt(hamiltonianCycle, edgeWeight);

            PrimsMST mst=new PrimsMST();
            Graph H = MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
            Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
            List<String> tour = HierholzerAlgorithm.findEulerTour(H);
            List<String> eulerTour = Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);
            List<String> hamiltonianCycle = Hamiltonian.shortcutEulerianCycle(eulerTour,edgeWeight);
            List<String> threeOptRoute = simulatedAnnealing(hamiltonianCycle,edgeWeight);

            double startX ,startY,endX,endY;
            for(int i=0;i< threeOptRoute.size()-1;i++){
                startX = 0;startY=0 ;endX=0;endY=0;

                for(CircleData eachCircleData : circleDataList){
                    if(threeOptRoute.get(i)==eachCircleData.getName()) {
                        startX = eachCircleData.getX();
                        startY = eachCircleData.getY();
                    }
                    if(threeOptRoute.get(i+1)==eachCircleData.getName()){
                        endX= eachCircleData.getX();
                        endY=eachCircleData.getY();

                    }
                }
                Line line = new Line(startX, startY, endX, endY);
                line.setStroke(Color.AQUAMARINE);
                root.getChildren().add(line);
            }

        }

    }

    public static void main(String args[]){
        launch(args);
    }
}

