package jinbok.culture.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WebClient webClient;

    @Value("${externalApi.weather.authKey}")
    private String authKey;

    public Mono<String> getWeatherData(String baseDate, String baseTime, int nx, int ny) {
        // API URL과 파라미터들을 동적으로 설정
        String url = String.format("?pageNo=1&numOfRows=1000&dataType=JSON&base_date=%s&base_time=%s&nx=%d&ny=%d&authKey=%s",
                baseDate, baseTime, nx, ny, authKey);

        // WebClient를 사용하여 GET 요청을 보냄
        return webClient.get()
                .uri(url)  // URL에 파라미터 추가
                .retrieve()  // 응답을 받음
                .bodyToMono(String.class);  // 응답 본문을 String으로 변환
    }
}
