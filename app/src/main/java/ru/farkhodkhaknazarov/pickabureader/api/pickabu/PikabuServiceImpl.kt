package ru.farkhodkhaknazarov.pickabureader.api.pickabu

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.farkhodkhaknazarov.pickabureader.common.Constants

class PikabuServiceImpl {
    companion object {
        fun getApiService(): PikabuService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val request = retrofit.create(PikabuService::class.java)

            return request
        }
    }
}