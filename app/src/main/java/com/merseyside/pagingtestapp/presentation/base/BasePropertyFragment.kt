package com.merseyside.pagingtestapp.presentation.base

import androidx.databinding.ViewDataBinding
import com.upstream.basemvvmimpl.presentation.fragment.BaseMvvmFragment

abstract class BasePropertyFragment<B : ViewDataBinding, M : BasePropertyViewModel> : BaseMvvmFragment<B, M>() {

}