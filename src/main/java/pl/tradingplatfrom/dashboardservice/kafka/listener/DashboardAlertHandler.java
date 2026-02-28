package pl.tradingplatfrom.dashboardservice.kafka.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import pl.tradingplatform.events.AlertEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardAlertHandler {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "detected-alerts")
    public void handleAlert(AlertEvent alert) {
        log.info("Sending to WS: {} - {}", alert.getTicker(), alert.getDropMessage());
        messagingTemplate.convertAndSend("/topic/alerts", alert);
        messagingTemplate.convertAndSend("topic/alerts/" + alert.getTicker(), alert);
    }

}
