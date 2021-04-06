package com.fitternity.urbanfresh.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UrbanFreashResponse
{
    @Expose
    private Boolean success;
    @Expose
    private List<Component> components=null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "UrbanFreashResponse{" +
                "success=" + success +
                ", components=" + components +
                '}';
    }
}
