package com.jenking.spandroid.models.impl;

import com.jenking.spandroid.models.base.MatchModel;
import com.jenking.spandroid.models.base.UserMatchModel;

public class UserMatchDetail extends UserMatchModel {
    public String match_name;
    public String match_time;
    public String match_level;

    public String getMatch_name() {
        return match_name;
    }

    public void setMatch_name(String match_name) {
        this.match_name = match_name;
    }

    public String getMatch_time() {
        return match_time;
    }

    public void setMatch_time(String match_time) {
        this.match_time = match_time;
    }

    public String getMatch_level() {
        return match_level;
    }

    public void setMatch_level(String match_level) {
        this.match_level = match_level;
    }

    @Override
    public String toString() {
        return "UserMatchDetail{" +
                "match_name='" + match_name + '\'' +
                ", match_time='" + match_time + '\'' +
                ", match_level='" + match_level + '\'' +
                '}';
    }
}
