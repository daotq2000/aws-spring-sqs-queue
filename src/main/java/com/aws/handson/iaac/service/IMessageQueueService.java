package com.aws.handson.iaac.service;

public interface IMessageQueueService {
    String produceMessageToSQS(String message);
}
