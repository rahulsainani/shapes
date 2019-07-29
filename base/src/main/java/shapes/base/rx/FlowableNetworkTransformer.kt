package shapes.base.rx

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class FlowableNetworkTransformer<T> : FlowableTransformer<T, T> {

    override fun apply(upstream: Flowable<T>): Publisher<T> =
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
}