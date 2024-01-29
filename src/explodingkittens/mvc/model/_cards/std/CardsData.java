package explodingkittens.mvc.model._cards.std;

import explodingkittens.mvc.model._cards.actions.*;
import explodingkittens.mvc.model._cards.cats.*;
import explodingkittens.mvc.model._cards.defuse.DefuseCard;
import explodingkittens.mvc.model._cards.explode.ExplodingKittenCard;

public class CardsData {
    // Action cards' title and description.
    private static final String ATTACK_CARD_TITLE = "Attack Card";
    private static final String ATTACK_CARD_DESCRIPTION = "Do not draw any cards. Force the next player to take two turns.";
    private static final String FAVOR_CARD_TITLE = "Favor Card";
    private static final String FAVOR_CARD_DESCRIPTION = "One player must give you a card of their choice.";
    private static final String NOPE_CARD_TITLE = "Nope Card";
    private static final String NOPE_CARD_DESCRIPTION = "Stop the action of another player. You can play this at any time.";
    private static final String SEE_THE_FUTURE_CARD_TITLE = "See The Future Card";
    private static final String SEE_THE_FUTURE_CARD_DESCRIPTION = "Privately view the top three cards of the pile.";
    private static final String SHUFFLE_CARD_TITLE = "Shuffle Card";
    private static final String SHUFFLE_CARD_DESCRIPTION = "Shuffle the draw pile.";
    private static final String SKIP_CARD_TITLE = "Skip Card";
    private static final String SKIP_CARD_DESCRIPTION = "End your turn without drawing a card.";

    // Exploding Kittens card's title and description.
    private static final String EXPLODING_KITTEN_CARD_TITLE = "Exploding Kittens Card";
    private static final String EXPLODING_KITTEN_CARD_DESCRIPTION = "Show this card immediately.";

    // Defuse card's title and description.
    private static final String DEFUSE_CARD_TITLE = "Defuse Card";
    private static final String DEFUSE_CARD_DESCRIPTION = "Do not draw any cards. Force the next player to take two turns.";

    // Cat cards' title and description.
    private static final String BEARD_CARD_TITLE = "Beard Card";
    private static final String BEARD_CARD_DESCRIPTION = """
            This is a cat card and is powerless on its own.
            Play two beard cats as a pair to steal a random card
            from another player.""";
    private static final String CATTERMELON_CARD_TITLE = "Cattermelon Card";
    private static final String CATTERMELON_CARD_DESCRIPTION = """
            This is a cat card and is powerless on its own.
            Play two cattermelons as a pair to steal a random card
            from another player.""";
    private static final String HAIRY_POTATO_CARD_TITLE = "Hairy Potato Card";
    private static final String HAIRY_POTATO_CARD_DESCRIPTION = """
            This is a cat card and is powerless on its own.
            Play two hairy-potato cats as a pair to steal a random card
            from another player.""";
    private static final String RAINBOW_RALPHING_CARD_TITLE = "Rainbow Ralphing Card";
    private static final String RAINBOW_RALPHING_CARD_DESCRIPTION = """
            This is a cat card and is powerless on its own.
            Play two rainbow-ralphing cats as a pair to steal a random card
            from another player.""";
    private static final String TACO_CARD_TITLE = "Taco Card";
    private static final String TACO_CARD_DESCRIPTION = """
            This is a cat card and is powerless on its own.
            Play two taco cats as a pair to steal a random card
            from another player.""";

    // Blank card's title and description.
    private static final String BLANK_CARD_TITLE = "EXPLODING KITTENS GAME CARD";

    public static String getCardClassDescription(Card card) {
        switch (card) {
            case ActionCard actionCard -> {
                switch (actionCard) {
                    case AttackCard attackCard -> {
                        return ATTACK_CARD_DESCRIPTION;
                    }
                    case FavorCard favorCard -> {
                        return FAVOR_CARD_DESCRIPTION;
                    }
                    case NopeCard nopeCard -> {
                        return NOPE_CARD_DESCRIPTION;
                    }
                    case SeeTheFutureCard seeTheFutureCard -> {
                        return SEE_THE_FUTURE_CARD_DESCRIPTION;
                    }
                    case ShuffleCard shuffleCard -> {
                        return SHUFFLE_CARD_DESCRIPTION;
                    }
                    case SkipCard skipCard -> {
                        return SKIP_CARD_DESCRIPTION;
                    }
                    default -> {
                        return "Action Card Description";
                    }
                }
            }
            case CatCard catCard -> {
                switch (catCard) {
                    case BeardCard beardCard -> {
                        return BEARD_CARD_DESCRIPTION;
                    }
                    case CattermelonCard cattermelonCard -> {
                        return CATTERMELON_CARD_DESCRIPTION;
                    }
                    case HairyPotatoCard hairyPotatoCard -> {
                        return HAIRY_POTATO_CARD_DESCRIPTION;
                    }
                    case RainbowRalphingCard rainbowRalphingCard -> {
                        return RAINBOW_RALPHING_CARD_DESCRIPTION;
                    }
                    case TacoCard tacoCard -> {
                        return TACO_CARD_DESCRIPTION;
                    }
                    default -> {
                        return "Cat Card Description";
                    }
                }
            }
            case ExplodingKittenCard explodingKittenCard -> {
                return EXPLODING_KITTEN_CARD_DESCRIPTION;
            }
            case DefuseCard defuseCard -> {
                return DEFUSE_CARD_DESCRIPTION;
            }
            default -> {
                return null;
            }
        }
    }

    public static String getCardClassTitle(Card card) {
        switch (card) {
            case ActionCard actionCard -> {
                switch (actionCard) {
                    case AttackCard attackCard -> {
                        return ATTACK_CARD_TITLE;
                    }
                    case FavorCard favorCard -> {
                        return FAVOR_CARD_TITLE;
                    }
                    case NopeCard nopeCard -> {
                        return NOPE_CARD_TITLE;
                    }
                    case SeeTheFutureCard seeTheFutureCard -> {
                        return SEE_THE_FUTURE_CARD_TITLE;
                    }
                    case ShuffleCard shuffleCard -> {
                        return SHUFFLE_CARD_TITLE;
                    }
                    case SkipCard skipCard -> {
                        return SKIP_CARD_TITLE;
                    }
                    default -> {
                        return "Action Card Title";
                    }
                }
            }
            case CatCard catCard -> {
                switch (catCard) {
                    case BeardCard beardCard -> {
                        return BEARD_CARD_TITLE;
                    }
                    case CattermelonCard cattermelonCard -> {
                        return CATTERMELON_CARD_TITLE;
                    }
                    case HairyPotatoCard hairyPotatoCard -> {
                        return HAIRY_POTATO_CARD_TITLE;
                    }
                    case RainbowRalphingCard rainbowRalphingCard -> {
                        return RAINBOW_RALPHING_CARD_TITLE;
                    }
                    case TacoCard tacoCard -> {
                        return TACO_CARD_TITLE;
                    }
                    default -> {
                        return "Cat Card Title";
                    }
                }
            }
            case ExplodingKittenCard explodingKittenCard -> {
                return EXPLODING_KITTEN_CARD_TITLE;
            }
            case DefuseCard defuseCard -> {
                return DEFUSE_CARD_TITLE;
            }
            default -> {
                return BLANK_CARD_TITLE;
            }
        }
    }
}