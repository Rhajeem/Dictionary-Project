import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.sun.istack.internal.Interned;
import com.sun.istack.internal.Nullable;
import com.sun.prism.paint.Color;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import com.sun.prism.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.scenicview.ScenicView;

import java.io.File;
import java.io.IOException;

import static java.lang.Math.abs;


/*Created by:
 * Rhajeem Crawford - 1501389
 * Santana Broderick - 1500711
 * Jahvier Small - 1501224
 */


public class Main extends Application 
{
    
    private static Tree tree = new Tree();
    private static LinkedList dlc = new LinkedList();
    private static String label, word, partOfSpeech, meaning;
    private Stage stage1 = new Stage();
    private static Stage option;
    private Stage add = new Stage();
    private FileProcess fileProcess = new FileProcess();
    private static boolean waiting = true;
    private static Thread task;
    private XYChart.Series treeSeries = new XYChart.Series();
    private XYChart.Series linkListSeries = new XYChart.Series();
    
    @FXML
    private Label linkListLabel = new Label(), treeLabel = new Label(), labelError = new Label(), labelNew;
    @FXML
    private TreeView treeView1 = new TreeView(), treeView2 = new TreeView();
    @FXML
    private TextField textField = new TextField(), posNew = new TextField(), wordNew = new TextField();
    @FXML
    private TextArea meaningNew = new TextArea();
    @FXML
    private Rectangle rect;
    @FXML
    private Notifications notif = Notifications.create();
    @FXML
    private ToggleButton addBtn, validateBtn;
    @FXML
    private AnchorPane anchor, errorPane, optionPane;
    @FXML private static SplitPane page;
    @FXML private BarChart bc = new BarChart<>(new CategoryAxis(), new NumberAxis(1,25,1), null);
    @FXML private CategoryAxis xaxis = new CategoryAxis();
    @FXML private NumberAxis yaxis = new NumberAxis(1,5,1);
    
    public static void main(String[] args) {
        launch(args);
    
    
    }
    
    @Override
    public void start(Stage a) throws Exception {
    
    
    
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        this.stage1.setScene(new Scene(root));
        stage1.getIcons().add(new Image("hold.png"));
        stage1.show();
    
    }
    
    @FXML
    public void initialize() {
        initialize(null);
    }
    
    @FXML
    public void initialize(ActionEvent event) {
        
        
    }
    
  
    
    public void load() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(this.stage1);
            
            if (file == null) {
                linkListLabel.setText("no file selected");
                treeLabel.setText("no file selected");
                
            }
            
            long j = fileProcess.parse(this.tree, file);
            long k = fileProcess.parse(this.dlc, file);
            
            treeLabel.setText("loading took " + j + "ms");
            linkListLabel.setText("loading the list took " + k + "ms");
    
          
            addToTree();
            
            updateGraph(j,k,1);
        } catch (Exception e) {
            treeLabel.setText("no file was selected");
            linkListLabel.setText("no file was selected");
        }
    }
    
    private void updateGraph(long j, long k, int type){
    
        
        xaxis.setLabel("Methods");
        yaxis.setLabel("Times");
        treeSeries.setName("Tree");
        linkListSeries.setName("link List");
        
        
        bc.setLegendVisible(false);
        bc.setAlternativeRowFillVisible(true);
        bc.setBarGap(0.0);
        bc.setTitleSide(Side.TOP);
        bc.setLegendSide(Side.BOTTOM);
        bc.impl_updatePeer();
        
        switch (type){
            case 1:
                treeSeries.getData().add(new XYChart.Data("parse",abs(j)));
                linkListSeries.getData().add(new XYChart.Data<>("parse",abs(k)));
                break;
            case 2:
                treeSeries.getData().add(new XYChart.Data("search",abs(j)));
                linkListSeries.getData().add(new XYChart.Data<>("search",abs(k)));
                break;
            case 3:
                treeSeries.getData().add(new XYChart.Data("sort",abs(j)));
                linkListSeries.getData().add(new XYChart.Data<>("sort",abs(k)));
                break;
            default:
                break;
    
        }
    
        
        if(bc.getData().contains(treeSeries)) {
           
            bc.requestLayout();
            return;
           
        }
       
           bc.getData().addAll(treeSeries, linkListSeries);
       
    }
    
    private void addToTree() {
        
        TreeItem root = new TreeItem<>(tree.getCount() + " Definitions");
        root.setExpanded(true);
        root = tree.searchOrder(this.tree.getRoot(), "", root);
        root.setValue(tree.getCount() + " Definitions");
        
        TreeItem root1 = new TreeItem<>(tree.getCount() + " Definitions");
        root1.setExpanded(true);
        root1 = dlc.populateList(root1);
        root1.setValue(dlc.getSize() + " Definitions");
        
        
        treeView1.setRoot(root);
        treeView2.setRoot(root1);
    
        treeView1.requestLayout();
        treeView2.requestLayout();
        
        
    }
    
    private boolean testEmpty(String a) {
        if (textField.getText().equals("") || a.equals("Word already exists")) {
            
            labelError.setText(a);
            TranslateTransition openNav = new TranslateTransition(new Duration(350), errorPane);
            openNav.setToY(0);
            openNav.play();
            
            return true;
        }
        return false;
    }
    
   
    
    public void setOptionLabel() {
        labelNew.setText('"' + word + '"');
    }
    
    public void changeView(){
       page.setDividerPosition(0,0.0);
       try {
           option.show();
       }catch(IllegalStateException e){
           
       }
        
        
    }
    

    public void closeErrorPane() {
        TranslateTransition openNav = new TranslateTransition(new Duration(350), errorPane);
        openNav.setToY(88);
        openNav.play();
    }
    
    public void search() {
        if (testEmpty("please enter word to search for")){
            textField.requestFocus();
            
            return;
        }
        
        
        TreeItem root = new TreeItem<>(tree.getCount() + " Definitions");
        root.setExpanded(true);
        FpTimer h = new FpTimer();
        root = tree.searchOrder(tree.getRoot(), textField.getText(), root);
        TreeItem root1 = new TreeItem<>(tree.getCount() + " Definitions");
        root1.setExpanded(true);
        FpTimer g = new FpTimer();
        root1 = dlc.searchReturn(textField.getText(), root1);
         
        updateGraph(h.getDiff(),g.getDiff(),2);
        
        treeView1.setRoot(root);
        treeView2.setRoot(root1);
        
        
    }
    
    public void update() throws Exception {
        if (textField.getText().equals("")) {
            if (treeView1.isShowRoot())
                addToTree();
        }
    }
    
    
    
    public void add(ActionEvent event) {
       
        
        if (testEmpty("please enter word to add")) {
            textField.setFocusTraversable(true);
            return;
        }
        
        if (tree.exists(tree.getRoot(), textField.getText(), new TreeItem())) {
            //word already exists
            testEmpty("Word already exists");
            
        } else {
            
            word = textField.getText();
            
            openAdd(event);
            
            
        }
    }
    
    private void openAdd(ActionEvent event) {
        try {
            
           option = new Stage();
            page = FXMLLoader.load(getClass().getResource("decision.fxml"));
           
           if(event.getSource()==validateBtn)
                page.setDividerPosition(0,1.0);
           else
               page.setDividerPosition(0,0.0);
    
    
            option.setScene(new Scene(page));
             option.initModality(Modality.APPLICATION_MODAL);
             
             try {
             
             }catch(NullPointerException e){
                 
             }
    
            wordNew.setText(word);
    
            option.showAndWait();
          
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    public void closeAdd(){
        option = (Stage) meaningNew.getScene().getWindow();
        
        option.close();
        return;
        
    }
    
    public void addUp() {
        
        if (posNew.getText().equals("")) {
            posNew.setPromptText("please enter part of speech");
            return;
        }
        if (meaningNew.getText().equals("")) {
        	meaningNew.setPromptText("Please enter definition");
            return;
        }
    
        
        partOfSpeech = posNew.getText();
        meaning = meaningNew.getText();
      
        
        closeAdd();
        
        
        Data info = new Data(word, partOfSpeech, meaning);
        long h = tree.insert(info);
        long g = dlc.insert(info);
        addToTree();
        stage1.requestFocus();
        treeView1.requestLayout();
        treeView2.requestLayout();
    
        if(!option.isShowing())
        textField.clear();
    
    
        treeLabel.setText("adding a word took " + abs(h) + "ms");
        linkListLabel.setText("adding a word took " + abs(g) + "ms");
        
      
    }
    
   
    public synchronized void validate(ActionEvent event) {
        if (testEmpty("please enter text to validate")) {
            textField.setFocusTraversable(true);
    
            return;
        }

        String a = textField.getText();
        int i = 0;
        String b = "";

        while (true) {

            if (a.charAt(i) == ' ' && i == 0) {
                i++;
                continue;
            }

            b = b + a.charAt(i);
            i++;

            if (i + 1 == a.length()) {
                
                b = b + a.charAt(i);
                i++;

            }

            if (i == a.length() || a.charAt(i) == ' ') {

                if (i != a.length())
                    i++;


                String labelString = b;

                if (tree.exists(tree.getRoot(), labelString, new TreeItem()) && dlc.exist(labelString)) {
                    //exists already
                } else {
                    word = labelString;
                    openAdd(event);
                
                }


                b = "";

            }

            if (i >= a.length())
                return;
        }

    }
    
   
    public void sort() {
        
        
            if (dlc.getSize() == 0) {
            linkListLabel.setText("nothing to sort");
            return;
        }
        treeLabel.setText("tree list is automatically sorted");
        long g = dlc.sort(dlc.getHead(), dlc.getSize() - 1);
        linkListLabel.setText("linkedList took " + abs(g) + "ms to sort");
        addToTree();
        updateGraph(0,abs(g),3);
        
    }
    
    
    public void drag() {
        
    }
    
}
