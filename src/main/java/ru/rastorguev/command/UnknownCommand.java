package ru.rastorguev.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.rastorguev.api.command.ICommand;
import ru.rastorguev.service.SendMessageService;

public class UnknownCommand implements ICommand {

    public static final String UNKNOWN_MESSAGE = "I don't understand you \uD83D\uDE1F, write /help to see what I understand.";

    private final SendMessageService sendMessageService;

    public UnknownCommand(SendMessageService sendBotMessageService) {
        this.sendMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }

    @Override
    public String getCommandName() {
        return "unknown";
    }

    @Override
    public String getCommandDescription() {
        return "unknown";
    }

}
