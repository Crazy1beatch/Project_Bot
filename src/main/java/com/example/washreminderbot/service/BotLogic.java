package com.example.washreminderbot.service;

public class BotLogic {
    String initialisation(String nameOfUser) {
        return "Привет, " + nameOfUser + ", я твой персональный бот-напоминалка)\n" +
                "Со мной твоя машинка всегда будет чистой!)";
    }

    String listOfHelp() {

        return """
                Смотри, что я умею:
                1) Попросить меня напомнить, когда мыть машину - /when
                2) Настроить период напоминаний - /period
                3) Спросить, стоит ли сегодня мыть машину - /now

                Пока на этом всё, но я всегда готов развиваться!
                Пиши моим разрабам - @XamaX2008 и @Crazy1beatch свои идеи по боту)""";
    }
    String shallIWashNow(){
        String answer;
        if (isWeatherGood())
            answer = "Да, сегодня самый день, чтобы поехать на мойку <3";
        else answer = "Нет, лучше сегодня не делать этого -_- (";
        return answer;
    }
    String defaultStuff(){
        return "Извини, я просто глупый бот (-_-)";
    }
    boolean isWeatherGood(){
        boolean result = true;
        return result;
    }
}
