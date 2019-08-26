package dice.sinanya.windows;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.entity.imal.MessagesTypes;

import static dice.sinanya.tools.getinfo.Deck.getDeck;

public class test {
    public static void main(String[] args) {
        EntityTypeMessages entityTypeMessages = new EntityTypeMessages(MessagesTypes.PRIVATE_MSG, 0, "test");
        StringBuilder test = new StringBuilder();

        for (int i = 0; i <= 20; i++) {
            test.append("\n").append(getDeck(entityTypeMessages, "src/main/resources/test.yaml", "default"));
        }
        System.out.println(test);
    }
}
