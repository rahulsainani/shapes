package shapes.base.rx

import dagger.Reusable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Reusable
class SchedulerProvider @Inject constructor() {

    fun computation(): Scheduler = Schedulers.computation()

    fun io(): Scheduler = Schedulers.io()
}