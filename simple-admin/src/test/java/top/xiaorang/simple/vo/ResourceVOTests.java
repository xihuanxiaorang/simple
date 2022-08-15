package top.xiaorang.simple.vo;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.xiaorang.simple.common.pojo.TreeNode;
import top.xiaorang.simple.common.utils.TreeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liulei
 * @version 1.0
 * @since 2022/8/14 15:03
 */
@SpringBootTest
public class ResourceVOTests {
    @Test
    public void test() {
        List<ResourceVO> listVOS = TreeUtil.listToTree(buildData());
        System.out.println(JSONUtil.toJsonStr(listVOS));

        List<ResourceVO> resourceVOS = TreeUtil.treeToList(listVOS);
        System.out.println(JSONUtil.toJsonStr(resourceVOS));
    }

    public List<ResourceVO> buildData() {
        List<ResourceVO> list = new ArrayList<>();
        list.add(new ResourceVO(1L, 0L, "1"));
        list.add(new ResourceVO(2L, 1L, "1-2"));
        list.add(new ResourceVO(3L, 1L, "1-3"));
        list.add(new ResourceVO(4L, 2L, "1-2-4"));
        list.add(new ResourceVO(5L, 2L, "1-2-5"));
        list.add(new ResourceVO(6L, 3L, "1-3-6"));
        return list;
    }

    @Data
    static class ResourceVO implements TreeNode<Long, ResourceVO> {
        private Long id;
        private Long pid;
        private String name;
        private List<ResourceVO> children;

        public ResourceVO(Long id, Long pid, String name) {
            this.id = id;
            this.pid = pid;
            this.name = name;
        }

        @Override
        public Long id() {
            return this.id;
        }

        @Override
        public Long parentId() {
            return this.pid;
        }

        @Override
        public boolean root() {
            return this.pid == 0L;
        }
    }
}
