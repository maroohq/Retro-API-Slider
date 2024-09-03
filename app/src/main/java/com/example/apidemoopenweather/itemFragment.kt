package com.example.apidemoopenweather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apidemoopenweather.models.forecast.Hour3Forecast
import com.example.apidemoopenweather.models.forecast.WeatherForecast
import com.example.apidemoopenweather.placeholder.PlaceholderContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/forecast"
private const val PARAM_LAT = "lat"
const val LAT = -25.7459277
private const val PARAM_LON = "lon"
const val LON = 28.1879101
private const val PARAM_API_KEY = "appid"
const val API_KEY = "put your key here"

/**
 * A fragment representing a list of Items.
 */

class itemFragment : Fragment() {

    private var columnCount = 1
    lateinit var recylView: RecyclerView
    lateinit var  dataX : Response<WeatherForecast>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        recylView = view.findViewById(R.id.list)

        Log.v("openapi", "ran recylview assignment")




        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                CoroutineScope(Dispatchers.IO).launch {

                // Get data from api
                    dataX = RetrofitClient.weatherApi.getWeatherForecast(LAT.toString(), LON.toString(),
                        API_KEY)

                    if (dataX.isSuccessful) {
                        //open main coroutine
                        launch(Dispatchers.Main) {
                            adapter = dataX.body()?.list?.let { MyitemRecyclerViewAdapter(it) }
                        }
                    }
                    else

                    {
                            Log.v("APIDEMO","Something went wrong")
                        }
                    //show text on screen

                }
                //adapter = MyitemRecyclerViewAdapter(data.ITEMS)




            }
        }
        return view
    }

    fun setAdapter( list : List<Hour3Forecast>)
    {

       // this.recylView.adapter = MyitemRecyclerViewAdapter(list)

        this.recylView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


        //Log.v("openapi",this.parentFragmentManager.fragments.size.toString())
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            itemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}