package io.robert;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        args=new String[]{"Write a story about life and death"};
        Main m = new Main();
        m.callGemini(args);
    }
    public void callGemini(String args[])
    {
        
        ChatLanguageModel gemini=GoogleAiGeminiChatModel.builder()
            .apiKey(getToken())
            .modelName("gemini-1.5-flash")
            .build();

        String response = gemini.generate(args[0]);

        System.out.println(response);
    }

    private String getToken()
    {
        return "GEMINI_API_TOKEN";
    }
}