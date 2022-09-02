package ru.litvinenko;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "firstTestTg_bot";
    }

    @Override
    public String getBotToken() {
        return "5555522218:AAGg-czdtqCD8lBtMOIs8mSlMOy7QRJuqNw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Model model = new Model();
        if(message != null && message.hasText()){

            switch (message.getText()){
                case "/help":
                    sendMsg(message, "Введите название города чтобы узнать погоду");
                    break;
                default:
                    try{
                        sendMsg(message, Weather.getWeather(message.getText()));
//                        sendImage(message);
                        sendGif(message);
                    } catch (IOException e){
                        sendMsg(message, "Для помощи введите /help");
                    }
            }

        }
    }
//    private void sendImage(Message message) throws IOException {
//
//        SendPhoto sendPhotoRequest = new SendPhoto();
//        sendPhotoRequest.setChatId(message.getChatId());
//        sendPhotoRequest.setPhoto(new InputFile(SendImage.sendImgFromUrl(message.getText())));
//        try {
//
//            execute(sendPhotoRequest);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
    private void sendGif(Message message) {
        SendAnimation animation = new SendAnimation();
        animation.setChatId(message.getChatId());
        try {
            animation.setAnimation(new InputFile(SendGif.sendGif(message.getText())));
//            animation.setAnimation(new InputFile("https://media1.giphy.com/media/VJMkd4eQJ526DTjzga/giphy.gif?cid=3144d253kcg4u2uk7dw1aw0uegroax4e10k03e41qeilvuwm&rid=giphy.gif&ct=g"));
            System.out.println(SendGif.sendGif(message.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            execute(animation);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("/help"));
        keyboardRows.add(firstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
