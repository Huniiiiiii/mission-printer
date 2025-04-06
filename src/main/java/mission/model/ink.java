package mission.model;

public class ink {
    private int ink = 1000;

    public int getInk() {
        return ink;
    }

    public void minusInk() {

            if (ink <= 0) {
                throw new IllegalStateException("[ERROR] 잉크가 부족해 출력을 중단합니다.");
            }
            this.ink -= 1;

    }

    public void refillInk() {
        this.ink = 1000;
    }
}
