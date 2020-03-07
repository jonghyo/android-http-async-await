package c.c.jonghyo.snippet

import okhttp3.Request

class HttpUtil {

    fun httpGet(url : String): String? {
        val request = Request.Builder()
                .url(url)
                .build()

        val response = HttpClient.instance.newCall(request).execute()
        val body = response.body?.string()
        return body
    }
}
