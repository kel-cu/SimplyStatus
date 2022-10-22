package og.__kel_.simplystatus.configs.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {

    public boolean viewStatic = true;
    public boolean viewOffHand = false;
    public boolean viewRPC = true;
    public boolean viewUsername = true;
    public boolean bedrock = false;
    public boolean cringe = false;
    public boolean showTime = true;
    public boolean changeStatusNameInMinecraft = false;
    public boolean viewVoice = false;
    public boolean viewMusicListening = false;
    public boolean viewReplayMod = false;
    public boolean showAvatar = false;
    public Client(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        try{
            this.viewStatic = json.getBoolean("static");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.viewOffHand = json.getBoolean("offhand");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.viewRPC = json.getBoolean("rpc");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.viewUsername = json.getBoolean("username");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.bedrock = json.getBoolean("bedrock");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.cringe = json.getBoolean("cringe");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.showTime = json.getBoolean("showTime");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.changeStatusNameInMinecraft = json.getBoolean("title");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.viewVoice = json.getBoolean("voice");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.viewMusicListening = json.getBoolean("music");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.viewReplayMod = json.getBoolean("replay");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try{
            this.showAvatar = json.getBoolean("avatar");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
