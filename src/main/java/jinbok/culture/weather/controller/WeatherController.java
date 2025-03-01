package jinbok.culture.weather.controller;

import jinbok.culture.weather.dto.WeatherResponse;
import jinbok.culture.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:80", "http://localhost:8080"}, allowCredentials = "true")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public Mono<List<WeatherResponse.Item>> getWeather(@RequestParam String baseDate,
                                                       @RequestParam String baseTime,
                                                       @RequestParam Long nx,
                                                       @RequestParam Long ny) {
        return weatherService.getWeatherData(baseDate, baseTime, nx, ny);
    }
}
