package ru.netology.sender;

import ru.netology.entity.Location;

import java.util.Map;

public interface MessageSender {

    String send(Map<String, String> headers);
}
