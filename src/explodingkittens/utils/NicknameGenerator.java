package explodingkittens.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * The {@code NicknameGenerator} class provides methods to generate unique
 * Exploding Kittens-themed nicknames for a specified array length.
 */
public class NicknameGenerator {

    // List of funny-sounding syllables or combinations
    private static final String[] FUNNY_SYLLABLES = {
            "meow", "boom", "kaboom", "purr", "kitty", "whisk", "fluff", "snug"
    };

    // List of cat-related terms for added humor
    private static final String[] CAT_TERMS = {
            "mischief", "whisker", "furball", "paws", "tail", "nap"
    };

    /**
     * Generates an array of unique Exploding Kittens-themed nicknames
     * with the specified length.
     *
     * @param arrayLength The length of the array of nicknames to generate.
     * @return An array of unique Exploding Kittens-themed nicknames.
     */
    public static String[] generateNicknames(int arrayLength) {
        Set<String> uniqueNicknames = new HashSet<>();
        Random random = new Random();

        while (uniqueNicknames.size() < arrayLength) {
            String nickname = generateNickname(random);
            uniqueNicknames.add(nickname);
        }

        return uniqueNicknames.toArray(new String[0]);
    }

    /**
     * Generates a unique Exploding Kittens-themed nickname.
     *
     * @param random The Random object used for generating random elements.
     * @return A unique Exploding Kittens-themed nickname.
     */
    private static String generateNickname(Random random) {
        // Add a funny syllable or combination
        StringBuilder syllable = new StringBuilder(FUNNY_SYLLABLES[random.nextInt(FUNNY_SYLLABLES.length)]);
        syllable.setCharAt(0, Character.toUpperCase(syllable.charAt(0)));

        // Add a cat-related term
        StringBuilder term = new StringBuilder(CAT_TERMS[random.nextInt(CAT_TERMS.length)]);
        term.setCharAt(0, Character.toUpperCase(term.charAt(0)));

        return syllable.append(term).toString();
    }
}

