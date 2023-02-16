package com.example.memeapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getdata()

        button.setOnClickListener{
            getdata()
        }

    }

    private fun getdata() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Wait Please")
        progressDialog.show()

        retrofitInstance.apiInterface.getData().enqueue(
            object : Callback<responsedataclass?> {
                override fun onResponse(
                    call: Call<responsedataclass?>,
                    response: Response<responsedataclass?>
                ) {
                   //memetitle.text=response.body()?.title
                    Glide.with(this@MainActivity).load(response.body()?.url).into(imageView);

                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<responsedataclass?>, t: Throwable) {

                   Toast.makeText(this@MainActivity,"Failed",Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                }
            }
        )
    }

    //https://meme-api.com/gimme

}