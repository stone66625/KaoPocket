package tw.kao.pocket.ui.travel;



import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class TravelResponse {
    //第一階JSONObject
    @SerializedName("data")
    public data data;

}

//第二階 JSONObject
class data {

    //第二階 繼續往下 第三階JSONObject
    @SerializedName("XML_Head")
    public XML_Head XML_Head;

}

//第三階JSONObject
class XML_Head {
    //第三階 繼續往下 第四階JSONObject
    @SerializedName("Infos")
    public Infos Infos;
}

//第四階JSONObject
class Infos {
    //第四階 JSONObject 裡面的 Info 是一個JsonArray
    //繼續往下 第五階
    @SerializedName("Info")
    public ArrayList<Info> Info = new ArrayList<Info>();
}

//第五階
class Info {

    //第五階裡面 我要的的東西 name,tel,add,px,py,picture1;
    @SerializedName("Name")
    public String Name;
    @SerializedName("Opentime")
    public String Opentime;
    @SerializedName("Tel")
    public String Tel;
    @SerializedName("Add")
    public String Add;
    @SerializedName("Px")
    public String Px;
    @SerializedName("Py")
    public String Py;
    @SerializedName("Picture1")
    public String Picture1;
    @SerializedName("Ticketinfo")
    public String Ticketinfo;

}