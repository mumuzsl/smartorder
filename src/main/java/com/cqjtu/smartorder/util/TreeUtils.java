package com.cqjtu.smartorder.util;

import com.cqjtu.smartorder.api.model.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mumu
 * @date 2020/7/26
 */
public class TreeUtils {

    private TreeUtils() {}

    public static List<Menu> toTree(List<Menu> treeList, Long pid) {
        List<Menu> retList = new ArrayList<Menu>();
        for (Menu parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }

    private static Menu findChildren(Menu parent, List<Menu> treeList) {
        for (Menu child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChild() == null) {
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(findChildren(child, treeList));
            }
        }
        return parent;
    }
}
