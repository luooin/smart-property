package com.es.estatemanagement.util;


import com.es.estatemanagement.domain.EstateRight;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    //构建树形结构
    public static List<EstateRight> bulidTree(List<EstateRight> estateRightsList) {
        //创建集合封装最终数据
        List<EstateRight> trees = new ArrayList<>();
        //遍历所有菜单的集合
        for (EstateRight estateRight : estateRightsList) {
            if (estateRight.getRightType().equals("Folder")) {
                trees.add(findChildren(estateRight, estateRightsList));
            }
        }
        return trees;
    }

    //从根节点进行递归查询，查询子节点
    //判断id = parentid是否相同，如果是子节点，进行数据的封装
    private static EstateRight findChildren(EstateRight estateRight, List<EstateRight> estateRightsList) {
        //数据初始化
        estateRight.setChildren(new ArrayList<EstateRight>());
        //遍历递归进行查找
        for (EstateRight estateRights : estateRightsList) {
            if (estateRight.getRightCode().equals(estateRights.getRightParentCode())) {
                if (estateRight.getChildren() == null) {
                    estateRight.setChildren(new ArrayList<EstateRight>());
                }
                estateRight.getChildren().add(findChildren(estateRights, estateRightsList));
            }
        }
        return estateRight;
    }


}
