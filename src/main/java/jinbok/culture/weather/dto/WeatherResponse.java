package jinbok.culture.weather.dto;

import java.util.List;

public record WeatherResponse(
    Response response
) {

    public record Response(Body body) {}

    public record Body(ItemList items) {}

    public record ItemList(List<Item> item) {}

    public record Item(
            String category,
            String obsrValue
    ) {}
}
