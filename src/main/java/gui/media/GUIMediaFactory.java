package gui.media;

import app.media.*;
import javafx.scene.media.MediaView;


/**
 * Simple factory which creates the appropriate GUIMedia object for a given
 * Media object.
 */
public class GUIMediaFactory {

    private GUIMediaFactory() {}

    /**
     * Instatiate the correct GUIMedia sub-class for the given Media object.
     * <p>
     * When we load a Media object from storage, we don't yet know specifically
     * what type it is, but each type of Media has a different GUI representation,
     * so we need to check the type of the Media and then create an instance of
     * the correct sub-class of GUIMedia.
     */
    public static GUIMedia getFor(Media media) throws Exception {
        if (media instanceof PenStroke) {
            return new GUIPenStroke((PenStroke) media);
        } if (media instanceof MediaAudio) {
            System.out.println(media.getName());
            if (media.getName().substring(media.getName().length() - 4).equals(".mp4"))   {
                return new GUIVideo((MediaAudio) media);
            }   else {
                return new GUIAudio((MediaAudio) media);
            }
        } if (media instanceof  MediaHyperlink) {
            return new GUIHyperlink((MediaHyperlink) media);
        } else {
            throw new Exception("No appropriate GUIMedia class for `" + media + "`.");
        }
    }
}
