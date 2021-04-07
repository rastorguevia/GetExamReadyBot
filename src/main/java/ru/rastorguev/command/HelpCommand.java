package ru.rastorguev.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.rastorguev.api.command.ICommand;
import ru.rastorguev.service.SendMessageService;

import java.util.LinkedList;
import java.util.List;

public class HelpCommand implements ICommand {

    private final SendMessageService sendMessageService;
    private CommandContainer commandContainer;

    private final StringBuilder sb = new StringBuilder();

    public HelpCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    public HelpCommand(SendMessageService sendMessageService, CommandContainer commandContainer) {
        this.sendMessageService = sendMessageService;
        this.commandContainer = commandContainer;
    }

    @Override
    public void execute(Update update) {

        final List<ICommand> commandList = new LinkedList<>(commandContainer.getCommandMap().values());

        sb.append("✨<b>Available commands</b>✨");
        sb.append("\n");

        int count = commandList.size();
        for (final ICommand command : commandList) {
            sb.append(command.getCommandName())
                    .append(": ")
                    .append(command.getCommandDescription());
            count--;
            if (count > 0) sb.append("\n");
        }
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), sb.toString());
        sb.setLength(0);
    }

    @Override
    public String getCommandName() {
        return "/help";
    }

    @Override
    public String getCommandDescription() {
        return "show all commands";
    }

}