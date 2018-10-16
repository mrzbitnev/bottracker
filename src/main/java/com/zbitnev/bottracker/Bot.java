import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.security.GeneralSecurityException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatID = update.getMessage().getChatId();
            String messageFromChatID = update.getMessage().getText();

            MessageChecker messageCheck = new MessageChecker();
            SendMessage message = new SendMessage();

            if (messageCheck.isMagnetLink(update.getMessage().getText())) {
                MessageCipher cipherTest = new MessageCipher();
                ClientConnection clientConnection = new ClientConnection();

                try {
                    cipherTest.getCipher(chatID + messageFromChatID);
                    clientConnection.getConnection(cipherTest.getCipher(chatID + messageFromChatID));
                    message.setChatId(chatID).setText("Message sent on server");

                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            } else {
                message.setChatId(chatID).setText("We have a problem");
            }
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "usefulbb_bot";
    }

    @Override
    public String getBotToken() {
        return "532916624:AAEsbVZvBZrMzbL3YlgLTXgIZ14P9RIWY5o";
    }
}