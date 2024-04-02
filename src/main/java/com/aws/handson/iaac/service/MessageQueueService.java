package com.aws.handson.iaac.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageQueueService implements IMessageQueueService{
    @Value( "${aws.accesskey}" )
    private String AWS_ACCESS_KEY;
    @Value("${aws.secretkey}")
    private String AWS_SECRET_KEY;
    @Value("${aws.sqs.queue.url}")
    private String AWS_SQS_QUEUE_URL;
    @Async
    @Override
    public String produceMessageToSQS(String message) {
        AmazonSQS sqsClient = sqsClientBuilder();
        SendMessageRequest request = new SendMessageRequest().withQueueUrl(AWS_SQS_QUEUE_URL).withMessageBody(message)
                .withDelaySeconds(1);
        return sqsClient.sendMessage(request).getMessageId();
    }
    private AmazonSQS sqsClientBuilder() {
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY))).withRegion(Regions.SA_EAST_1)
                .build();
        return sqs;
    }
    public List<Message> consumeMessageFromSQS() {
        AmazonSQS sqsClient = sqsClientBuilder();

        ReceiveMessageRequest request = new ReceiveMessageRequest(AWS_SQS_QUEUE_URL).withWaitTimeSeconds(1)
                .withMaxNumberOfMessages(10);

        List<Message> sqsMessages = sqsClient.receiveMessage(request).getMessages();
        for (Message message : sqsMessages) {
            //run process for message
            System.out.println(message.getBody());

            //dequeue message after using it
            //also perfect step so check if message was successfully processed
            dequeuMessageFromSQS(message);
        }
        return sqsMessages;
    }

    public void dequeuMessageFromSQS(Message message) {
        AmazonSQS sqsClient = sqsClientBuilder();
        sqsClient.deleteMessage(new DeleteMessageRequest()
                .withQueueUrl(AWS_SQS_QUEUE_URL)
                .withReceiptHandle(message.getReceiptHandle()));

    }
}
