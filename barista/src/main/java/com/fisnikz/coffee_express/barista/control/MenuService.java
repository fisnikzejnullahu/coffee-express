package com.fisnikz.coffee_express.barista.control;

import com.fisnikz.coffee_express.barista.boundary.BaristaCommandService;
import com.fisnikz.coffee_express.barista.entity.MenuItem;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class MenuService {

    @Inject
    BaristaCommandService commandService;

    public void addItem(MenuItem menuItem) {
        menuItem.persistAndFlush();
        commandService.menuItemAdded(menuItem);
    }

    public int removeItem(long id) {
        int update = MenuItem.update("removed = 1 where id = ?1", id);

        if (update != 0) {
            commandService.menuItemRemoved(id);
        }
        return update;
    }

    public void addItems(List<MenuItem> menuItems) {
        MenuItem.persist(menuItems);
        commandService.menuItemsAdded(menuItems);
    }
}
