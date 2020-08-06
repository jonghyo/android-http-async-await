package c.c.jonghyo.snippet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.eclipsesource.json.Json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch




class MainActivity : AppCompatActivity() {
    val URL = "https://qiita.com/api/v2/items/bf3e4e06022eebe8e3eb"
    var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getButton = findViewById(R.id.button) as Button
        getButton.setOnClickListener(object : View.OnClickListener {
            override
            fun onClick(view: View) {
                onParallelGetButtonClick()
            }
        })
    }

    fun onParallelGetButtonClick() = GlobalScope.launch(Dispatchers.Main) {
        val http = HttpUtil()
        async(Dispatchers.Default) { http.httpGet(URL) }.await().let {
            val result = Json.parse(it).asObject()
            val textView = findViewById(R.id.text) as TextView
            textView.setText(result.get("likes_count").asInt().toString() + "LGTM!")
        }
    }
}
