package Model.NetworkRelated;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class Request {
    private RequestType requestType;
    private String action;
    private ArrayList<String> params = new ArrayList<>();

    public Request(String action) {
        this.action = action;
    }
    public Request() {}

    public Request(RequestType requestType, ArrayList<String> params) {
        this.requestType = requestType;
        this.params = params;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }
    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getAction() {
        return action;
    }


    public void setAction(String action) {
        this.action = action;
    }

    public void addData(String value){
        params.add(value);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Request fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Request.class);
    }
}
