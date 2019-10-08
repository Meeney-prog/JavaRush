package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class TextEditMenuListener implements MenuListener {
    private View view;

    public TextEditMenuListener(View view) {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu menu = (JMenu) e.getSource();
        Component[] components = menu.getMenuComponents();
        boolean isEnable = view.isHtmlTabSelected();
        for (Component component : components) {
            component.setEnabled(isEnable);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {    }

    @Override
    public void menuCanceled(MenuEvent e) {    }
}
