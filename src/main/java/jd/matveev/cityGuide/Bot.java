package jd.matveev.cityGuide;

import jd.matveev.cityGuide.data.CityRepository;
import jd.matveev.cityGuide.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.List;
import java.util.Optional;

@Component
public class Bot extends TelegramLongPollingBot {

    private static CityRepository data;

    static {ApiContextInitializer.init();}

    public static void runBot() {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot(data));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String inMessageText = message.getText();
            String outMessageText = getOutMessageText(inMessageText);
            sendMsg(message, outMessageText);
        }

    }

    private String getOutMessageText(String inMessageText) {
        Optional<List<City>> citiesList = Optional.ofNullable(data.findByCityName(inMessageText));
        return citiesList.isPresent() && !citiesList.get().isEmpty() ? citiesList.get().get(0).getDescription(): "City not found";
    }

    private void sendMsg(Message message, String responseMessage) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(responseMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "SimpleCityGuide_bot";
    }

    @Override
    public String getBotToken() {
        return "1270021357:AAETw-ByqX3baZiwube89yDSxUM0vSne6KE";
    }

    @Autowired
    public Bot(CityRepository cityRepository) {
        this.data = cityRepository;
    }
}
