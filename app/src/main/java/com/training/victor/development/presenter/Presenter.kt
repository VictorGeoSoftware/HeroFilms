package com.training.victor.development.presenter

abstract class Presenter<T1> {
    var view: T1? = null


    open fun destroy() {
        System.out.println("onDestroy!")
        view = null
    }
}