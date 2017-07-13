package design.pattern.state.test;


import design.pattern.state.context.Context;
import design.pattern.state.state.ClosingState;

/**
 * 模拟电梯的动作
 *
 * @author suzhida
 * @since 2017/5/30 08:54
 */
public class StateTest {

    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
