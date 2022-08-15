package top.xiaorang.simple.common.utils;

import top.xiaorang.simple.common.utils.enums.EnumUtil;
import top.xiaorang.simple.common.utils.enums.NameValueEnum;

/**
 * @author liulei
 * @version 1.0
 * @since 2022/8/15 21:28
 */
public class EnumUtilTests {
    public static void main(String[] args) {
        System.out.println(EnumUtil.isExist(A.values(), 1));
        System.out.println(EnumUtil.getNameByValue(A.values(), 1));
        System.out.println(EnumUtil.getValueByName(A.values(), "禁用"));
        System.out.println(EnumUtil.getEnumByName(A.values(), "启用"));
        System.out.println(EnumUtil.getEnumByValue(A.values(), 0));
    }

    enum A implements NameValueEnum<Integer> {
        DISABLE("禁用", 0), ENABLE("启用", 1);
        private String name;
        private Integer value;

        A(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Integer getValue() {
            return value;
        }
    }
}
