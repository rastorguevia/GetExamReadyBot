package ru.rastorguev.api.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ICommand {

    void execute(Update update);

    String getCommandName();

    String getCommandDescription();

}
