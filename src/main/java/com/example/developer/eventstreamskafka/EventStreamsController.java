package com.example.developer.eventstreamskafka;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventStreamsController {
	private KafkaTemplate<String, String> template;
	private List<String> messages = new CopyOnWriteArrayList<>();

	public EventStreamsController(KafkaTemplate<String, String> template) {
		this.template = template;
	}

	@KafkaListener(topics = "${listener.topic}")
	public void listen(ConsumerRecord<String, String> cr) throws Exception {
		messages.add(cr.value());
	}

	
	@PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody String message) {
        template.sendDefault(message);
    }

	@GetMapping("received")
	public String recv() throws Exception {
		String result = messages.toString();
		messages.clear();
		return result;
	}
}
