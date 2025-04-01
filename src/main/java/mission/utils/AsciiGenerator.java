package mission.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;

public class AsciiGenerator {
    private final List<List<String>> data;

    static private final List<String> fileNames = List.of(
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    );

    public AsciiGenerator() {
        data = new ArrayList<>();
        fileNames.forEach(fileName -> {
            try {
                Path path = Paths.get(Objects.requireNonNull(
                        getClass().getClassLoader().getResource("numberAsciiDesign/" + fileName + ".txt")
                ).toURI());
                String content = Files.readString(path);

                List<String> paddedLines = Arrays.stream(content.split("\n"))
                        .map(line -> String.format("%-3s", line))
                        .toList();

                data.add(paddedLines);
            } catch (URISyntaxException | IOException e) {
                throw new IllegalStateException("[ERROR] 아스키아트를 로드하지 못했습니다.");
            }
        });
    }

    public List<String> getNumberAsciiDesign(int number) {
        try {
            return data.get(number);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("[ERROR] 범위를 초과했습니다. ");
        }
    }
}
