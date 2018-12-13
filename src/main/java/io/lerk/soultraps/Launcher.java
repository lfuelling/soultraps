package io.lerk.soultraps;

public class Launcher extends Level {

    public static final int BASE_WIDTH = 64;
    public static final int BASE_HEIGHT = 32;

    public Launcher() {
        super();
        Button title = getTitle();
        addObject(title, getWidth() / 2, 0);
    }

    private Button getTitle() {
        return new Button("Soultraps", 256, 320, 72f) {
            private boolean up = true;
            private int count = 0;
            private int throttleCount = 0;

            private final int throttle = 12;
            private final int hoverAmount = 3;

            @Override
            public void act() {
                if (throttleCount >= throttle) {
                    if (up) {
                        setLocation(getX(), getY() + 1);
                        if (count > hoverAmount) {
                            count = 0;
                            up = false;
                        }
                        count++;
                    } else {
                        setLocation(getX(), getY() - 1);
                        if (count > hoverAmount) {
                            count = 0;
                            up = true;
                        }
                        count++;
                    }
                    throttleCount = 0;
                } else {
                    throttleCount++;
                }
            }
        };
    }

}
