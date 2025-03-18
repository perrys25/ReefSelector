package me.perrysahnow.reefselector.widget;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.InputStream;

@Description(name = "PositionSelector", summary = "Shows a different image of the selected position based on a value", dataTypes = String.class)
@ParametrizedController("PositionSelector.fxml")
public class PositionSelector extends SimpleAnnotatedWidget<String> {

    @FXML
    private Pane root;

    @FXML
    private ImageView reefPositions;

    @FXML
    private void initialize() {
        reefPositions.imageProperty().bind(Bindings.createObjectBinding(() -> {
            String data = getData();
            if (data != null && !data.equals("")) {
                String pose = getData();
                InputStream steam = getClass().getResourceAsStream("images/Reef_Reef Position " + pose + ".png");
                if (steam != null) {
                    return new Image(steam);
                }
            }
            return new Image(getClass().getResourceAsStream("images/Reef_Reef Positions Empty.png"));
        }, dataProperty()));
    }

    @Override
    public Pane getView() {
        return root;
    }
}
