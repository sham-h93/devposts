package com.hshamkhani.article_details.Utils

import com.hshamkhani.article_details.R
import com.hshamkhani.base_domain.Error
import com.hshamkhani.base_feature.UiString

fun Error.Local.asUiError(): UiString = when (this) {
    Error.Local.IO -> UiString.Resource(resId = R.string.article_details_error_loading_article)
    Error.Local.Unknown -> UiString.Resource(
        resId = com.hshamkhani.base_feature.R.string.all_unknown_error,
    )
}
