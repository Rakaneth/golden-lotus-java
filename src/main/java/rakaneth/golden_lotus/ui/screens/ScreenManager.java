package rakaneth.golden_lotus.ui.screens;


import java.util.Stack;

public class ScreenManager {
    private final Stack<BaseScreen> screenStack;
    private static ScreenManager instance = null;

    private ScreenManager() {
        screenStack = new Stack<>();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void push(BaseScreen screen) {
        screenStack.push(screen);
        screen.enter();
    }

    public BaseScreen pop() { return screenStack.pop(); }
    public BaseScreen peek() { return screenStack.peek(); }

    public void clear() {
        while (!screenStack.isEmpty()) {
            BaseScreen toExit = screenStack.pop();
            toExit.exit();
        }
    }

    public void render() {
        screenStack.forEach(BaseScreen::render);
    }
}
