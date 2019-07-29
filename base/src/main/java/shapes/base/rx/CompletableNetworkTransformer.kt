package shapes.base.rx

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.schedulers.Schedulers

class CompletableNetworkTransformer : CompletableTransformer {

    override fun apply(upstream: Completable): CompletableSource =
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
}