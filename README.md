# WeatherREST

This is a web service for getting weather by coordinates

## Examples
### Request

`GET /weather/yandex`

### Response

    Weather in Saint Petersburg Latitude:59.93428 Longitude: 30.3351 temperature: 12 condition: overcast

### Request

`GET /weather/yandex?lat=62.598375&lon=50.886569`

### Response

    Weather in Emva Latitude:62.598375 Longitude: 50.886569 temperature: 0 condition: wet-snow

### Request
Shows the history of weather requests by city

`GET /weather/city-list`

### Response

    Saint Petersburg latitude: 59.93428 longitude: 30.3351
    time: 2023-10-15 16:23:15.912 temperature: 9 condition: light-rain
    time: 2023-10-15 16:26:54.894 temperature: 9 condition: light-rain
    time: 2023-10-15 16:28:05.928 temperature: 9 condition: light-rain
    time: 2023-10-15 16:30:54.12 temperature: 9 condition: light-rain
    time: 2023-10-15 19:31:30.722 temperature: 7 condition: light-rain
    time: 2023-10-15 22:40:22.881 temperature: 6 condition: cloudy

    Emva latitude: 62.598373 longitude: 50.88657
    time: 2023-10-15 22:42:16.942 temperature: 6 condition: overcast