/**
 * Class that handles JS requests in java code.
 */
package rs.prosmart.JSHandlers;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.PromptData;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class that handles JS requests in java code.
 * @author Sinisa
 */
public class JSHandlers {
    /**
     * Handles window.alert() call by displaying a dialog.
     * @param e 
     */
    public static void alertHandler(WebEvent<String> e) {
       Stage stage = new Stage();
       stage.setTitle("test");
       VBox root;  
       Object el = e.getSource();
       

        String data = e.getData();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);               
        } catch (JSONException ex) {
            Logger.getLogger(JSHandlers.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(jsonObject == null && data.contains(":/"))
        {
            try {
                 WebView myView = new WebView();
                 myView.getEngine().load(new URL(data).toExternalForm());
                 root = new VBox(myView);
                 root.setPrefHeight(400);
                 root.setPrefWidth(600);
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

                 // Automatically close dialog after 5 seconds.
                 PauseTransition delay = new PauseTransition(Duration.seconds(5));
                 delay.setOnFinished( event -> stage.close() );
                 delay.play();

            } catch (MalformedURLException ex) {
                Logger.getLogger(JSHandlers.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if(jsonObject == null)
            {
                Label label = new Label(data);
                Button btnOk = new Button("OK");
                btnOk.setOnAction(evt -> {
                    stage.close();
                });
                root = new VBox(20, label, btnOk);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.showAndWait();
            } else {
                try {
                    String poruka = jsonObject.get("msg").toString();
                    int left = jsonObject.getInt("left");
                    int top = jsonObject.getInt("top");

                    double x = (left - 300) <= 0 ? 0 : left - 300;
                    double y = (top - 200) <= 0 ? 0 : top - 200;
                    
                    WebView myView = new WebView();
                    myView.getEngine().load(new URL(poruka).toExternalForm());
                    root = new VBox(myView);
                    root.setPrefHeight(400);
                    root.setPrefWidth(600);
                    
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setX(x);
                    stage.setY(y);
                    stage.initStyle(StageStyle.UNDECORATED);
                    
                    stage.show();
                    

                    // Automatically close dialog after 5 seconds.
                    PauseTransition delay = new PauseTransition(Duration.seconds(3));
                    delay.setOnFinished( event -> stage.close() );
                    delay.play();
                    
                } catch (JSONException ex) {
                    Logger.getLogger(JSHandlers.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(JSHandlers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

         }
              
    }
    
    /**
     * Returns a Callback to handle window.prompt() call by displaying a dialog.
     * @return 
     */
    public static Callback<PromptData, String> getPromptHandler() {
       Callback<PromptData, String> handler = pData -> {
           Stage stage = new Stage();
           stage.setTitle("Prompt");
           
           Label msgLbl = new Label(pData.getMessage());
           TextField dataFld = new TextField();
           dataFld.setText(pData.getDefaultValue());
           Button okBtn = new Button("OK");
           okBtn.setOnAction(e -> stage.close());
           
           VBox root = new VBox(20, msgLbl, dataFld, okBtn);
           root.setAlignment(Pos.CENTER);
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.showAndWait();
           
           String userData = dataFld.getText();
           return userData;
       };
       
       return handler;
    }
    
    /**
     * Returns a Callback to handle window.open() call by displaying the popup
     * in a separate window using a separate WebEngine.
     * @return 
     */
    public static Callback<PopupFeatures, WebEngine> getPopupHandler() {
       Callback<PopupFeatures, WebEngine> handler = pFeatures -> {
           Stage stage = new Stage();
           stage.setTitle("Popup");
           
           WebView popupView = new WebView();
           VBox root = new VBox(popupView);
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           
           return popupView.getEngine();
       };
       
       return handler;
    }
    
    public static Callback<String, Boolean> getConfirmHandler() {
        Callback<String, Boolean> handler = msg -> {
            Stage stage = new Stage();
            stage.setTitle("Confirm");
            
            Label msgLbl = new Label(msg);
            Button okBtn = new Button("OK");
            okBtn.setOnAction(e -> {
                okBtn.getProperties().put("userPressed", true);
                stage.close();
            });
            
            Button cancelBtn = new Button("Cancel");
            cancelBtn.setOnAction(e -> stage.close());
            
            HBox buttons = new HBox(20, okBtn, cancelBtn);
            buttons.setAlignment(Pos.CENTER);
            
            VBox root = new VBox(20, msgLbl, buttons);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            
            Boolean userSelection = (Boolean)okBtn.getProperties().get("userPressed");
            userSelection = (userSelection != null);
            return userSelection;
        };
        
        return handler;
    }
    
    
}
