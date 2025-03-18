package me.perrysahnow.reefselector;

import edu.wpi.first.shuffleboard.api.data.DataType;
import edu.wpi.first.shuffleboard.api.data.types.StringType;
import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;
import me.perrysahnow.reefselector.widget.PositionSelector;
import me.perrysahnow.reefselector.widget.ReefSelector;

import java.util.List;

/**
 * An example plugin that provides a custom data type (a 2D point) and a simple widget for viewing such data.
 */
@Description(
        group = "me.perrysahnow",
        name = "ReefSelector",
        version = "2025.0.0",
        summary = "A plugin to select which reef level to score on"
)
public final class ReefSelectorPlugin extends Plugin {

//    @Override
//    public List<DataType> getDataTypes() {
//        return List.;
//    }
    @Override
    public List<ComponentType> getComponents() {
        return List.of(
                WidgetType.forAnnotatedWidget(ReefSelector.class),
                WidgetType.forAnnotatedWidget(PositionSelector.class));
    }
}