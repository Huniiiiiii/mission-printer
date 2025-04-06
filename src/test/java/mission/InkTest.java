package mission;

import mission.model.ink;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InkTest {

    @Test
    void initial_ink_amount_should_be_1000() {
        ink testInk = new ink();
        assertEquals(1000, testInk.getInk());
    }

    @Test
    void ink_decreases_by_1_per_print() {
        ink testInk = new ink();
        testInk.minusInk();
        assertEquals(999, testInk.getInk());
    }

    @Test
    void exception_thrown_when_ink_is_empty() {
        ink testInk = new ink();
        for (int i = 0; i < 1000; i++) testInk.minusInk();
        IllegalStateException e = assertThrows(IllegalStateException.class, testInk::minusInk);
        assertTrue(e.getMessage().contains("[ERROR] 잉크가 부족해 출력을 중단합니다."));
    }

    @Test
    void refill_restores_ink_to_1000() {
        ink testInk = new ink();
        testInk.minusInk();
        testInk.refillInk();
        assertEquals(1000, testInk.getInk());
    }
}
