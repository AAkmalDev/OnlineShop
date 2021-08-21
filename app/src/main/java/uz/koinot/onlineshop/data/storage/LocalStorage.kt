package uz.koinot.onlineshop.data.storage

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor(@ApplicationContext context: Context) {

    private var sharedPref = context.getSharedPreferences("onlineShop", Context.MODE_PRIVATE)

    var accessToken: String
        set(value) = sharedPref.edit { putString(::accessToken.name, value) }
        get() = sharedPref.getString(::accessToken.name, "")!!
}