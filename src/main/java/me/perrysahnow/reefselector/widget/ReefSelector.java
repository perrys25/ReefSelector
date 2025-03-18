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


@Description(name = "ReefSelector", summary = "Shows a different image of the reef based on a given value", dataTypes = String.class)
@ParametrizedController("ReefSelector.fxml")
public class ReefSelector extends SimpleAnnotatedWidget<String> {

    @FXML
    private Pane root;

    @FXML
    private ImageView coralReef;

    @FXML
    private void initialize() {
        coralReef.imageProperty().bind(Bindings.createObjectBinding(() -> {
            String data = getData();
            if (data != null && !data.equals("") && false) {
                String reefLevel = getData();
                InputStream steam = getClass().getResourceAsStream("images/Reef_" + reefLevel + " Reef.png");
                if (steam != null) {
                    return new Image(getClass().getResourceAsStream("images/Reef_" + reefLevel + " Reef.png"));
                }
            }
            return new Image(getClass().getResourceAsStream("images/Reef_Empty Reef.png"));
        }, dataProperty()));
        coralReef.setOnMouseClicked(event -> {
            String data = getData();
            if (data != null && !data.equals("")) {
                switch (data) {
                    case "L2":
                        setData("L3");
                        break;
                    case "L3":
                        setData("L4");
                        break;
                    default:
                        setData("L2");
                        break;
                }
            } else {
                setData("L4");
            }
        });
    }

    @Override
    public Pane getView() {
        return root;
    }
}
