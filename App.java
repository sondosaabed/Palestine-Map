import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/** @author SS
 * In this project The user inputs a file of number of cities and their names, then the program shows the map of Palestine,
 * And gets the shortest Path for the user
 **/

public class App extends Application{
	public static FileChooser fileChooser;
	public static File file;//The input file
	private static int citiesNum=0;
	public static ArrayList<City> citie = new ArrayList<>();
	static Table table = new Table();
	static int mouseClick = 0;
	static int total = 0;
	
	@Override
	public void start(Stage stage) throws Exception {
		//Creating grid pane
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(100,210,100,210));
		pane.setHgap(10.5);
		pane.setVgap(10.5); 
		
		//A color that is repeatedly used in the project
		Color c4=Color.web("#353535");
		BackgroundFill bgf = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
		Background bg = new Background(bgf);
		pane.setBackground(bg);
		
		//Label Greeting User
		Label hello = new Label("Hello user, select your cities file...");
		hello.setFont(Font.font(16));  
		hello.setTextFill(c4);
		pane.add(hello, 0,0);
		
		//This label will be updated by process of file importing
		Label process = new Label("Process");
		process.setFont(Font.font(12));  
		process.setTextFill(Color.RED );
		pane.add(process , 0, 1);
		
		//User button to run the program
		Button run = new Button("show map");
		run.setFont(Font.font(14)); 
		run.setTextFill(c4);
		run.setPrefSize(100, 30);
		run.setStyle("-fx-background-radius: 18, 7;-fx-background-color:lightgreen;");
		
		//User Button to browse file
		Button add = new Button("Browse"); 
		add.setFont(Font.font(14)); 
		add.setStyle("-fx-background-radius: 18, 7;-fx-background-color:lightgreen;");
		add.setTextFill(c4);
		add.setPrefSize(100, 30);
		pane.add(add , 1,1);
		add.setOnAction(d-> { 
			Stage stage3 = new Stage();
    		fileChooser = new FileChooser();
    		file=fileChooser.showOpenDialog(stage3); 
			if(file==(null)) {
				process.setText("You haven't chose a file yet!");
				System.out.println("null pointer");
			} else {
				if(readFile(file)==-1) {
					process.setText("Number of cities doesn't match");
				}else {
					pane.add(run, 0, 1);
					for(int i=0; i<citiesNum;i++) {
						System.out.println(citie.get(i).toString());
					}
					hello.setText("Reday to run");
				}
			}
    	});
		
		run.setOnAction(e -> {
			Stage stage2 = new Stage();
			GridPane pane2 = new GridPane();
			pane2.setPadding(new Insets(20,10,20,10));
		    pane2.setAlignment(Pos.CENTER); 
			pane2.setHgap(10);
			pane2.setVgap(10); 
			pane2.setBackground(bg);
			
			GridPane pane20 = new GridPane();
			pane20.setPadding(new Insets(20,10,20,10));
			pane20.setHgap(17);
			pane20.setVgap(17); 
			pane2.add(pane20,1,0);
			
			//Label Greeting User
			Label mapp = new Label("Palestine Map");
			mapp.setFont(Font.font(20));  
			mapp.setTextFill(c4);
			pane20.add(mapp,0,0);
			
			BackgroundFill bgf1 = new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY,Insets.EMPTY);
			Background bg1= new Background(bgf1 );
			
			Label cityl = new Label("Choose City by:");
			cityl.setFont(Font.font(16));  
			cityl.setTextFill(c4);
			pane20.add(cityl,0,1);
			
			ComboBox<String> choice = new ComboBox<String>();
			choice.setPrefHeight(30);
			choice.setPrefWidth(150);
			choice.setBackground(bg1);
			choice.getItems().add("List");
			choice.getItems().add("Click");
			choice.setValue("Click");
		    pane20.add(choice, 1, 1);
		    
			Label sourcel = new Label("Source:");
			sourcel.setFont(Font.font(16));  
			sourcel.setTextFill(c4);
			pane20.add(sourcel,0,2);

			ComboBox<String> source = new ComboBox<String>();
			source.setPrefHeight(30);
			source.setPrefWidth(150);
			source.setBackground(bg1);
			for(int i=0; i<citiesNum;i++) {
				source.getItems().add(citie.get(i).getName()) ;
			}
			source.setValue(citie.get(0).getName());
		    pane20.add(source, 1, 2);
		    
			//This label will be updated by process of file importing
			Label targetl = new Label("Target:");
			targetl.setFont(Font.font(16));  
			pane20.add(targetl,0,3);
			
			ComboBox<String> target = new ComboBox<String>();
			target.setPrefHeight(30);
			target.setPrefWidth(150);
			target.setBackground(bg1);
			for(int i=0; i<citiesNum;i++) {
				target.getItems().add(citie.get(i).getName()) ;
			}
			target.setValue(citie.get(1).getName());
		    pane20.add(target, 1, 3);

			//This label will be updated by process of file importing
			Label pathl = new Label("Shortest Path:");
			pathl.setFont(Font.font(16));  
			pane20.add(pathl,0,8);
			
			TextArea path = new TextArea();
			path.setPrefHeight(100);
			path.setPrefWidth(150);
			path.setEditable(false);
			pane20.add(path, 1, 8);
			
			//This label will be updated by process of file importing
			Label distl = new Label("Distance:");
			distl.setFont(Font.font(16));  
			pane20.add(distl,0,9);
			
			TextField dist = new TextField();
			dist.setPrefHeight(30);
			dist.setPrefWidth(150);
			dist.setEditable(false);
			pane20.add(dist, 1, 9);
			
			//User button to reset map
			Button reset = new Button("Reset");
			reset.setFont(Font.font(14));
			reset.setPrefSize(100, 30);
    		GridPane.setHalignment(reset, HPos.CENTER);
			reset.setStyle("-fx-background-radius: 10, 5;-fx-background-color:lightgrey;");
			pane20.add(reset,1,4); 
			reset.setOnAction(l -> {
	            Image Image = new Image("placeholder1.png");
				for (City city : citie) {
		             ImageView vi = new ImageView(Image);
		             vi.setFitHeight(17);
		             vi.setFitWidth(16);
		             city.getR().setGraphic(vi);
		             city.getR().setSelected(false);
		             city.getLabel().setTextFill(Color.BLACK);
		             mouseClick=0;
		             path.setText("");
		             dist.setText("");
				}
			}); 
			
			//This label will be updated by process of chosing cities
			Label proces = new Label("Process:");
			proces.setFont(Font.font(16));  
			proces.setTextFill(Color.RED);
			pane20.add(proces,0,6);
    		GridPane.setHalignment(proces, HPos.CENTER);

			Label processl = new Label("...");
			processl.setFont(Font.font(16));  
			processl.setTextFill(Color.RED);
			pane20.add(processl,1,6);
    		GridPane.setHalignment(processl, HPos.CENTER);

			//User button to run the shortest path
			Button run0 = new Button("Run");
			run0.setFont(Font.font(14));
			run0.setPrefSize(100, 30);
			run0.setStyle("-fx-background-radius: 10, 5;-fx-background-color:lightgrey;");
			pane20.add(run0,0,4); 
			run0.setOnAction(l -> {
				ArrayList<City> list = new ArrayList<>(2);
				//run depends on how the user will chose the cities
				
				
				if(choice.getSelectionModel().getSelectedItem().equals("List")) {
					list.add(0,searchArr(source.getSelectionModel().getSelectedItem()));
					list.add(1,searchArr(target.getSelectionModel().getSelectedItem()));
					list.get(0).getR().fire();
					list.get(1).getR().fire();
				}else if(choice.getSelectionModel().getSelectedItem().equals("Click")){
					//Check for clicked cities and add it 
					for(City city : citie) {
						if(city.getR().isSelected()) {
							if(mouseClick==0) {
								list.add(mouseClick,city);
							}else if(mouseClick==1){
								list.add(mouseClick,city);
							}
							mouseClick++;
						}
					}
					if(mouseClick==2) {
						City startObj = list.get(0);
						City targetObj = list.get(1);
						source.setValue(startObj.getName());
						target.setValue(targetObj.getName());
					}else {				  	
					  	reset.fire();
					}
					
				}
					Table table = new Table(citie, list.get(0).getIndex());

					String pat = "";
					String distance = "";
					City targetCity = table.vertices[list.get(1).getIndex()];
					pat = targetCity.getName() + " . " + pat;
					//Search for adjacent which equals to path[targetIndex] to get
					for (int i = 0; i < targetCity.getAdjacents().size(); i++) {
						if (targetCity.getAdjacents().get(i).getCity().getName().equals(table.path[list.get(1).getIndex()].getName())) {
							distance = targetCity.getAdjacents().get(i).getDistance() + " . " + distance;
							total += targetCity.getAdjacents().get(i).getDistance();
						}
					}
					//reach the start point
		            Image Image = new Image("placeholder2.png");
					while (targetCity.getName().equals(table.vertices[list.get(0).getIndex()].getName()) == false) {
						// find the next adjacent according to the given path and change it's graphic
						for (int i = 0; i < targetCity.getAdjacents().size(); i++) {
					             ImageView vi = new ImageView(Image);
					             vi.setFitHeight(17);
					             vi.setFitWidth(16);
							if (targetCity.getAdjacents().get(i).getCity().getName().equals(table.path[list.get(1).getIndex()].getName())) {
								targetCity.getAdjacents().get(i).getCity().getR().setGraphic(vi);
							}
						}
						for (int i = 0; i < table.vertices.length; i++)
							if (table.path[list.get(1).getIndex()] == null)
								break;
							else if (table.path[list.get(1).getIndex()].getName().equals(table.vertices[i].getName())) {

								for (int j = 0; j < table.vertices[i].getAdjacents().size(); j++) {
						             ImageView vi = new ImageView(Image);
						             vi.setFitHeight(17);
						             vi.setFitWidth(16);
									if (targetCity.getName().equals(table.vertices[i].getAdjacents().get(j).getCity().getName())) {
										table.vertices[i].getAdjacents().get(j).getCity().getR().setGraphic(vi);
										break;
									}
								}
								targetCity = table.vertices[i];
								pat = targetCity.getName() + "\n" + " to " + pat;
								for (int i1 = 0; i1 < targetCity.getAdjacents().size(); i1++)
									if (table.path[i] != null)
										if (targetCity.getAdjacents().get(i1).getCity().getName()
												.equals(table.path[i].getName())) {
											distance = targetCity.getAdjacents().get(i1).getDistance() + " + " + distance;
											total += targetCity.getAdjacents().get(i1).getDistance();
										}

								if (targetCity.getName().equals(table.vertices[list.get(0).getIndex()].getName()) == false) {
									targetCity.getR().setTextFill(Color.BLUE);
								}
								list.get(1).setIndex(i);
							}
					}
		            
					Image Imagee = new Image("placeholder.png");
		             ImageView vi = new ImageView(Imagee);
		             vi.setFitHeight(17);
		             vi.setFitWidth(16);
		             ImageView vi0 = new ImageView(Imagee);
		             vi0.setFitHeight(17);
		             vi0.setFitWidth(16);
					list.get(0).getR().setGraphic(vi);
					list.get(1).getR().setGraphic(vi0);

					path.setText(pat);
					dist.setText( total + " KM");
				
				System.out.println(list.toString());
			}); 
			
			//User button to run
			Button cancel = new Button("Exit");
			cancel.setFont(Font.font(14));
			cancel.setPrefSize(100, 30);
    		GridPane.setHalignment(cancel, HPos.CENTER);
			cancel.setStyle("-fx-background-radius: 10, 5;-fx-background-color:lightgrey;");
			pane20.add(cancel,1,10); 
			cancel.setOnAction(l -> Platform.exit()); 
			
			Image map = new Image("map.png");
			ImageView v = new ImageView(map);
			v.setFitWidth(440);
			v.setFitHeight(800);
			v.setStyle("-fx-border-radius: 18, 7;");
			pane2.add(v,0,0);
			
	         GridPane root = new GridPane();  
	         root.setAlignment(Pos.CENTER); 
	         root.setPrefHeight(800);
	         root.setPrefWidth(440);
	         pane2.add(root, 0, 0);

	         for(int y = 0; y <10; y++){
	             for(int x = 0; x <25; x++){
	                 // Create a new num in each Iteration
			       	 Label num = new Label();
		             num.setPrefHeight(100);
		             num.setPrefWidth(400);
	                 num.setStyle("-fx-background-color:transparent;-fx-border-color:transparent;");
	                 GridPane.setRowIndex(num,x);
			       	 GridPane.setColumnIndex(num,y); 
	                 root.getChildren().add(num);
	             }
	         }
	         for(int i=0; i<citiesNum;i++) {
	             citie.get(i).getR().setId(i+""); 
	             citie.get(i).getLabel().setId(i+""); 
	             root.add(citie.get(i).getLabel(),citie.get(i).getX(),citie.get(i).getY());
	         }
	    	//Scene setting
	    	Scene scene = new Scene(pane2); 
	        scene.getStylesheets().add("style.css");

	    	stage2.setTitle("Palestine Map");
			stage2.getIcons().add(new Image("icon.png"));
			stage2.setScene(scene);  
	    	stage2.show(); 
		}); 
		
		//User button to exit
		Button cancel = new Button("Cancel");
		cancel.setFont(Font.font(14)); 
		cancel.setTextFill(c4);
		cancel.setPrefSize(100, 30);
		cancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:lightgreen;");
		pane.add(cancel,2,1); 
		cancel.setOnAction(e -> Platform.exit()); 
		
		//Create Scene
		Scene scene  = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Palestine Map file");
		stage.getIcons().add(new Image("icon.png"));
		stage.show();
	}
    
	public static void main(String[] args) {
		launch(args);
	}
	
	public static int readFile(File input) {
		//This method reads the input file into 2 arrays, LEDs and power sources
		int x=0;
		try {
			 FileReader fileR = new FileReader(input);
		     BufferedReader buffer = new BufferedReader(fileR);
		     
		     citiesNum= Integer.parseInt(buffer.readLine());
		     buffer.close();
		     
			 Scanner sc = new Scanner(input);			 
 
		     //read the 1st line to get the number of power sources
		     sc.nextLine();
		     for(int i=0;i<citiesNum;i++) {
		    	 City city = new City(sc.nextLine());
		    	 city.setIndex(i);
		    	 citie.add(i, city);
		     }
			 while (sc.hasNextLine()) {
		    	 //Safad,Nazrte,20
		    	 String[] adjs = sc.nextLine().split(",");
		    	 Adjacent adj = new Adjacent(searchArr(adjs[1]),Double.parseDouble(adjs[2])); 		    	 
		    	 searchArr(adjs[0]).getAdjacents().add(adj);
			 }   
			sc.close();
		}catch(NumberFormatException t) {
			System.out.println(t);
		}catch (FileNotFoundException e1) {
			System.out.println(e1);
		}catch (IOException e1) {
			System.out.println(e1);
		} catch (InputMismatchException e) {
			System.out.println(e);
		} catch (NullPointerException y) {
			y.printStackTrace();
		}
		return x;
	}

	public static City searchArr(String name) {
		for (City cityl : citie) {
			if(cityl.getName().equals(name)) {
				return cityl;
			}
		}
		return null;
	}
}
