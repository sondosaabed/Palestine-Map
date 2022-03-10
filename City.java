import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class City {
	private String name;
	private int x;
	private int y;
	private String display;
	private ArrayList<Adjacent> adjacents = new ArrayList<>();;
	private RadioButton r;
	private Label label ;
	private ToggleGroup group;
	private int index;

	public City(String city) {
		String[]arr=  city.split(",", city.length());
		String name = arr[0];
		this.name = name;
		int x = Integer.parseInt(arr[1]);
		this.x = x;
		int y = Integer.parseInt(arr[2]);
		this.y = y;
		this.label = new Label(getName());
		this.r  = new RadioButton();
		this.r.setToggleGroup(this.group);
		String display = arr[3];
		this.display = display;

        Image Image = new Image("placeholder1.png");
        ImageView vi = new ImageView(Image);
        vi.setFitHeight(17);
        vi.setFitWidth(16);
            
	   	label.setPadding(new Insets(-5));
	   	r.setGraphic(vi);
	   	label.setGraphic(r);
	   	label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 8));
	   	label.setTextFill(Color.BLACK);
	
	    Tooltip tip = new Tooltip(this.getName());
	    tip.setFont(new Font(16));
	    tip.setStyle("-fx-background-color:grey;");
	    label.setTooltip(tip);
	    r.setTooltip(tip);
         
        if(getDisplay().equalsIgnoreCase("l"))
        	label.setContentDisplay(ContentDisplay.LEFT);
        else if(getDisplay().equalsIgnoreCase("r"))
        	label.setContentDisplay(ContentDisplay.RIGHT);
         
        Image Image0 = new Image("placeholder.png");
        r.setOnAction(o->{
	        ImageView vi0 = new ImageView(Image0);
	        vi0.setFitHeight(17);
	        vi0.setFitWidth(16);
	        label.setTextFill(Color.DARKRED);
		    r.setGraphic(vi0);
		    r.setSelected(true);
        });        
	}
	
	public City() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public ArrayList<Adjacent> getAdjacents() {
		return adjacents;
	}

	public void setAdjacents(ArrayList<Adjacent> adjacents) {
		this.adjacents = adjacents;
	}

	public RadioButton getR() {
		return r;
	}

	public void setR(RadioButton r) {
		this.r = r;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public ToggleGroup getGroup() {
		return group;
	}

	public void setGroup(ToggleGroup group) {
		this.group = group;
	}	
	
	@Override
	public String toString() {
		return "City [name=" + name + ", x=" + x + ", y=" + y + ", display=" + display + ", adjacents=" + adjacents;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
