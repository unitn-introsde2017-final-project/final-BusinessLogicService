# Business Logic Service for the Final Project at IntroSDE

## Endpoints

### business/getPersonProfile?userId=X

This function is to assemble all the data for a profile to provide for a client like:
* Personal details
* Weather information
* Step history and data
* Motivational text depending on the step count/goal and the weather

Example result:

```
{
  "stepGoal": 5001,
  "weather_info": "The temperature is -10 C outside, the wind blows with 1 m/s",
  "birthdate": "1990-05-19",
  "city": "Debrecen,hu",
  "name": "Teszt Remote",
  "id": 1,
  "steps": [
    {
      "date": "2017-01-25",
      "number": 6019,
      "personId": 1,
      "id": 1
    },
    {
      "date": "2017-01-26",
      "number": 4000,
      "personId": 1,
      "id": 2
    },
    {
      "date": "2017-01-27",
      "number": 6568,
      "personId": 1,
      "id": 3
    },
    {
      "date": "2017-01-28",
      "number": 7893,
      "personId": 1,
      "id": 4
    },
    {
      "date": "2017-01-29",
      "number": 10987,
      "personId": 1,
      "id": 5
    },
    {
      "date": "2017-01-30",
      "number": 4000,
      "personId": 1,
      "id": 6
    },
    {
      "date": "2017-02-08",
      "number": 6700,
      "personId": 1,
      "id": 11
    }
  ],
  "steps_today": "No steps for today yet, but it is never late to start!"
}
```

## Copyright

&copy; Sándor Tibor Nagy as the final project for Introduction to Service Design and Engineering course 2016/2017 at [UNITN](http://www.unitn.it/)