package ru.rastorguev.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.rastorguev.api.command.ICommand;
import ru.rastorguev.service.SendMessageService;

public class StartCommand implements ICommand {

    private final SendMessageService sendMessageService;

    private final static String START_MESSAGE = "Hello mate";

    public StartCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }

    public String getCommandName() {
        return "/start";
    }

    @Override
    public String getCommandDescription() {
        return "start bot";
    }

}
