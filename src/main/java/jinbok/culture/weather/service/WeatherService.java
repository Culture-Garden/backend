package jinbok.culture.weather.service;

import jinbok.culture.weather.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WebClient webClient;

    @Value("${externalApi.weather.authKey}")
    private String authKey;

    public Mono<List<WeatherResponse.Item>> getWeatherData(String baseDate, String baseTime, Long nx, Long ny) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("pageNo", 1)
                        .queryParam("numOfRows", 1000)
                        .queryParam("dataType", "JSON")
                        .queryParam("base_date", baseDate)
                        .queryParam("base_time", baseTime)
                        .queryParam("nx", nx)
                        .queryParam("ny", ny)
                        .queryParam("authKey", authKey)
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .map(weatherResponse -> weatherResponse.response().body().items().item());
    }
}
