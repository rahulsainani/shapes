package shapes.base.rx

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class IOTransformer<T> : CompletableTransformer, FlowableTransformer<T, T> {

    override fun apply(upstream: Completable): CompletableSource =
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())

    override fun apply(upstream: Flowable<T>): Publisher<T> =
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
}
