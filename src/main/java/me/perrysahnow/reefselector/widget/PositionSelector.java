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

@Description(name = "PositionSelector", summary = "Shows a different image of the selected position based on a value", dataTypes = String.class)
@ParametrizedController("PositionSelector.fxml")
public class PositionSelector extends SimpleAnnotatedWidget<String> {

    @FXML
    private Pane root;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView reefPositions;

    private HashMap<String, Image> reefImages = new HashMap<>();

    @FXML
    private void initialize() {

        for (char c = 'A'; c <= 'L'; c++) {
            String letter = String.valueOf(c);
            reefImages.put(letter, new Image(getClass().getResourceAsStream("images/Reef_Reef Position " + letter + ".png")));
        }
        reefImages.put("Empty", new Image(getClass().getResourceAsStream("images/Reef_Reef Positions Empty.png")));

        reefPositions.imageProperty().bind(Bindings.createObjectBinding(() -> {
            String data = getData();
            if (data != null && !data.equals("")) {
                if (reefImages.containsKey(data)) {
                    return reefImages.get(data);
                }
            }
            return reefImages.get("Empty");
        }, dataProperty()));

        reefPositions.setPreserveRatio(true);
        reefPositions.fitWidthProperty().bind(borderPane.widthProperty());
        reefPositions.fitHeightProperty().bind(borderPane.heightProperty());

        reefPositions.setOnMouseClicked(event -> {
            String data = getData();
            if (data != null && !data.equals("")) {
                char letter = data.charAt(0);
                if (!event.isShiftDown()) {
                    if (letter == 'L') {
                        setData("A");
                    } else {
                        setData(String.valueOf((char) (letter + 1)));
                    }
                } else {
                    if (letter == 'A') {
                        setData("L");
                    } else {
                        setData(String.valueOf((char) (letter - 1)));
                    }
                }
            } else {
                setData("A");
            }
        });

        reefPositions.setOnKeyPressed(event -> {
            String data = getData();
            char character = event.getCharacter().charAt(0);
            if (character >= 'A' && character <= 'L') {
                setData(String.valueOf(character));
            } else {
                if (data != null && !data.equals("")) {
                    char letter = data.charAt(0);
                    if (event.getCode() == KeyCode.LEFT) {
                        if (letter == 'L') {
                            setData("A");
                        } else {
                            setData(String.valueOf((char) (letter + 1)));
                        }
                    } else if (event.getCode() == KeyCode.RIGHT) {
                        if (letter == 'A') {
                            setData("L");
                        } else {
                            setData(String.valueOf((char) (letter - 1)));
                        }
                    }
                }
            }
        });
    }

    @Override
    public Pane getView() {
        return root;
    }
}
