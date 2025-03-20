package me.perrysahnow.reefselector.widget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.InputStream;
import java.util.HashMap;


@Description(name = "ReefSelector", summary = "Shows a different image of the reef based on a given value", dataTypes = String.class)
@ParametrizedController("ReefSelector.fxml")
public class ReefSelector extends SimpleAnnotatedWidget<String> {

    @FXML
    private Pane root;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView coralReef;

    private HashMap<String, Image> reefImages = new HashMap<>();

    @FXML
    private void initialize() {
        reefImages.put("L2", new Image(getClass().getResourceAsStream("images/Reef_L2 Reef.png")));
        reefImages.put("L3", new Image(getClass().getResourceAsStream("images/Reef_L3 Reef.png")));
        reefImages.put("L4", new Image(getClass().getResourceAsStream("images/Reef_L4 Reef.png")));
        reefImages.put("Empty", new Image(getClass().getResourceAsStream("images/Reef_Empty Reef.png")));

        coralReef.setPreserveRatio(true);
        coralReef.fitWidthProperty().bind(borderPane.widthProperty());
        coralReef.fitHeightProperty().bind(borderPane.heightProperty());

        coralReef.imageProperty().bind(Bindings.createObjectBinding(() -> {
            String data = getData();
            if (data != null && !data.equals("")) {
                if (reefImages.containsKey(data)) {
                    return reefImages.get(data);
                }
            }
            return reefImages.get("Empty");
        }, dataProperty()));
        coralReef.setOnMouseClicked(event -> {
            String data = getData();
            if (data != null && !data.equals("")) {
                if (!event.isShiftDown()) {
                    raiseLevel(data);
                } else {
                    lowerLevel(data);
                }
            } else {
                setData("L4");
            }
        });

        coralReef.setOnKeyPressed(event -> {
            String data = getData();
            char character = event.getCharacter().charAt(0);
            if (character >= '2' && character <= '4') {
                setData("L" + character);
            } else {
                if (data != null && !data.equals("")) {
                    if (event.getCode() == KeyCode.UP) {
                        raiseLevel(data);
                    } else if (event.getCode() == KeyCode.DOWN) {
                        lowerLevel(data);
                    }
                }
            }
        });
    }

    private void lowerLevel(String data) {
        switch (data) {
            case "L2":
                setData("L4");
                break;
            case "L3":
                setData("L2");
                break;
            default:
                setData("L3");
                break;
        }
    }

    private void raiseLevel(String data) {
        switch (data) {
            case "L2":
                setData("L3");
                break;
            case "L4":
                setData("L2");
                break;
            default:
                setData("L4");
                break;
        }
    }

    @Override
    public Pane getView() {
        return root;
    }
}
