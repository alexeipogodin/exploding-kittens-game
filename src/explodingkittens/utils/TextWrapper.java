package explodingkittens.utils;

public class TextWrapper {
    public static String softWrapText(String text, int lineLength) {
        StringBuilder result = new StringBuilder();
        String[] words = text.split("\\s+");
        int currentLineLength = 0;

        for (String word : words) {
            if (currentLineLength + word.length() + 1 <= lineLength) {
                result.append(word).append(" ");
                currentLineLength += word.length() + 1;
            } else {
                result.append("\n").append(word).append(" ");
                currentLineLength = word.length() + 1;
            }
        }

        return result.toString().trim();
    }

    public static String softWrapTextBorders(String text, int lineLength, char border) {
        String wrappedText = softWrapText(text, lineLength - 4);
        StringBuilder result = new StringBuilder();
        for (String line: wrappedText.split("\n")) {

            result.append(border).append(" ").append(
                    String.format("%-" + (lineLength - 4) + "s", line)
            ).append(" ").append(border).append("\n");
        }
        return result.toString();
    }

    public static String textBorders(String text, int lineLength, char border) {
        StringBuilder result = new StringBuilder();
        for (String line: text.split("\n")) {
            result.append(border).append(" ").append(
                    String.format("%-" + (lineLength - 4) + "s", line)
            ).append(" ").append(border).append("\n");
        }
        return result.toString();
    }
}