package app.media_managers;
import app.MediaCommunicator;
import app.media.MediaAudio;
import javafx.util.Duration;
import storage.FileLoaderWriter;
import storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;


public class AudioModifier implements MediaManager {
    /**
    * Manages creation/interactions on MediaAudio
     * <p>
     * Class stores instances of a MediaAudio to modify, the MediaCommunicator of the page it is on, and may store
     * a Duration that should be added or removed from instance MediaAudio
    */

    private Duration timestamp;
    private MediaAudio audio;
    private MediaCommunicator communicator;
    private String[] types;
    private String fileType;

    /** Allows the user to select an audio file to add to the page
     * @throws Exception when user selected file fails to load
     */
    @Override
    public void addMedia() throws Exception{
        //Loading raw audio data based on user selection
        Storage fileManager = new FileLoaderWriter();
        StringBuilder acceptedExtensions = new StringBuilder("(" + types[0]);
        for(int i = 1; i < types.length; i++)    {
            acceptedExtensions.append(", ").append(types[i]);
        }
        acceptedExtensions.append(")");

        HashMap<String, byte[]> fileData = fileManager.readFile(types, fileType + acceptedExtensions);
        if (fileData != null)    {
            String fileName = (String) (fileData.keySet().toArray())[0];
            MediaAudio audio = new MediaAudio(fileName.substring(0, fileName.length() - 4)
                    , 200, 200, 200, 200, fileData.get(fileName), new ArrayList<Duration>(),
                    fileType);
            //Giving the audio an ID then adding it to the page
            this.communicator.updateMedia(audio);
            }
    }

    /**
     * Allowing timestamps to either be added or removed from referenced parameter audio
     * <p>
     * If this class' timestamp attribute already exist in the audio attribute, it is removed from audio. Otherwise,
     * it is added
     * @throws Exception when MediaCommunicator fails to update referenced audio
     */
    @Override
    public void modifyMedia() throws Exception{
        //Either adds or removes timestamps from the audio
        if (audio.getTimestamps().contains(timestamp))  {
            audio.getTimestamps().remove(timestamp);
        } else {
            audio.getTimestamps().add(timestamp);
        }
        communicator.updateMedia(audio);
    }

    @Override
    public void searchMedia() {

    }

    public void addTimeStamp(Duration givenTimeStamp){

        this.timestamp = givenTimeStamp;
    }

    public void setAudio(MediaAudio audio) {
        this.audio = audio;
    }

    public void setCommunicator(MediaCommunicator communicator) {
        this.communicator = communicator;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
