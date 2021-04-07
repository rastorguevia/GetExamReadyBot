package ru.rastorguev.command;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import ru.rastorguev.api.command.ICommand;
import ru.rastorguev.service.SendMessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CommandContainer {

    private final Map<String, ICommand> commandMap;
    private final ICommand unknownCommand;

    public CommandContainer(SendMessageService sendMessageService) {
        commandMap = new HashMap<>();
        initCommandMap(sendMessageService);
        unknownCommand = new UnknownCommand(sendMessageService);
    }

    private void initCommandMap(SendMessageService sendMessageService) {
        final Set<Class<? extends ICommand>> classes = new Reflections("ru.rastorguev.command").getSubTypesOf(ICommand.class);
        try {
            for (Class<? extends ICommand> commandClass : classes) {
                ICommand command = commandClass.getConstructor(SendMessageService.class).newInstance(sendMessageService);
                if ("/help".equals(command.getCommandName())) {
                    command = commandClass.getConstructor(SendMessageService.class, CommandContainer.class).newInstance(sendMessageService, this);
                }
                if ("unknown".equals(command.getCommandName())) continue;
                this.commandMap.put(command.getCommandName(), command);
            }
        } catch (Exception e) {
            //TODO add logger
            e.printStackTrace();
        }
    }

    public Map<String, ICommand> getCommandMap() {
        return commandMap;
    }

    public ICommand retrieveCommand(String commandIdentifier) {
        return this.commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
