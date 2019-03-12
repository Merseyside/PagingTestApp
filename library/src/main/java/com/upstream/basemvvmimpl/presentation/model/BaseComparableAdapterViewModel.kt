package com.upstream.basemvvmimpl.presentation.model

import androidx.recyclerview.widget.DiffUtil

abstract class BaseComparableAdapterViewModel<M : Comparable<M>> : BaseAdapterViewModel<M>() {

    abstract fun isContentTheSame(obj: M): Boolean
    abstract fun isItemsTheSame(obj: M): Boolean
    abstract fun compareTo(obj: M) : Int

}
