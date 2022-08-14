package top.xiaorang.simple.common.pojo;

import java.util.List;

/**
 * @author liulei
 * @version 1.0
 * @since 2022/8/14 16:30
 */
public interface TreeNode<T, E extends TreeNode<T, E>> {
    /**
     * 获取节点id
     *
     * @return 树节点id
     */
    T id();

    /**
     * 获取该节点的父节点id
     *
     * @return 父节点id
     */
    T parentId();

    /**
     * 是否是根节点
     *
     * @return true：根节点
     */
    boolean root();

    /**
     * 获取所有子节点
     *
     * @return 子节点列表
     */
    List<E> getChildren();

    /**
     * 设置节点的子节点列表
     *
     * @param children 子节点
     */
    void setChildren(List<E> children);
}
