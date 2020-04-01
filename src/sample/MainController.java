/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button addBtn,
                   removeBtn,
                   clearBtn,
                   addOrderBtn,
                   showBtn;

    @FXML
    private ComboBox<String> styleCombo,
                             sizeCombo;

    @FXML
    private ListView<String> toppingsList,
                             selectedList;

    @FXML
    private ImageView pizzaImage;

    public MainController() {}


}
