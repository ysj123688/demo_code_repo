package java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 集合之流式操作
 *
 * @author suzhida
 * @since 2017/5/30 10:48
 */
public class StreamDemo {

    /**
     * 串行流和并行流比较
     */
    @Test
    public void sequentialAndParallelTest() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            double d = Math.random() * 1000;
            list.add(d + "");
        }

        long startSeqTime = System.currentTimeMillis();
        list.stream().sequential().sorted().count();
        System.out.println("使用串行流排序时间：" + (System.currentTimeMillis() - startSeqTime));

        long startParTime = System.currentTimeMillis();
        list.stream().parallel().sorted().count();
        System.out.println("使用并行流排序时间：" + (System.currentTimeMillis() - startParTime));
    }

    /**
     * 对元素进行过滤
     */
    @Test
    public void filterTest() {
        List<String> names = Arrays.asList("Jerome", "Jelly", "Nemo");
        // @formatter:off
        names.stream()
             .filter((name) -> name.startsWith("J"))
             .forEach(System.out::println);
        // @formatter:on

        /*
        终止操作
        该操作必须是流的最后一个操作，一旦被调用，Stream 就到了一个终止状态，而且不能再使用了。常见的终止操作有：
        forEach()：对每个元素做处理；
        toArray()：把元素导出到数组；
        findFirst()：返回第一个匹配的元素；
        anyMatch()：是否有匹配的元素等。
        count()：统计个数
        */
    }

    /**
     * 不能在lambda代码块里控制forEach的loop流程
     */
    @Test
    public void forEachLoop() {
        String[] args = {"jelly", "jerome", "nemo"};
        Arrays.asList(args).forEach(s -> {
            if (s.length() > 4) {
                // 这个return语句并不能终止loop循环
                return;
            }
            System.out.println(s);
        });
    }

//    int betCount = betNumbers
//            .stream()
//            .map(betNumber -> betNumber.getCount())
//            .reduce((sum, count) -> sum + count).get();

}
