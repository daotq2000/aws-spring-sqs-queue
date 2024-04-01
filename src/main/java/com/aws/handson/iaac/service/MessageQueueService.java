package com.aws.handson.iaac.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueService implements IMessageQueueService{
    @Value( "${aws.accesskey}" )
    private String AWS_ACCESS_KEY;
    @Value("${aws.secretkey}")
    private String AWS_SECRET_KEY;
    private String AWS_SQS_QUEUE_URL;
    @Override
    public String produceMessageToSQS(String message) {
        AmazonSQS sqsClient = sqsClientBuilder();
        SendMessageRequest request = new SendMessageRequest().withQueueUrl(AWS_SQS_QUEUE_URL).withMessageBody(message)
                .withDelaySeconds(1);
        return null;
    }
    private AmazonSQS sqsClientBuilder() {
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY))).withRegion(Regions.SA_EAST_1)
                .build();
        return sqs;
    }
}
