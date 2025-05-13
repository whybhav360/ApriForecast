package com.example.testmajor
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var predictButton: Button
    private lateinit var imageView: ImageView
    private lateinit var resultText: TextView
    private lateinit var progressBar: ProgressBar

    private val countries = listOf(
        "India", "Australia", "China", "Canada", "France",
        "Germany", "Pakistan", "Russia", "Ukraine", "United States"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.countrySpinner)
        predictButton = findViewById(R.id.predictButton)
        imageView = findViewById(R.id.imageView)
        resultText = findViewById(R.id.resultText)
        progressBar = findViewById(R.id.progressBar)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        predictButton.setOnClickListener {
            val selectedCountry = spinner.selectedItem.toString()
            sendRequest(selectedCountry)
        }
    }

    private fun sendRequest(country: String) {
        predictButton.isEnabled = false
        progressBar.visibility = View.VISIBLE
        resultText.visibility = View.GONE
        imageView.visibility = View.GONE

        val request = PredictionRequest(country)

        RetrofitClient.apiService.getPrediction(request).enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                progressBar.visibility = View.GONE
                predictButton.isEnabled = true

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!
                    val decodedImage = Base64.decode(body.plot_image_base64, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)

                    imageView.setImageBitmap(bitmap)
                    imageView.visibility = View.VISIBLE
                    resultText.text = body.prediction
                    resultText.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                predictButton.isEnabled = true
                Toast.makeText(this@MainActivity, "Failed: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
