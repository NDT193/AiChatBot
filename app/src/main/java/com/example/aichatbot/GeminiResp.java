package com.example.aichatbot;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.ChatFutures;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.BlockThreshold;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.ai.client.generativeai.type.HarmCategory;
import com.google.ai.client.generativeai.type.SafetySetting;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Collections;
import java.util.concurrent.Executor;

import ApiService.ResponeApi;

public class GeminiResp {

    public static void getRespone(ChatFutures chatModel, String query, ResponeApi callback)
    {
        Content.Builder userMessageBuilder = new Content.Builder();
        userMessageBuilder.setRole("user");
        userMessageBuilder.addText(query);

        Content userMessage = userMessageBuilder.build();
        Executor executor = Runnable::run;

        ListenableFuture<GenerateContentResponse> response = chatModel.sendMessage(userMessage);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {

                String resultText = result.getText();
                callback.onResponse(resultText);

            }

            @Override
            public void onFailure(Throwable throwable) {

                throwable.printStackTrace();
                callback.onError(throwable);

            }
        },executor);
    }

    public GenerativeModelFutures getModel()
    {
        String apiKey = BuildConfig.apiKey;
        SafetySetting harassementSafety = new SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.ONLY_HIGH);

        GenerationConfig.Builder configBuilder = new GenerationConfig.Builder();
        configBuilder.temperature= 0.9f;
        configBuilder.topK= 16;
        configBuilder.topP = 0.1f;
        GenerationConfig generationConfig = configBuilder.build();

        GenerativeModel gm = new GenerativeModel(
                "gemini-1.5-flash",apiKey,generationConfig,
                Collections.singletonList(harassementSafety)
        );

       return GenerativeModelFutures.from(gm);
    }


}
