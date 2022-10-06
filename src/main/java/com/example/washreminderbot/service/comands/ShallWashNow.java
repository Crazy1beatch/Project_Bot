package com.example.washreminderbot.service.comands;

import com.example.washreminderbot.service.BotLogic;

public class ShallWashNow implements Command{
    protected BotLogic myBotLogic;
    public ShallWashNow(BotLogic myBotLogic){
        this.myBotLogic = myBotLogic;
    }
    private final String helpAnswer = """
            /now - спросить, стоит ли сейчас мыть машину""";
    @Override
    public String getHelp() {
        return helpAnswer;
    }
    @Override
    public boolean isTriggered(String nameOfCommand) {
        return nameOfCommand.equals("/now");
    }
    private final String answerCaseYes = "Да, сегодня самый день, чтобы поехать на мойку <3";
    private final String answerCaseNo = "Нет, лучше сегодня не делать этого -_- (";
    @Override
    public String Execute(String nameOfUser) {
        return myBotLogic.isWeatherGood()
                ? answerCaseYes
                : answerCaseNo;
    }

    @Override
    public void setSettings(Command[] commandList) {

    }
}
