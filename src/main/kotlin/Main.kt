import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    val runner: Runner = ObserveOn()
    runner.run()
}

interface Runner {
    fun run()
}

class ObserveOn : Runner {
    override fun run() {
        Single
            .just(2)
            .flatMap {
                Single.just(it * 2)
            }
            .observeOn(Schedulers.io())
            .flatMap {
                Single.just(it * 3)
            }
            .subscribe({
                println(it)
            }, {

            })

        Thread.sleep(10000)
    }
}

class SubscribeOn : Runner {
    override fun run() {
        Single
            .just(2)
            .flatMap {
                Single.just(it + 1)
            }
            .flatMap {
                Single.just(it * 2)
            }
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.just(it * 3)
            }
            .subscribe({
                println(it)
            }, {

            })

        Thread.sleep(10000)
    }
}

class SubscribeOnObserveOn1 : Runner {
    override fun run() {
        Single
            .just(2)
            .flatMap {
                Single.just(it + 1)
            }
            .flatMap {
                Single.just(it * 2)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .flatMap {
                Single.just(it * 3)
            }
            .subscribe({
                println(it)
            }, {

            })

        Thread.sleep(10000)
    }
}

class SubscribeOnObserveOn2 : Runner {
    override fun run() {
        Single
            .just(2)
            .flatMap {
                Single.just(it + 1)
            }
            .observeOn(Schedulers.computation())
            .flatMap {
                Single.just(it * 2)
            }
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.just(it * 3)
            }
            .subscribe({
                println(it)
            }, {

            })

        Thread.sleep(10000)
    }
}

class SubscribeOnMultiple : Runner {
    override fun run() {
        Single
            .just(2)
            .flatMap {
                Single.just(it + 1)
            }
            .subscribeOn(Schedulers.computation())
            .flatMap {
                Single.just(it * 2)
            }
            .subscribeOn(Schedulers.io())
            .flatMap {
                Single.just(it * 3)
            }
            .subscribe({
                println(it)
            }, {

            })

        Thread.sleep(10000)
    }
}

