package com.saintsrobotics.webinterfacebot.motors;
import org.json.JSONObject;

import com.saintsrobotics.util.dash.WebDashboard;
public class MotorsWebDashboard{
    private JSONObject vals;
    private WebDashboard web;
    public MotorsWebDashboard(WebDashboard web){
        this.web = web;
        vals = web.values.getJSONObject("client").getJSONObject("motors");
    }
    public Motor get(String motor){
        return Motors.get(vals.getJSONObject(motor).getInt("port"),vals.getJSONObject(motor).getBoolean("inverted"));
    }
    public void refresh(){
        Motors.stopAll();
        vals = web.values.getJSONObject("client").getJSONObject("motors");
    }
}