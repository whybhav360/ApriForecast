# 🟠 Apricot Forecast App

A lightweight FastAPI-based backend that forecasts apricot production for different countries using time series modeling (ARIMA). The app serves predictions and graphs through an API, suitable for integration with Android frontends or web dashboards.

---

App Installation Guide:


Download the aab file from releases
while installing the applicatio your phone might ask to approve request to download from this location - press allow
while installation a pop up might come with a malicous/unknown software warning - either press scan app or install app without scanning

## 🚀 Features

- 📊 **ARIMA-based Forecasting**  
  Auto-configured ARIMA models per country using `pmdarima` and `statsmodels`.

- 🗺️ **Country-wise Predictions**  
  Forecast apricot production per country for the next 5 years.

- 📈 **Graph Visualization**  
  Historical + Forecast data shown in a single, cleanly connected line plot.

- 💾 **Model Caching**  
  Pretrained models are saved and reused using `.pkl` files to improve response time.

- 🌐 **API-Ready**  
  Fully functional FastAPI backend with GET and POST endpoints.

---
Clone the repo


git clone https://github.com/whybhav360/ApriForecast.git
cd apricot-forecast-api
Install dependencies
(Recommended: Use a virtual environment)

pip install -r requirements.txt
Run the app locally

uvicorn main:app --reload


Visit:

API Docs: http://localhost:8000/docs

Root Message: http://localhost:8000/

🌐 API Endpoints
GET /
Returns a status message.

{
  "message": "Apricot Forecast API is running"
}


POST /forecast
Request forecast data for a specific country.

Request Body:
{
  "country": "Turkey"
}
Response:
Returns a graph image (base64 encoded PNG) and forecasted values:

{
  "country": "Turkey",
  "plot_image": "<base64_string>",
  "predictions": [
    {
      "year": 2024,
      "predicted_apricot_growth": 125.0,
      "lower_ci": 110.0,
      "upper_ci": 140.0
    },
    ...
  ]
}

📦 Deploying to Render
Push this project to GitHub.

Go to https://render.com

Create a new Web Service

Link your repo.

Set:

Build Command: pip install -r requirements.txt

Start Command: bash start.sh

Environment: Python 3.x

Deploy! 🎉

📊 Sample Forecast Graph

![Sample Forecast](https://github.com/user-attachments/assets/e9e3412d-7b68-4798-acd0-7aa72b59fa42)


Blue = Historical data | Orange = Forecast


📱App Working:

![ApriForecast-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/1453de5a-190d-4cb4-be3e-d9620e2ce0e5)


📌 Future Improvements:

Add support for user-uploaded datasets

Model retraining dashboard

🤝 Contributions
Feel free to fork the repo, submit issues, or open pull requests!

🧑‍💻 Author:
Vaibhav Madaan,
Android & AI Developer

