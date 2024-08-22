package com.jzj.demo.tool;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class TreeData {
    private Integer id;
    private Integer parentId;
    private String name;
    private Integer sort;

    public static void main(String[] args) {
        List<TreeData> list = Lists.newArrayList(
                new TreeData(1, 0, "1-1", 1),
                new TreeData(2, 0, "1-2", 2),
                new TreeData(3, 1, "2-1", 1),
                new TreeData(4, 1, "2-2", 2),
                new TreeData(5, 2, "2-3", 1),
                new TreeData(6, 2, "2-4", 2),
                new TreeData(7, 3, "3-1", 1),
                new TreeData(8, 3, "3-2", 2)
        );
        List<Tree<Integer>> build = TreeUtil.build(list.stream().map(it -> {
            TreeNode<Integer> treeDataTreeNode = new TreeNode<>();
            treeDataTreeNode.setName(it.getName());
            treeDataTreeNode.setId(it.getId());
            treeDataTreeNode.setParentId(it.getParentId());
            treeDataTreeNode.setWeight(it.getSort());
            return treeDataTreeNode;
        }).collect(Collectors.toList()), 0);

        build.forEach(System.out::println);
    }
}
