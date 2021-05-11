package fr.serval.application.menu;

import fr.serval.application.menu.settings.SettingsMenu;
import fr.serval.controller.Controller;
import javafx.scene.control.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuController implements Controller {
    private static MenuController instance;

    private final SettingsMenu settingsMenu;

    private final List<Menu> menuList;

    public MenuController() {
        this.settingsMenu = new SettingsMenu();

        this.menuList = new ArrayList<>();

        this.setupMenuList();
    }

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }

        return instance;
    }

    private void setupMenuList() {
        this.menuList.add(this.settingsMenu.getComponent());
    }

    public List<Menu> getMenuItemList() {
        return this.menuList;
    }
}
