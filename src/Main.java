import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.*;

import java.util.List;

public class Main {

    private static AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();

    public static void main(String[] args) throws InterruptedException {
        String arn = createTopic("MyNewTopic");
        String arn2 = createTopic("Test-Topic");
        publishToTopic(arn, "D&D is not fun");
        publishToTopic(arn2, "D&D is not fun");    }

    public static String createTopic(String name){
        CreateTopicRequest createTopicRequest = new CreateTopicRequest(name);
        CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
        System.out.println("Created topic: " + createTopicResult);
        return  createTopicResult.getTopicArn();
    }

    public static void subscribeToTopic(String topicArn, String protocol, String endpoint){
        SubscribeRequest subRequest = new SubscribeRequest(topicArn, protocol, endpoint);
        snsClient.subscribe(subRequest);
        System.out.println("Check your email and confirm subscription.");
    }

    public static void publishToTopic(String topicArn, String msg){
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        PublishResult publishResult = snsClient.publish(publishRequest);
        System.out.println("MessageId - " + publishResult.getMessageId());
    }

    public static void deleteTopic(String topicArn) {
        DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(topicArn);
        DeleteTopicResult deleteTopicResult = snsClient.deleteTopic(deleteTopicRequest);
        System.out.println("Successfully deleted " + topicArn);
    }



}
