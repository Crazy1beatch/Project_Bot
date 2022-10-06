package com.example.washreminderbot.service.comands;

public class HelpCommand implements Command{

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
    private Command[] commandList;
    public void setSettings(Command[] commandList){
        this.commandList = commandList;
    }
    @Override
    public String Execute(String nameOfUser) {
        StringBuilder result = new StringBuilder().append("Вот справка о том, как со мной работать:\n");
        for (var i : this.commandList) {
            result.append(i.getHelp()).append("\n");
        }
        return result.toString();
    }

}
