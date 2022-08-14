package top.xiaorang.simple.common.utils;

import top.xiaorang.simple.common.pojo.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树形结构工具类
 *
 * @author liulei
 * @version 1.0
 * @since 2022/8/9 2:51
 */
public class TreeUtil {
    /**
     * 根据所有树节点列表，生成含有所有树形结构的列表
     *
     * @param nodes 树形节点列表
     * @param <T>   节点id类型
     * @param <E>   节点类型
     * @return 树形结构列表
     */
    public static <T, E extends TreeNode<T, E>> List<E> generateTree(List<E> nodes) {
        if (nodes == null || nodes.isEmpty()) return new ArrayList<>();
        Map<T, E> map = new HashMap<>();
        nodes.forEach(node -> map.put(node.id(), node)); // 为了使查询变快，构建一个map存储 O(1)
        List<E> result = new ArrayList<>();
        nodes.forEach(node -> {
            E parent = map.get(node.parentId());
            if (parent != null) {
                List<E> children = parent.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(node);
            } else {
                result.add(node);
            }
        });
        return result;
    }
}
