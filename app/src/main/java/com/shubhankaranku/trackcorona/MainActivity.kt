package com.shubhankaranku.trackcorona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    lateinit var stateRV: RecyclerView
    lateinit var stateAdapter: StateAdapter
    lateinit var stateList: List<StateModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Init
        stateRV = findViewById(R.id.recyclerview)
        stateList = ArrayList<StateModel>()

        getStateInfo()
       // Toast.makeText(this,"VYVYvhgugygtfttyguhh", Toast.LENGTH_LONG).show()

    }

    private fun getStateInfo(){
        val url = "https://api.rootnet.in/covid19-in/stats/latesd"
        Log.d("url", url)
        val queue = Volley.newRequestQueue(this@MainActivity)
        val request =
            JsonObjectRequest(Request.Method.GET, url, null, { response ->
                try {
                    Log.i("Tag", url)
                    val dataObj = response.getJSONObject("data")
                    val regionalArray = dataObj.getJSONArray("regional")
                    for(i in 0 until regionalArray.length()){
                        val regionalObj = regionalArray.getJSONObject(i)
                        val statecode:String = regionalObj.getString("loc")
                        val active:Int = regionalObj.getInt("confirmedCasesIndian")
                        val confirmed:Int = regionalObj.getInt("confirmedCasesForeign")
                        val recovered:Int = regionalObj.getInt("discharged")
                        val deceased:Int = regionalObj.getInt("deaths")

                        Log.e("Tag", statecode)
                        Log.e("Tag", active.toString())


                        val StateModel = StateModel(statecode,active,confirmed,recovered,deceased)

                        stateList = stateList+ StateModel

                        stateAdapter = StateAdapter(stateList)
                        stateRV.layoutManager = LinearLayoutManager(this)
                        stateRV.adapter = stateAdapter
                    }



                }catch (e:JSONException){
                    e.printStackTrace()
                }
            }, { error ->
                {
                    Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show()
                }
            })
        queue.add(request)
    }
}