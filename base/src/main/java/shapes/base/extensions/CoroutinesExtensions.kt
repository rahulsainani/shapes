package shapes.base.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.launchSafe(
    action: suspend () -> Unit,
    onError: (Throwable) -> Unit = {}
) {
    launch {
        try {
            action()
        } catch (e: Exception) {
            onError(e)
        }
    }
}
