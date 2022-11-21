package gui.media;
import app.media.TextBox;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
public class GUIHyperlinkBox extends GUITextBox{
    private Text text;
    private String link;

    private void setInitialValues() {
        this.text = new Text("");
        this.link = new String ("");
        this.text.setUnderline(true);

        getChildren().clear();
        getChildren().addAll(this.text);
    }

    public GUIHyperlinkBox(Point2D point, String text, String link, Color colour ) {
        super(new TextBox(point.getX(), point.getY(), text, link, colour.toString()));

        setInitialValues();
        setText(text);
        setLink(link);
        this.text.setFill(colour);
    }

    // copying methods from superclass because for some reason an error runs if these aren't here
    private void setText(String textIn) {
        this.text.setText(textIn); }

    private void setLink(String givenLink) {
        this.link = givenLink;
    }

    public String getText(){
        return this.text.getText();
    }

    public String getLink(){

        return this.link;
    }
    public void updateHyperLink(String textIn, String givenLink) {
        this.text.setText(textIn);
        this.link = givenLink;
    }


}
