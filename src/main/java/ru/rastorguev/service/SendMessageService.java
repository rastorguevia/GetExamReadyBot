package ru.rastorguev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rastorguev.api.service.ISendMessageService;
import ru.rastorguev.bot.GetExamReadyBot;

@Service
public class SendMessageService implements ISendMessageService {

    private final GetExamReadyBot examReadyBot;

    @Autowired
    public SendMessageService(GetExamReadyBot getExamReadyBot) {
        this.examReadyBot = getExamReadyBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            examReadyBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }

}
