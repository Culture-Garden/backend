package jinbok.culture.weather.controller;

import jinbok.culture.weather.dto.WeatherResponse;
import jinbok.culture.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping()
    public Mono<List<WeatherResponse.Item>> getWeather(@RequestParam String baseDate,
                                                       @RequestParam String baseTime,
                                                       @RequestParam int nx,
                                                       @RequestParam int ny) {
        return weatherService.getWeatherData(baseDate, baseTime, nx, ny);
    }
}
