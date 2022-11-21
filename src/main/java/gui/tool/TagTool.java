package gui.tool;

import app.controllers.ToolBarController;
import gui.media.GUIMedia;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javafx.event.*;
import javafx.scene.input.MouseEvent;


public class TagTool implements Tool{
    private final TagSettings settings;
    private final HandlerMethod<?>[] handlers;
    public Button tagButton = new Button("Tag");
    public GUIMedia<?> media;
    public TagTool(){
        settings = new TagSettings();
        this.handlers = new HandlerMethod<?>[]{new HandlerMethod<>(MouseEvent.MOUSE_CLICKED, this::getMedia)};
    }

    @Override
    public String getName(){
        return "Tag";
    }
    @Override
    public HandlerMethod<?>[] getHandlerMethods() {
        return handlers;
    }

    @Override
    public FlowPane getSettingsGUI(){
        return settings;
    }

    public void getMedia(MouseEvent e){
        if (e.getButton() == MouseButton.PRIMARY){
            e.consume();
            EventTarget clicked = e.getTarget();
            if (clicked instanceof GUIMedia) {
                media = (GUIMedia<?>) clicked;
                tagButton.setDisable(false);
            }
            else{
                tagButton.setDisable(true);
            }
        }
    }

    class TagSettings extends FlowPane{
        public TagSettings(){

            TextField createTag = new TextField();
            tagButton.setDisable(true);

            tagButton.setOnMouseClicked(TagTool.this::getMedia);

            tagButton.setOnAction(e->{
                ToolBarController tb = new ToolBarController();
                tb.tag(createTag, media);

            });

            int PADDING = 5;
            HBox tagging = new HBox(PADDING, new Label("Tag Name"), createTag, tagButton);
            tagging.setAlignment(Pos.CENTER_LEFT);
            HBox.setHgrow(tagging, Priority.ALWAYS);

            getChildren().addAll(tagging);

        }
    }
}
