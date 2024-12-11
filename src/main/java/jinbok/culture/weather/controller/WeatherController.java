package jinbok.culture.weather.controller;

import jinbok.culture.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping()
    public Mono<String> getWeather(@RequestParam String baseDate,
                                   @RequestParam String baseTime,
                                   @RequestParam int nx,
                                   @RequestParam int ny) {
        return weatherService.getWeatherData(baseDate, baseTime, nx, ny);
    }
}
