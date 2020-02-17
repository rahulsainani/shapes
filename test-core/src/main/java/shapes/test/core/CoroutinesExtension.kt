package shapes.test.core

import javax.inject.Qualifier
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(CoroutinesTestDispatcherExtension::class)
annotation class CoroutinesExtension
