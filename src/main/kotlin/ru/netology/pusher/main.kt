package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val likeMessage = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken("ci5KtYQXTdqWI2J1yC_bdM:APA91bFEorzn769HP6gy7o-s8Z4Owz7AI69BZnSDX2NpCDRxUYUrfIlctHxxPZw7Sp7ZD9FH1XFIKKhzBJZeyCdEwv7WqZ3tPTHftEZwAUIh49iGpYVONPL-QfXLS6x4nNeZpIyo8Had")
        .build()

    val newPostMessage = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
          "postId": 1,
          "postAuthorId": "1",
          "postAuthor": "Alex",
          "content": "Counting more than half a billion individuals and over 70 different breeds, cats are the world's most popular pets"
        }""".trimIndent())
        .setToken("ci5KtYQXTdqWI2J1yC_bdM:APA91bFEorzn769HP6gy7o-s8Z4Owz7AI69BZnSDX2NpCDRxUYUrfIlctHxxPZw7Sp7ZD9FH1XFIKKhzBJZeyCdEwv7WqZ3tPTHftEZwAUIh49iGpYVONPL-QfXLS6x4nNeZpIyo8Had")
        .build()



    FirebaseMessaging.getInstance().send(newPostMessage)
    FirebaseMessaging.getInstance().send(likeMessage)
}
