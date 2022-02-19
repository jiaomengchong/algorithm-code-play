package com.jmc.algorithm.weekProblem.class_2021_12_4_week;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 来自北京北明数科信息技术有限公司
 * area表示的是地区全路径,最多可能有6级,用分隔符连接,分隔符是 spliter,
 * 分隔符是逗号
 * 例如：
 * area = 中国,四川,成都  或者  中国,浙江,杭州  或者  中国,浙江,义乌
 * spliter = ,
 * count表示门店数
 * class AreaResource {
 *     String area;
 *     String spliter;
 *     long count;
 * }
 * area = "中国,四川,成都"
 * spliter = ","
 * count = 10
 * 现在需要把  List<AreaResource> 进行字符串转换，供下游处理，需要做到同级的地域能合并
 * 比如
 * area为 中国,四川,成都  有10个门店
 *        中国,浙江,杭州 有25个门店
 *        中国,浙江,义乌 有22个门店
 *        中国,四川,成都 有25个门店
 * spliter为逗号 "," 最终转化成JSON的形式，并且同级的地域需要被合并，最终生成的JSON字符串如下所示
 * 返回: {
 *    "中国":
 *           {"四川":{"成都":35]},
 *            "浙江":{"义乌":22,"杭州":25}}}
 * 请实现下面的方法 public String mergeCount(List<AreaResource> areas)
 * @Author jmc
 * @Description
 * @Date 2021/12/28 10:18
 **/
public class Code02_MergeArea {
    private static class AreaResource {
        private String area;
        private String splitter;
        private long count;

        public AreaResource(String area, String splitter, long count) {
            this.area = area;
            this.splitter = splitter;
            this.count = count;
        }
    }

    private static class Area {
        private String name;
        private Map<String, Area> next;
        private long count;

        public Area(String name, long count) {
            this.name = name;
            this.count = count;
            next = new HashMap<>();
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            if (!name.isEmpty()) {
                // {"中国":"{四川xxx,江苏xxx}"}
                sb.append("\"").append(name).append("\"").append(":");
            }
            if (next.size() == 0) {
                sb.append(count);
            } else {
                sb.append("{");
                for (Area child : next.values()) {
                    sb.append(child.toString() + ",");
                }
                sb.setCharAt(sb.length() - 1, '}');
            }

            return sb.toString();
        }
    }

    public static String merge(List<AreaResource> areas) {
        Area all = new Area("", 0);
        for (AreaResource resource : areas) {
            String path = resource.area;
            // 中国,四川,成都
            String[] split = path.split(resource.splitter);
            long count = resource.count;
            process(0, split, all, count);
        }
        return all.toString();
    }

    private static void process(int index, String[] split, Area pre, long count) {
        if (index == split.length) {
            pre.count += count;
        } else {
            String cur = split[index];
            if (!pre.next.containsKey(cur)) {
                pre.next.put(cur, new Area(cur, 0));
            }
            process(++index, split, pre.next.get(cur), count);
        }
    }

    public static void main(String[] args) {
        AreaResource a1 = new AreaResource("中国,四川,成都", ",", 10);
        AreaResource a2 = new AreaResource("中国,浙江,杭州", ",", 50);
        AreaResource a3 = new AreaResource("中国,浙江,杭州", ",", 25);
        AreaResource a4 = new AreaResource("中国,浙江,义务", ",", 22);
        AreaResource a5 = new AreaResource("中国,四川,成都", ",", 15);
        AreaResource a6 = new AreaResource("中国,四川,攀枝花", ",", 12);
        AreaResource a7 = new AreaResource("中国,浙江,宁波", ",", 16);

        List<AreaResource> areas = new ArrayList<>();
        areas.add(a1);
        areas.add(a2);
        areas.add(a3);
        areas.add(a4);
        areas.add(a5);
        areas.add(a6);
        areas.add(a7);

        String ans = merge(areas);

        System.out.println(ans);
    }
}
