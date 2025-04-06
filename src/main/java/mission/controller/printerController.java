package mission.controller;

import mission.model.ink;
import mission.utils.AsciiGenerator;
import mission.view.printerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class printerController {
    private final AsciiGenerator generator;
    private final ink inkManager;
    private final printerView view;
    private static final int LINE_COUNT = 5;
    private final Map<String, Runnable> menuMap = new HashMap<>();

    public printerController(AsciiGenerator generator, ink inkManager, printerView view) {
        this.generator = generator;
        this.inkManager = inkManager;
        this.view = view;
        initMenu();
    }

    public void run() {
        while (true) {
            view.printMenu();
            String input = view.input("");
            if (input.equals("4")) return;
            Runnable action = menuMap.get(input);
            if (action != null) action.run();
        }
    }

    private void initMenu() {
        menuMap.put("1", this::handlePrint);
        menuMap.put("2", this::handleInkCheck);
        menuMap.put("3", this::handleInkRefill);
    }

    private void handlePrint() {
        String text = view.input("출력할 문자를 입력해주세요. ");
        int size = view.paperSize("용지 크기를 입력해주세요. ");
        printAsciiHorizontally(text, size);
        view.showMessage("출력이 완료되었습니다. ");
    }

    private void handleInkCheck() {
        int amount = inkManager.getInk();
        view.showMessage(String.format("잉크 잔량 : %d/1000", amount));
    }

    private void handleInkRefill() {
        inkManager.refillInk();
        view.showMessage("잉크를 교체하였습니다. ");
    }

    public void printAsciiHorizontally(String input, int paperSize) {
        int start = 0;
        while (start < input.length()) {
            int end = Math.min(start + paperSize, input.length());
            printOneLine(input.substring(start, end));
            start = end;
        }
    }

    private void printOneLine(String input) {
        List<StringBuilder> lines = initLines();
        try {
            buildAsciiLines(input, lines);
            printLines(lines);
        } catch (IllegalStateException e) {
            view.showMessage(e.getMessage());
        }
    }

    private void buildAsciiLines(String input, List<StringBuilder> lines) {
        for (char ch : input.toCharArray()) {
            if (ch == ' ') {
                appendSpace(lines);
                continue;
            }
            inkManager.minusInk();
            appendAscii(lines, ch);
        }
    }

    private List<StringBuilder> initLines() {
        List<StringBuilder> lines = new ArrayList<>();
        for (int i = 0; i < LINE_COUNT; i++) lines.add(new StringBuilder());
        return lines;
    }

    private void appendAscii(List<StringBuilder> lines, char ch) {
        int num = Character.getNumericValue(ch);
        List<String> ascii = generator.getNumberAsciiDesign(num);
        for (int i = 0; i < ascii.size(); i++) {
            lines.get(i).append(String.format("%s  ", ascii.get(i)));
        }
    }

    private void appendSpace(List<StringBuilder> lines) {
        for (int i = 0; i < LINE_COUNT; i++) {
            lines.get(i).append(String.format("%s  ", "   "));
        }
    }

    private void printLines(List<StringBuilder> lines) {
        for (StringBuilder line : lines) System.out.println(line);
    }
}
