package com.thientan.model;

import javax.swing.Icon;

public class ModelMenu {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public ModelMenu(Icon icon, String menuName) {
        this.icon = icon;
        this.menuName = menuName;

    }

    public ModelMenu() {
    }

    private Icon icon;
    private String menuName;
}
