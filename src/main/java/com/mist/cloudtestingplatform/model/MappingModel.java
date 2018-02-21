package com.mist.cloudtestingplatform.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prophet on 2018/2/21.
 */
public class MappingModel {

    private String name;

    private List<Child> children;

    public MappingModel() {
        children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public static class Child {

        private String name;

        public Child(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
