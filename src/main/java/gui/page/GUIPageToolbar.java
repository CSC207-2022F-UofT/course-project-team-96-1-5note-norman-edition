package gui.page;

import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.beans.*;
import javafx.beans.value.*;
import javafx.beans.property.*;

import java.util.Map;
import java.util.HashMap;


/**
 * GUI element which allows the user to select the active tool.
 */
class GUIPageToolbar extends FlowPane {

    private static final int PADDING = 5;

    private ToggleGroup toggles;
    private Map<Toggle, GUIPageTool> toolMap;
    private ReadOnlyObjectWrapper<GUIPageTool> selectedTool;

    public GUIPageToolbar(GUIPageTool[] tools) {
        super(PADDING, PADDING);

        paddingProperty().setValue(new Insets(PADDING));

        getStyleClass().add("toolbar");

        selectedTool = new ReadOnlyObjectWrapper<>(null);

        toolMap = new HashMap<>();
        toggles = new ToggleGroup();

        toggles.selectedToggleProperty().addListener((o, oldVal, newVal) -> {
            if (newVal != null && newVal != oldVal) {
                selectedTool.setValue(toolMap.get(newVal));
            }
        });

        for (GUIPageTool tool: tools) {
            addTool(tool);
        }
    }

    private void addTool(GUIPageTool tool) {
        ToggleButton toolButton = new ToolButton(tool);
        toggles.getToggles().add(toolButton);
        toolMap.put(toolButton, tool);

        getChildren().add(toolButton);
    }

    public ObservableValue<GUIPageTool> selectedTool() {
        return selectedTool.getReadOnlyProperty();
    }
}


class ToolButton extends ToggleButton {

    public ToolButton(GUIPageTool tool) {
        super(null, tool.getGraphic());

        // Prevent de-selecting a ToolButton by clicking it, i.e. the only way
        // to de-select a tool should be to select another tool.
        setOnAction(e -> {
            if (!isSelected()) {
                select();
            }
        });
    }

    public void select() {
        setSelected(true);
    }
}
