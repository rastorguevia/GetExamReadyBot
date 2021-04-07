package ru.rastorguev.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.rastorguev.api.command.ICommand;
import ru.rastorguev.service.SendMessageService;

public class StopCommand implements ICommand {

    private final SendMessageService sendMessageService;

    private final static String STOP_MESSAGE = "Bye mate";

    public StopCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }

    @Override
    public String getCommandName() {
        return "/stop";
    }

    @Override
    public String getCommandDescription() {
        return "stop bot";
    }

}
