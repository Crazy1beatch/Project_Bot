package com.example.washreminderbot.service.comands;

public class HelpCommand implements Command, CanHaveList{

    private final String helpAnswer = """
            /help - получить справку по работе бота""";

    @Override
    public String getHelp() {
        return helpAnswer;
    }

    @Override
    public boolean isTriggered(String nameOfCommand) {
        return nameOfCommand.equals("/help");
    }
    private Command[] commandList = null;

    @Override
    public String Execute() {
        StringBuilder result = new StringBuilder().append("Вот справка о том, как со мной работать:\n");
        if (this.commandList != null) {
            for (var i : this.commandList) {
                result.append(i.getHelp()).append("\n");
            }
            return result.toString();
        } else {
            return "Не правильно работает прога";
        }
    }

    @Override
    public void setList(Command[] list) {
        this.commandList = list;
    }
}
