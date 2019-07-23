package shapes.feature.core

import org.junit.jupiter.api.extension.ExtendWith
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(InstantTaskExecutorExtension::class)
annotation class InstantTask