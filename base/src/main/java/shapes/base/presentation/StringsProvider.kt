package shapes.base.presentation

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject
import shapes.base.di.ApplicationContext

class StringsProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getString(@StringRes resId: Int): String = context.getString(resId)
}
