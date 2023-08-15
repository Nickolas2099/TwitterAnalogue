package com.example.twitterAnalog.controller.communication;


import com.example.twitterAnalog.domain.response.Response;
import com.example.twitterAnalog.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("phrase-service-public/communication/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/getMySubscribers")
    public ResponseEntity<Response> getMySubscribers(@RequestHeader String accessToken) {
        log.info("START endpoint getMySubscribers, accessToken: {}", accessToken);
        ResponseEntity<Response> resp = subscriptionService.getMySubscribers(accessToken);
        log.info("END endpoint getMySubscribers, resp: {}", resp);
        return resp;
    }

    @GetMapping("/getMyPublishers")
    public ResponseEntity<Response> getMyPublishers(@RequestHeader String accessToken) {
        log.info("START endpoint getMyPublishers, accessToken: {}", accessToken);
        ResponseEntity<Response> resp = subscriptionService.getMyPublishers(accessToken);
        log.info("END endpoint getMyPublishers, resp: {}", resp);
        return resp;
    }

    @GetMapping("/subscription")
    public ResponseEntity<Response> subscription(@RequestHeader String accessToken, @RequestBody SubscriptionReq req) {
        log.info("START endpoint subscription, accessToken: {}, SubsciptionReq", accessToken, req);
        ResponseEntity<Response> resp = subscriptionService.subscription(req, accessToken);
        log.info("END endpoint subscription, resp: {}", resp);
        return resp;
    }

    @GetMapping("/unsubscription")
    public ResponseEntity<Response> unsubscription(@RequestHeader String accessToken, @RequestBody UnsubscriptionReq req) {
        log.info("START endpoint unsubscription, accessToken: {}, UnsubscriptionReq: {}", accessToken, req);
        ResponseEntity<Response> resp = subscriptionService.unsubscription(req, accessToken);
        log.info("END endpoint unsubscription, resp: {}", resp);
        return resp;
    }
}
