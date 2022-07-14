/**
 * 
 */
package csc3a.graph.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.TYPE;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import csc3a.graph.model.LEVEL;
import csc3a.graph.model.Programmer;
import csc3a.graph.model.QueueList;
import csc3a.graph.model.StackList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 * 
 * SocialNetworkGraphsPane class provide the layout to be place on the scene by inheriting from StackPane layout for users
 * to use when interacting with the application.
 *
 */
public class SocialNetworkGraphPane extends StackPane{
	
	/** Attributes  of layout*/
	private BorderPane root  = null;
	private FlowPane   flow  = null;
	private GridPane   grid  = null;
	private VBox       vBox  = null, vBoxA = null;
	private HBox       hBox  = null, hBoxA = null, hBoxC = null, hBoxD;
	private Pane       pane  = null;
	private StackPane  stack = null;
	
	/** Buttons attributes*/
	private Button profileButton          = null,
			       exitButton             = null,
			       friendSuggestionButton = null,
			       notificationButton     = null,
			       befriendButton         = null,
			       unfriendButton         = null,
			       friendsOfFriendButton  = null,
			       popupAlertExitButton   = null,
			       popupProjectNameButton = null,
			       popupExpertFieldButton = null;
	
	/** Label attributes*/
	private Label profileCreationLabel  = null,
			      messageDisplayLabel   = null,
	              programerIDLabel      = null,
	              projectNameLabel      = null,
	              fieldOfExpertiseLabel = null,
	              levelEntryLabel       = null,
	              idProgrammerLabel     = null,
	              notificationLabel     = null,
	              unfriendLabel         = null,
	              popupAlertLabel       = null,
	              popupProjectNameLabel = null,
	              popupExpertFieldLabel = null;
	
	private TextField  programmerIDTextField     = null,
			           unfriendTextField         = null,
			           popupProjectNameTextField = null,
			           popupExpertFieldTextField = null; 
	
	private TextArea notificationTextArea = null;
	
	private ComboBox<String> projectNameBox      = null,
	                         fieldOfExpertiseBox = null;
	private ComboBox<LEVEL>  levelEntryBox       = null;
	
	private ImageView exitImage = null;
	
	private Popup alertPopUp = null,
			      projectNamePopUp = null,
			      fieldExpertPopUp = null;
	
	//private Circle programerRepCircle = null;
	/**Create a graph instance field*/
	private Graph<Programmer> programmerGraph = new Graph<>();
	
	/** Collect all the Vertexes containing programmer created*/
	private Collection<Vertex<Programmer>> vertexCollection = new ArrayList<>();	 // Vertex Collection for graph
	/** Collect all the Edges containing programmer created*/
	private Collection<Edge<Programmer>>   edgeCollection   = new ArrayList<>();  // Edge   Collection for graph
	
	private ArrayList<Vertex<Programmer>> vp = new ArrayList<>();
	private ArrayList<Edge<Programmer>>   ep = new ArrayList<>();
	private ArrayList<Circle>             cp = new ArrayList<>();
	private ArrayList<Line>               lp = new ArrayList<>();
	
	private QueueList<Vertex<Programmer>> queueVProgrammer = new QueueList<>();
	private StackList<Vertex<Programmer>> stackVProgrammer = new StackList<>();
	
	private Stage stage = null;
	
	/**
	 * Parameterized constructor
	 *  
	 * @param stage  stage parameter
	 */
	public SocialNetworkGraphPane(Stage stage) {
		
		this.stage = stage;
		
		setUpGUI();   // Invoke the method
		
		setLayoutStyle();  // Invoke set layout style
		
		setProgrammerProfile();  // set profile method invocation
		
		Random random = new Random(15);
		Random random2 = new Random(20);
		
		// create programmer profile
		profileButton.setOnAction(e -> {
			
			befriendButton.setDisable(false);
			friendsOfFriendButton.setDisable(true);
			unfriendTextField.setText("");
			createProgrammerProfile(random, random2); // Create new profile for programmer method invocation
			
		});
		
		// Exit the application
		exitButton.setOnAction(e -> {
			System.exit(-1);
		});
		
		// Pop up  Alert
		popupAlertExitButton.setOnAction(e -> {
			alertPopUp.hide();
		});
		
		projectNameBox.setOnAction(e -> {
			
			projectNamePopUp.show(stage);
			String str = projectNameBox.getValue();
			
			if(str.equals("OTHER")) {
				
				projectNamePopUp.show(stage);	
				
				popupProjectNameButton.setOnAction(ev -> {
					
					String a = popupProjectNameTextField.getText();
					if(a != null) {
						
						projectNamePopUp.hide();						
						//programmer.setStrProjectName(a);
					} 
					
				});
				
				
			} else {
				
				//programmer.setStrProjectName(projectNameBox.getValue());
			}
			
		});
		
		fieldOfExpertiseBox.setOnAction(e -> {
			
			fieldExpertPopUp.show(stage);
			String str = fieldOfExpertiseBox.getValue();
			
			if(str.equals("OTHER")) {
				
				fieldExpertPopUp.show(stage);	
				
				popupExpertFieldButton.setOnAction(ev -> {
					
					String a = popupExpertFieldTextField.getText();
					if(a != null) {
						
						fieldExpertPopUp.hide();						
						//programmer.setStrProjectName(a);
					} 
					
				});
				
				
			} else {
				
				//programmer.setStrProjectName(projectNameBox.getValue());
			}
			
		});
		
		
		
		
		// Suggest friends to programmer
		//friendSuggestionButton.setOnAction(e -> {
			
		//});
		
	}
	
	/**
	 * setUpGui method to set the graphical user interface up
	 */
	public void setUpGUI() {
		
		// Instances of the layouts
		root  = new BorderPane();
		flow  = new FlowPane();     
		grid  = new GridPane();
		stack = new StackPane();
		vBox  = new VBox(); 
		vBoxA = new VBox();
		hBox  = new HBox(10);
		hBoxA = new HBox(10);
		hBoxC = new HBox(10);
		hBoxD = new HBox(10);
		pane  = new Pane();
		
		notificationTextArea = new TextArea();
		
		profileCreationLabel  = new Label("CREATE PROGRAMMER PROFILE");
		messageDisplayLabel   = new Label("DISPLAY MESSAGE RESPONSE");
		programerIDLabel      = new Label("Programmers ID: ");
		projectNameLabel      = new Label("Project Name: ");
		fieldOfExpertiseLabel = new Label("Field of Expertise: ");
		levelEntryLabel       = new Label("Entry Level: ");
		idProgrammerLabel     = new Label();
		notificationLabel     = new Label();
		unfriendLabel         = new Label("PROGRAMMER ID: ");
		popupAlertLabel       = new Label();
		popupProjectNameLabel = new Label("PLEASE SPECIFY");
		popupExpertFieldLabel = new Label("PLEASE SPECIFY");
		
		profileButton          = new Button("CREATE PROFILE");
		exitButton             = new Button("EXIT");
		friendSuggestionButton = new Button("SUGGEST FRIENDS");
		notificationButton     = new Button("NOTIFICATION");
		befriendButton         = new Button("BEFRIEND");
		unfriendButton         = new Button("UNFRIEND");
		friendsOfFriendButton  = new Button("MUTUAL FRIENDS");
		popupAlertExitButton   = new Button("GOT IT!!");
		popupProjectNameButton = new Button("DONE");
		popupExpertFieldButton = new Button("DONE");
		
		programmerIDTextField     = new TextField();
		unfriendTextField         = new TextField();
		popupProjectNameTextField = new TextField();
		popupExpertFieldTextField = new TextField();
		
		projectNameBox      = new ComboBox<>();
		fieldOfExpertiseBox = new ComboBox<>();
		levelEntryBox       = new ComboBox<>();
		
		exitImage  = new ImageView("file:data/closeIMG.png");
		
		alertPopUp        = new Popup();
		projectNamePopUp = new Popup();
		fieldExpertPopUp = new Popup();
		
		grid.add(programerIDLabel, 0, 0);
		grid.add(programmerIDTextField, 1, 0);
		grid.add(projectNameLabel, 0, 1);
		grid.add(projectNameBox, 1, 1);
		grid.add(fieldOfExpertiseLabel, 0, 2);
		grid.add(fieldOfExpertiseBox, 1, 2);
		grid.add(levelEntryLabel, 0, 3);
		grid.add(levelEntryBox, 1, 3);
		grid.add(profileButton, 1, 4);
		
		vBoxA.getChildren().add(popupAlertLabel);
		vBoxA.getChildren().add(popupAlertExitButton);
		
		hBox.getChildren().add(friendSuggestionButton);
		hBox.getChildren().add(notificationButton);
		hBox.getChildren().add(befriendButton);
		hBox.getChildren().add(friendsOfFriendButton);
		
		hBoxA.getChildren().add(unfriendLabel);
		hBoxA.getChildren().add(unfriendTextField);
		hBoxA.getChildren().add(unfriendButton);
		
		hBoxC.getChildren().add(popupProjectNameLabel);
		hBoxC.getChildren().add(popupProjectNameTextField);
		hBoxC.getChildren().add(popupProjectNameButton);
		
		hBoxD.getChildren().add(popupExpertFieldLabel);
		hBoxD.getChildren().add(popupExpertFieldTextField);
		hBoxD.getChildren().add(popupExpertFieldButton);
		
		alertPopUp.getContent().add(vBoxA);
		projectNamePopUp.getContent().add(hBoxC);
		fieldExpertPopUp.getContent().add(hBoxD);
		
		flow.getChildren().add(messageDisplayLabel);
		flow.getChildren().add(notificationTextArea);
		
		vBox.getChildren().add(profileCreationLabel);
		vBox.getChildren().add(grid);
		vBox.getChildren().add(hBox);
		vBox.getChildren().add(hBoxA);
		vBox.getChildren().add(flow);
		vBox.getChildren().add(notificationLabel);
		vBox.getChildren().add(exitButton);
		
		pane.getChildren().add(stack);
		
		root.setLeft(vBox);
		root.setCenter(pane);
		
		this.getChildren().add(root);
		
	}
	
	/**
	 * create new profile of a programmer
	 */
	private void createProgrammerProfile(Random random, Random random2) {
		
		int yValue = random.nextInt(500);
		int xValue = random2.nextInt(500);
		
		System.out.println("[ " + yValue + ", " + xValue + " ]");
		
		Programmer programmer = new Programmer();  // Create the programmer instance profile
		
		// Check if the id field is filled
		if(programmerIDTextField.getText() != null){
			programmer.setStrID(programmerIDTextField.getText());
		}
		
		
		// Make programmer unique
		if(projectNameBox.getValue().equals("OTHER")){
			
			programmer.setStrProjectName(popupProjectNameTextField.getText());
		} else {
			
			programmer.setStrProjectName(projectNameBox.getValue());
		}
		
		// Make programmer unique
		if(fieldOfExpertiseBox.getValue().equals("OTHER")){
			
			programmer.setStrITExpertise(popupExpertFieldTextField.getText());
		} else {
			
			programmer.setStrITExpertise(fieldOfExpertiseBox.getValue());
		}
		

		programmer.setEnumLevel(levelEntryBox.getValue());
		
		Vertex<Programmer> programmerVertex = new Vertex<>(programmer);   // Store the programmer element to the Vertex
		
		vertexCollection.add(programmerVertex);    // Add the vertex to the collection
		
		System.out.println(vertexCollection.size());
		idProgrammerLabel.setText(programmerIDTextField.getText());
		
		Circle programerRepCircle = new Circle(20);   
		       cp.add(programerRepCircle);           // Add programmer node to keep track of
		Label  idProgrammerLabel  = new Label(programmer.getStrID());
               idProgrammerLabel.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 18));
               idProgrammerLabel.setTextFill(Color.GREEN);
		
		Pane stackProgrammerIDCircle = new Pane();
		
		stackProgrammerIDCircle.getChildren().add(programerRepCircle);
		stackProgrammerIDCircle.getChildren().add(idProgrammerLabel);

		programerRepCircle.setCenterY(yValue);
		programerRepCircle.setCenterX(xValue);
		idProgrammerLabel.relocate(programerRepCircle.getCenterX(), programerRepCircle.getCenterY());
		
		
		programmerGraph.getVertices().add(programmerVertex);   // ADD PROGRAMMER TO THE GRAPH		
		
		pane.getChildren().add(stackProgrammerIDCircle);
		
		notificationTextArea.appendText("Programmer with ID " + programmer.getStrID() + " created profile\n");

		
		//Button to suggest friends for newly created programmer profile
		friendSuggestionButton.setOnAction(e -> {
						
			for (int i = 0; i < programmerGraph.getVertices().size() - 1; i++) {
				
				if(programmer.compareTo(programmerGraph.getVertices().get(i).getValue()) == 0) {
					
					notificationTextArea.appendText("**You have something in common with Programmer whose ID is #" + programmerGraph.getVertices().get(i).getValue().getStrID() + "\n");
					
				} 
			}
			
		});
		
		// Follow a programmer
		befriendButton.setOnAction(e -> {
			
			unfriendButton.setDisable(false);
			
			for (int i = 0; i < programmerGraph.getVertices().size() - 1; i++) {
				
				if(programmer.compareTo(programmerGraph.getVertices().get(i).getValue()) == 0) {
					
					Edge<Programmer> edgeProgrammerF = new Edge<>((56 + 4 * i), programmerVertex, programmerGraph.getVertices().get(i));  // Created the edge
					Edge<Programmer> edgeProgrammerT = new Edge<>((56 + 4 * i), programmerGraph.getVertices().get(i), programmerVertex); 
					programmerGraph.getEdges().add(edgeProgrammerF);
					programmerGraph.getEdges().add(edgeProgrammerT);
	                ep.add(edgeProgrammerF);
	                programmerVertex.addEdge(edgeProgrammerF);
	                programmerGraph.getVertices().get(i).addEdge(edgeProgrammerT);
					
					Line  line           = new Line(programerRepCircle.getCenterX(), programerRepCircle.getCenterY(), cp.get(i).getCenterX(), cp.get(i).getCenterY());
					Label costEdgeLabel  = new Label();
					lp.add(line);
					pane.getChildren().add(line);   // Add edge to the 
					
					System.out.println(edgeProgrammerF.getCost());
					                 
					costEdgeLabel.setText(""  + edgeProgrammerF.getCost());
					costEdgeLabel.relocate((line.getStartX() + line.getEndX()) / 2, (line.getStartY() + line.getEndY()) / 2);
					//costEdgeLabel.setFont(Font.font(33));
					
					pane.getChildren().add(costEdgeLabel);
					
					befriendButton.setDisable(true);
					friendsOfFriendButton.setDisable(false);
				} 
			}
			
		});		
		
		// unfriend a programmer
		unfriendButton.setOnAction(ee -> {
			
			alertPopUp.show(stage);
			
			String programmerStringID = unfriendTextField.getText();
			
			notificationTextArea.appendText("***" + programmerStringID + " ID of a programmer to unfriend\n");
			
			if(!programmerStringID.equals("")) {
				
				for(int i = 0; i < programmerVertex.getEdges().size(); i++) {
					
					String tempStr = programmerVertex.getEdges().get(i).getToVertex().getValue().getStrID();				
					
					if(tempStr.equals(programmerStringID)) {   // If the given id exist inside the edge of a contain
						
						for(int p = 0; p < programmerGraph.getEdges().size(); p++) {   // Go through all the edges in the Graph
							
							// Compare all available edges with the edge of interest
							if(programmerGraph.getEdges().get(p).equals(programmerVertex.getEdges().get(i))) {  
								
								programmerGraph.getEdges().remove(programmerGraph.getEdges().get(p));
								
								notificationTextArea.appendText("Programmer whose ID is " + programmerStringID + " is unfriend by programmer whose ID is " + programmerVertex.getValue().getStrID() + "\n");
								
							} else {
								
								notificationLabel.setText("Unfollow successfully");
								
							}
						}
						
					} else {
						
						notificationLabel.setText("You have no such friend whose ID is " + programmerStringID);
						alertPopUp.hide();
					}
					
				}
				
				/*for(int i = 0; i < programmerGraph.getVertices().size() - 1; i++) {
					
					// Compare the given ID with the ID of the current programmer
					if(programmerGraph.getVertices().get(i).getValue().getStrID().equals(programmerStringID)) {  
						
						// If found go through its edges
						for(int j = 0; j < programmerGraph.getVertices().get(i).getEdges().size() - 1; j++) {
							
							System.out.println(programmerGraph.getVertices().get(i).getEdges().get(6));
							if(programmerGraph.getVertices().get(i).getEdges().get(j).getToVertex().getValue().getStrID().equals(programmerStringID)) {
								programmerGraph.getVertices().get(i).getEdges().remove(j);
								
							}
						}
						
						notificationLabel.setText("You are no longer friends with a programmer whose ID is " + programmerStringID);
						notificationLabel.setTextFill(Color.GREEN);
					} else {
						System.out.println("NEGATIVE");
					}
					
				}*/
				
			} else {
				
				notificationLabel.setText("PLEASE FILL THE ID OF A PROGRAMMER YOU WISH TO UNFRIEND");
				notificationLabel.setTextFill(Color.RED);
			}
			
		});
		
		// notification
		notificationButton.setOnAction(event -> {
			
			notificationTextArea.appendText("");
			
			// Check the number of friends 
			if(programmerVertex.getEdges().size() == 0) {
				
				notificationLabel.setText("Programmer with ID " + programmerVertex.getValue().getStrID() + " has " + programmerVertex.getEdges().size() + " friends");
			
			} else if(programmerVertex.getEdges().size() == 1){
				
				notificationLabel.setText("Programmer with ID " + programmerVertex.getValue().getStrID() + " has " + programmerVertex.getEdges().size() + " friend");
				notificationTextArea.appendText("Programmer with ID " + programmerVertex.getValue().getStrID() + "'s friend is a programmer whose ID is " + programmerVertex.getEdges().get(0).getToVertex().getValue().getStrID());
				notificationTextArea.appendText("\n");

			} else {
				
				notificationLabel.setText("Programmer with ID " + programmerVertex.getValue().getStrID() + " has " + programmerVertex.getEdges().size() + " friendS");
				notificationTextArea.appendText("Programmer with ID " + programmerVertex.getValue().getStrID() + "List of friends are {");
				for(int i = 0; i < programmerVertex.getEdges().size(); i++) {
					
					notificationTextArea.appendText( programmerVertex.getEdges().get(i).getToVertex().getValue().getStrID() + " -> ");

				}
				
				notificationTextArea.appendText("} ");
				notificationTextArea.appendText("\n");

			}
			
		});
		
		// mutual friends
		friendsOfFriendButton.setOnAction(e -> {
			
			notificationTextArea.appendText(" \n");
			
			if(programmerVertex.getEdges().size() == 0) {
				
				notificationLabel.setText("Programmer with ID " + programmerVertex.getValue().getStrID() + " must make friends first"
						+ "\n");
			} else {
				
				for(int i = 0; i < programmerVertex.getEdges().size(); i++) {
					
					notificationTextArea.appendText("Programmers whose IDs are  " + programmerVertex.getValue().getStrID() + " and : \n");
					notificationTextArea.appendText("\t*" + programmerVertex.getEdges().get(i).getToVertex().getEdges().get(0).getToVertex().getValue().getStrID() + "\n");
					String currentID = programmerVertex.getEdges().get(i).getToVertex().getEdges().get(0).getToVertex().getValue().getStrID();
					String nextID = programmerVertex.getEdges().get(i).getToVertex().getEdges().get(0).getToVertex().getValue().getStrID();
					
					for (int j = 0; j < programmerVertex.getEdges().get(i).getToVertex().getEdges().size() - 1; j++) {
						
						if(!currentID.equals(programmerVertex.getEdges().get(i).getToVertex().getEdges().get(j).getToVertex().getValue().getStrID()) &&
							!nextID.equals(programmerVertex.getEdges().get(i).getToVertex().getEdges().get(j).getToVertex().getValue().getStrID())) {
							
							notificationTextArea.appendText("\t*" + programmerVertex.getEdges().get(i).getToVertex().getEdges().get(j).getToVertex().getValue().getStrID() + " \n");
						    nextID = programmerVertex.getEdges().get(i).getToVertex().getEdges().get(j).getToVertex().getValue().getStrID();
						}
						
						
					}
					
					notificationTextArea.appendText("have a mutual friend programmer whose ID is #" + programmerVertex.getEdges().get(i).getToVertex().getValue().getStrID() + "\n");
					notificationTextArea.appendText(" \n");

				}
				
			}
			
		});
		
		// Move a programmer around
		programerRepCircle.setOnMouseDragged(event -> {
			
			programerRepCircle.setCenterY(event.getY());
			programerRepCircle.setCenterX(event.getX());
			idProgrammerLabel.relocate(programerRepCircle.getCenterX(), programerRepCircle.getCenterY());
			
			
		});
		
	}
	
	/**
	 * Initial programmer profile setter method
	 */
	private void setProgrammerProfile() {
		
		friendsOfFriendButton.setDisable(true);
		unfriendButton.setDisable(true);
		
		/*
		 * Programmer A
		 */
		Programmer programmer = new Programmer();  // Create the programmer instance profile
		
		programmer.setStrID("45GHS");
		
		programmer.setStrProjectName("SOCIAL NET WITH GRAPH");
		programmer.setStrITExpertise("MACHINE LEARNING USING JAVA");
		programmer.setEnumLevel(LEVEL.PROFESSIONAL);
		
		Vertex<Programmer> programmerVertex77 = new Vertex<>(programmer, 1);    // Store the programmer element to the Vertex
		                   vertexCollection.add(programmerVertex77);            // Collection List
		                   vp.add(programmerVertex77);                          // ArrayList List
		                   queueVProgrammer.enqueue(programmerVertex77);        // Queue List
		                   stackVProgrammer.push(programmerVertex77);
		
		Circle programerRepCircle = new Circle(20);
		       cp.add(programerRepCircle);
		Label  idProgrammerLabel  = new Label(programmer.getStrID());
	           idProgrammerLabel.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 18));
	           idProgrammerLabel.setTextFill(Color.GREEN);
		
	     
	           
	   Pane stackProgrammerIDCircle = new Pane();
		     stackProgrammerIDCircle.getChildren().add(programerRepCircle);
		     stackProgrammerIDCircle.getChildren().add(idProgrammerLabel);
		
		programerRepCircle.setCenterY(100);
		programerRepCircle.setCenterX(150);
		idProgrammerLabel.relocate(programerRepCircle.getCenterX(), programerRepCircle.getCenterY());
		
		pane.getChildren().add(stackProgrammerIDCircle);
		
		
		/*
		 * Programmer B
		 */
		Programmer programmer1 = new Programmer();  // Create the programmer instance profile
		
		programmer1.setStrID("88YUF");
		
		programmer1.setStrProjectName("SELF DRIVING CAR ALGO");
		programmer1.setStrITExpertise("MACHINE LEARNING USING JAVA");
		programmer1.setEnumLevel(LEVEL.BEGINNER);
		
		Vertex<Programmer> programmerVertex1 = new Vertex<>(programmer1, 1);   // Store the programmer element to the Vertex
		                   vertexCollection.add(programmerVertex1);
		                   vp.add(programmerVertex1);
		                   queueVProgrammer.enqueue(programmerVertex1);
		
		Circle programerRepCircle1 = new Circle(20);
		       cp.add(programerRepCircle1);
		Label  idProgrammerLabel1  = new Label(programmer1.getStrID());
		       idProgrammerLabel1.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 18));
		       idProgrammerLabel1.setTextFill(Color.GREEN);
		
		Pane stackProgrammerIDCircle1 = new Pane();
		     stackProgrammerIDCircle.getChildren().add(programerRepCircle1);
		     stackProgrammerIDCircle.getChildren().add(idProgrammerLabel1);
		
		programerRepCircle1.setCenterY(100);
		programerRepCircle1.setCenterX(350);
		idProgrammerLabel1.relocate(programerRepCircle1.getCenterX(), programerRepCircle1.getCenterY());
		
		pane.getChildren().add(stackProgrammerIDCircle1);
		
		/*
		 * Programmer 3
		 */
		Programmer programmer2 = new Programmer();  // Create the programmer instance profile
		
		programmer2.setStrID("66JHI");
		
		programmer2.setStrProjectName("SOCIAL NETWORKS PROBLEM");
		programmer2.setStrITExpertise("MACHINE LEARNING USING JAVA");
		programmer2.setEnumLevel(LEVEL.PROFESSIONAL);
		
		Vertex<Programmer> programmerVertex2 = new Vertex<>(programmer2, 1);   // Store the programmer element to the Vertex
		                   vertexCollection.add(programmerVertex2);
		                   vp.add(programmerVertex2);
		                   queueVProgrammer.enqueue(programmerVertex2);
		
		Circle programerRepCircle2 = new Circle(20);
		       cp.add(programerRepCircle2);
		Label  idProgrammerLabel2  = new Label(programmer2.getStrID());
	           idProgrammerLabel2.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 18));
	           idProgrammerLabel2.setTextFill(Color.GREEN);
		
		Pane stackProgrammerIDCircle2 = new Pane();
		     stackProgrammerIDCircle2.getChildren().add(programerRepCircle2);
		     stackProgrammerIDCircle2.getChildren().add(idProgrammerLabel2);
		
		programerRepCircle2.setCenterY(250);
		programerRepCircle2.setCenterX(150);
		idProgrammerLabel2.relocate(programerRepCircle2.getCenterX(), programerRepCircle2.getCenterY());
		
		pane.getChildren().add(stackProgrammerIDCircle2);
		
		/*
		 * Programmer 4
		 */
		Programmer programmer3 = new Programmer();  // Create the programmer instance profile
		
		programmer3.setStrID("89OPI");
		
		programmer3.setStrProjectName("SPEECH RECOGNITION PRAC");
		programmer3.setStrITExpertise("MACHINE LEARNING USING PYTHON");
		programmer3.setEnumLevel(LEVEL.MIDLEVEL);
		
		Vertex<Programmer> programmerVertex3 = new Vertex<>(programmer3, 1);   // Store the programmer element to the Vertex
		                   vertexCollection.add(programmerVertex3);
		                   vp.add(programmerVertex3);
		                   queueVProgrammer.enqueue(programmerVertex3);
		
		Circle programerRepCircle3 = new Circle(20);
		       cp.add(programerRepCircle3);
		Label  idProgrammerLabel3  = new Label(programmer3.getStrID());
	           idProgrammerLabel3.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 18));
	           idProgrammerLabel3.setTextFill(Color.GREEN);
		
		Pane stackProgrammerIDCircle3 = new Pane();
		     stackProgrammerIDCircle3.getChildren().add(programerRepCircle3);
		     stackProgrammerIDCircle3.getChildren().add(idProgrammerLabel3);
		
		programerRepCircle3.setCenterY(500);
		programerRepCircle3.setCenterX(450);
		idProgrammerLabel3.relocate(programerRepCircle3.getCenterX(), programerRepCircle3.getCenterY());
		
		pane.getChildren().add(stackProgrammerIDCircle3);
		
		/*
		 * Programmer 5
		 */
		Programmer programmer4 = new Programmer();  // Create the programmer instance profile
		
		programmer4.setStrID("65GTK");
		
		programmer4.setStrProjectName("SOCIAL NET WITH GRAPH");
		programmer4.setStrITExpertise("JAVA DEVELOPER");
		programmer4.setEnumLevel(LEVEL.MIDLEVEL);
		
		Vertex<Programmer> programmerVertex4 = new Vertex<>(programmer4, 1);   // Store the programmer element to the Vertex
		                   vertexCollection.add(programmerVertex4);
		                   vp.add(programmerVertex4);
		                   queueVProgrammer.enqueue(programmerVertex4);
		
		Circle programerRepCircle4 = new Circle(20);
		       cp.add(programerRepCircle4);
		Label  idProgrammerLabel4  = new Label(programmer4.getStrID());
	           idProgrammerLabel4.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 18));
	           idProgrammerLabel4.setTextFill(Color.GREEN);
		
		Pane stackProgrammerIDCircle4 = new Pane();
		     stackProgrammerIDCircle4.getChildren().add(programerRepCircle4);
		     stackProgrammerIDCircle4.getChildren().add(idProgrammerLabel4);
		
		programerRepCircle4.setCenterY(350);
		programerRepCircle4.setCenterX(450);
		idProgrammerLabel4.relocate(programerRepCircle4.getCenterX(), programerRepCircle4.getCenterY());
		
		pane.getChildren().add(stackProgrammerIDCircle4);
		
		notificationLabel.setText("Five Programmers profile created");
		
		Programmer p = null;
		
		for(int i = 0; i < vp.size(); i++) {    /// Traverse through the vertex collections
			
			for(int j = i + 1; j < vp.size(); j++) {  /// Compare each element with others once
				
				p = vp.get(i).getValue();  /// move to the next Programmer
				
				if(p.compareTo(vp.get(j).getValue()) == 0) { 
					
					System.out.println("Programmer " + i + " compared to programmer " + j + " MATCHED");
					
					Line line = new Line(cp.get(i).getCenterX(), cp.get(i).getCenterY(), cp.get(j).getCenterX(), cp.get(j).getCenterY());
					     lp.add(line);
					pane.getChildren().add(line);   // Add edge to the 
					
					Edge<Programmer> edgeProgrammer = new Edge<>((56 + 4*j), vp.get(i), vp.get(j));  // Created the edge
					                 edgeCollection.add(edgeProgrammer);
					                 ep.add(edgeProgrammer);
					                 
					Label costEdgeLabel  = new Label();
					//pane.getChildren().add(line);   // Add edge to the 
					
					System.out.println(edgeProgrammer.getCost());
					                 
					costEdgeLabel.setText(""  + edgeProgrammer.getCost());
					costEdgeLabel.relocate((line.getStartX() + line.getEndX()) / 2, (line.getStartY() + line.getEndY()) / 2);
					//costEdgeLabel.setFont(Font.font(33));
					
					pane.getChildren().add(costEdgeLabel);
					                 
					// add vertex of programmer collection and edge collection in an undirected graph              
					programmerGraph = new Graph<>(TYPE.UNDIRECTED, vertexCollection, edgeCollection);
					
				} else {
					
					System.out.println("Programmer " + i + " compared to programmer " + j + " DID NOT MATCH");
				}
				
				
				
			}
			
			
		}

		
	}
	
	/**
	 * setLayoutStyle method makes user interface appealing by updating components
	 */
	private void setLayoutStyle() {
		// Setting gaps between node
		flow.setVgap(5);
		grid.setHgap(5);
		grid.setVgap(7);
		
		// Setting the Border
		grid.setBorder(new Border(new BorderStroke(Color.AQUA, Color.AQUA, Color.AQUA, Color.AQUA,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(10), null)));
		exitButton.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, Color.BLUEVIOLET, Color.BLUEVIOLET, Color.BLUEVIOLET,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(5), null)));
		popupAlertExitButton.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, Color.BLUEVIOLET, Color.BLUEVIOLET, Color.BLUEVIOLET,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(5), null)));
		pane.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, Color.BLUEVIOLET, Color.BLUEVIOLET, Color.BLUEVIOLET,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(10), null)));
		flow.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, Color.BLACK, Color.BLUEVIOLET, Color.BLUEVIOLET,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(10), null)));
		hBox.setBorder(new Border(new BorderStroke(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(10), null)));
		hBoxA.setBorder(new Border(new BorderStroke(Color.ROYALBLUE, Color.ROYALBLUE, Color.ROYALBLUE, Color.ROYALBLUE,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(10), null)));
		unfriendTextField.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	               BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, 
	               null, new BorderWidths(5), null)));
		programmerIDTextField.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	               BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, 
	               null, new BorderWidths(5), null)));
		popupExpertFieldTextField.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	               BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, 
	               null, new BorderWidths(3), null)));
		popupProjectNameTextField.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	               BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, 
	               null, new BorderWidths(3), null)));
		notificationButton.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(5), null)));
		friendsOfFriendButton.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(5), null)));
		befriendButton.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(5), null)));
		friendSuggestionButton.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(5), null)));
		unfriendButton.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(5), null)));
		vBoxA.setBorder(new Border(new BorderStroke(Color.RED, Color.RED, Color.RED, Color.RED,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(10), null)));
		hBoxC.setBorder(new Border(new BorderStroke(Color.RED, Color.RED, Color.RED, Color.RED,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(6), null)));
		hBoxD.setBorder(new Border(new BorderStroke(Color.RED, Color.RED, Color.RED, Color.RED,
	               BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, 
	               null, new BorderWidths(6), null)));
		
		programmerIDTextField.setText(null);
		popupProjectNameTextField.setText(null);
		popupExpertFieldTextField.setText(null);
		
		idProgrammerLabel.setTextFill(Color.GREEN);
		notificationLabel.setTextFill(Color.GREEN);
		
		messageDisplayLabel.setUnderline(true);
		
		vBoxA.setStyle("-fx-background-color: BLACK");
		hBoxC.setStyle("-fx-background-color: BLACK");
		hBoxD.setStyle("-fx-background-color: BLACK");
		
		popupAlertLabel.setText("#POP ALERT: Unfriending another programmer is one sided meaning that\n"
				              + " the one who unfriend the other won't be able to see any programming updates \n"
				              + "of the other AND NOT THE OTHER WAY ROUND unless both unfriend each other");
		popupAlertLabel.setTextFill(Color.WHITE);
		popupExpertFieldLabel.setTextFill(Color.WHITE);
		popupProjectNameLabel.setTextFill(Color.WHITE);
		
		alertPopUp.setHeight(10);
		alertPopUp.setWidth(10);
		alertPopUp.setX(400);
		alertPopUp.setY(350);
		projectNamePopUp.setX(300);
		projectNamePopUp.setY(115);
		fieldExpertPopUp.setX(300);
		fieldExpertPopUp.setY(159);
		
		messageDisplayLabel.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 18));
		profileCreationLabel.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 18));
		notificationLabel.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 15));
		unfriendLabel.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 18));
		friendSuggestionButton.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 15));
		notificationButton.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 15));
		befriendButton.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 15));
		unfriendButton.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 15));
		friendsOfFriendButton.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 15));
		profileButton.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 15));
		popupAlertLabel.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 15));
		popupExpertFieldLabel.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 12));
		popupProjectNameLabel.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 12));
		popupAlertExitButton.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 15));
		popupExpertFieldButton.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 12));
		popupProjectNameButton.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 12));
		
		exitButton.prefWidth(300);
		
		exitButton.setGraphic(exitImage);
		
		// Setting Alignment of node
		vBox.setAlignment(Pos.TOP_CENTER);
		hBox.setAlignment(Pos.TOP_CENTER);
		flow.setAlignment(Pos.TOP_CENTER);
		vBoxA.setAlignment(Pos.CENTER);
		
		// Default values
		projectNameBox.setValue("SELF-DRIVING CAR ALGO");
		fieldOfExpertiseBox.setValue("ARTIFICIAL INTELLIGENCE");
		levelEntryBox.setValue(LEVEL.BEGINNER);
		
		// COMBO BOX
		String[] projectNameStrArr   = {"SELF-DRIVING CAR ALGO", "SOCIAL NET WITH GRAPH", "COMPUTER VISION PRAC", "SPEECH RECOGNITION PRAC", "OTHER"};
		String[] expertiseStrArr   = {"ARTIFICIAL INTELLIGENCE", "CYBERSECURITY", "NUERAL NETWORK", "WEB DEVELOPER","JAVA DEVELOPER", "MOBILE DEV.", "SOFTWARE DEV.", "OTHER"};
		ObservableList<String> projectNameList = FXCollections.observableArrayList(projectNameStrArr);
		ObservableList<String> expertiseList = FXCollections.observableArrayList(expertiseStrArr);
		projectNameBox.getItems().addAll(projectNameList);
		fieldOfExpertiseBox.getItems().addAll(expertiseList);
		levelEntryBox.getItems().addAll(LEVEL.BEGINNER, LEVEL.MIDLEVEL, LEVEL.PROFESSIONAL);
		
	}

}
